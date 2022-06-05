## Modules

Application is split into multiple, logically separated modules

* `model` (shared data models)
* `db` (persistence layer: entities, repositories and migrations)
* `messaging` (kafka configuration and producer)
* `client` (standalone client used for simulating requests)
* `app` (Spring Boot application, controllers and services)

## Data types

Each distinct data type is backed by its' own implementation (ie. `PaymentId`). This leads to clearer data model
in which mistakes are easily noticeable (can't easily mistake `BeneficiaryId` with `OriginatorId`), but required support of
custom (de)serializers to keep this stricter model within application boundaries while external world observes plain old UUIDs/strings.

## Database model

We use three related tables: `customers`, `accounts` and `payments`. Each payment requires existence of referenced customers and accounts.

## Message flow

`PaymentsController` exposes `create payment` and `query payment status` functionality.
`202 Accepted` response is returned immediately to the client after the record is persisted to database with `created` status.
Kafka processing is decoupled from client requests, using scheduled `SenderService.send()` invocation, that periodically polls
database for records with `created` status. Record `key` is also stored and used to process only unique records.
We use optimistic locking approach using `@Version` number to avod conflicting writes. Drawback to this approach is that 
multiple nodes can read the same record and we can emit same message to Kafka more than once. Requirements do not specify this
as a failure mode, so we can assume that downstream clients can process messages idempotently. If that is not a correct assumption, we could
use pessimistic locking and lock row -> read data -> send to kafka -> update row with `sent` status -> release lock, with 
some performance penalty.

## Performance

Any questions wrt performance should be backed by data based on load testing, ideally as part of CICD processes 
so any regressions can be timely observed. To make sure that application can handle the expected load 
(max 1M writes, 10M reads per customer per day), we can start by checking how this translates to both storage and compute requirements:
* based on our data model we can estimate one record (payment) size, eg. if `uuid` type is 16 bytes, and we have 5 such fields, etc. 
For practicality we can use handy Postgres function to check column sizes
```
select sum(pg_column_size(p))
from payments p
where id = '364551b0-d403-4b8e-84ca-94e4a6c68402';
```
This return 200 bytes for one sample record (slighly over expected value - the sum of all field sizes, but we can attribute that to Postgres internals)
which means that we will roughly need 200 * 1000000 = 200 MB / per 1M messages.
Compute part (both reads and writes) should be load tested, making note of p90, p95 and p99 values which can be used to specify
SLAs to customers, together with considering any multi-tenancy specific requirements.
Decoupling of database writes from message processing allows us to scale both components independently.

## TODOs

* add tests
* improve mappers for object conversions, so no manual work is required
* add better error messages, also return them to client
* add CRUD API for customers and accounts
