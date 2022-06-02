package com.modusbox.payment;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.modusbox.internal.CreatePayment;
import com.modusbox.internal.Payment;
import com.modusbox.json.PaymentJson;
import com.modusbox.types.PaymentId;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class PaymentsController {
    private final PaymentService service;

    @PostMapping("/payments")
    public ResponseEntity<PaymentJson> createPayment(final @Valid @RequestBody CreatePayment payment) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(payment).toJson());
    }

    @GetMapping("/payments/{id}")
    public ResponseEntity<PaymentJson> getPayment(@PathVariable final UUID id) {
        return ResponseEntity.of(service.get(new PaymentId(id)).map(Payment::toJson));
    }

}
