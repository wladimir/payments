# Payments app

### Requirements
* Java 17
* Maven
* Docker Compose

### Docs
* [How to run](./docs/how_to_run.md)
* [Design](./docs/design.md)

### API

* GET all payments
```
curl --location --request GET 'http://localhost:8080/payments' \
```

* GET one payment
```
curl --location --request GET 'http://localhost:8080/payments/ac48ed4f-2d4e-4106-96eb-42f788d3b294'
```

* POST new payment
```
curl --location --request POST 'http://localhost:8080/payments' \
--header 'Content-Type: application/json' \
--data-raw '{
    "key": "key-1",
    "currency": "USD",
    "amount": 100,
    "originator": "9d964c67-913e-40ea-b407-41cd2701ff4e",
    "beneficiary": "4b83ee55-667f-4184-a0f1-4d376b952de6",
    "sender": "a9d9689e-8d6f-428d-89b5-a1927c3c1b91",
    "receiver": "1445a62a-d224-4df5-83e0-ad73515e2c1b"
}'
```


