package com.modusbox;

import com.modusbox.internal.CreatePayment;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

import java.math.BigInteger;
import java.util.UUID;
import java.util.concurrent.CountDownLatch;

public class PaymentsClient {
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_RESET = "\u001B[0m";

    public static void main(final String[] args) throws InterruptedException {
        final RestTemplate restTemplate = new RestTemplate();
        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        final int size = 10;
        final CountDownLatch latch = new CountDownLatch(size);

        for (int i = 0; i < size; i++) {
            final CreatePayment payment = new CreatePayment(
                    UUID.randomUUID().toString(),
                    "USD",
                    BigInteger.valueOf(100),
                    "9d964c67-913e-40ea-b407-41cd2701ff4e",
                    "4b83ee55-667f-4184-a0f1-4d376b952de6",
                    "a9d9689e-8d6f-428d-89b5-a1927c3c1b91",
                    "1445a62a-d224-4df5-83e0-ad73515e2c1b"
            );

            HttpEntity<CreatePayment> request = new HttpEntity<>(payment, headers);
            String response = restTemplate.postForObject("http://localhost:8080/payments", request, String.class);

            System.out.println(ANSI_GREEN + response + ANSI_RESET);
            latch.countDown();
        }

        latch.await();
    }
}
