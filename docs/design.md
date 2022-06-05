## Modules

Application is split into multiple, logically separated modules

* `model` (shared data models)
* `db` (persistence layer: entities, repositories and migrations)
* `messaging` (kafka configuration and producer)
* `client` (standalone client used for simulating requests)
* `app` (Spring Boot application, controllers and services)

## Message flow

`PaymentsController` exposes `create payment` and `query payment status` functionality.
`202 Accepted` response is returned immediately to the client after the record is persisted ot database with `created` status.
Kafka processing is decoupled from client requests, but using scheduled `SenderService.send()` invocation, that periodically polls
database for records with `created` status. 
We use optimistic locking approach using `@Version` number to avod conflicting writes. Drawback to this approach is that 
multiple nodes can read the same record and we can emit same message to Kafka more than once. Requirements do not specify this
as a failure mode, so we can assume that downstream client are idempotent. If that is not correct assumption, we could
use pessimistic locking and lock row -> read data -> send to kafka -> update row with `sent` status -> release lock, with 
some performance penalty.

## Performance

Any questions wrt performance should be backed by data, based on load testing on dedicated instance, ideally as part of 
CICD processes, so any regressions can be timely observed.
To make sure that application can handle the expected load (1M writes, 10M reads), we can start by checking how this
translates to both storage and compute requirements:
* based on our data model we can estimate one record (payment) size, and actually can use handy Postgres function to check it
```
select sum(pg_column_size(p) + 24) -- metadata
from payments p
where id = '364551b0-d403-4b8e-84ca-94e4a6c68402';
```
return 200 bytes, which is slighly over the sum of all field sizes, but we can attribute that to Postgres internals
and work with 200 bytes per record, which means that we should roughlt have 200 * 1000000 = 200 MB / per 1M messages.
Compute part (both reads and writes) should be load tested, making note of p90, p95 and p99 values which can lead to 
providing specific SLAs to customers, together with considering any multi-tenancy specifics.
