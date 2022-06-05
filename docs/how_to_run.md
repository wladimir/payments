## Run the app

* `git clone https://github.com/wladimir/payments.git && cd payments`
* `mvn clean compile package`
* `docker compose up -d`
* `mvn exec:java --projects client -Dexec.mainClass="com.modusbox.PaymentClient"`

NOTE: for local development purposes replace docker compose command with `docker compose up db migrate kafka -d`.

## Clean up

* `docker compose down`
