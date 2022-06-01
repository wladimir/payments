package com.modusbox;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.UUID;

@SpringBootApplication(scanBasePackages = "com.modusbox")
public class PaymentsApplication {
    public static void main(String[] args) {
        //SpringApplication.run(PaymentsApplication.class, args);
        for (int i = 0; i < 2; i++) {
            System.err.println(UUID.randomUUID());
        }
    }
}
