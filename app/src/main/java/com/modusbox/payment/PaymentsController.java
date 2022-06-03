package com.modusbox.payment;

import com.modusbox.internal.CreatePayment;
import com.modusbox.json.PaymentJson;
import com.modusbox.json.PaymentMapper;
import com.modusbox.types.PaymentId;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class PaymentsController {
    private final PaymentService service;

    @PostMapping("/payments")
    public ResponseEntity<PaymentJson> createPayment(final @Valid @RequestBody CreatePayment payment) {
        return ResponseEntity.status(HttpStatus.ACCEPTED)
                .body(PaymentMapper.INSTANCE.toJson(service.create(payment)));
    }

    @GetMapping("/payments")
    public ResponseEntity<List<PaymentJson>> getAllPayments() {
        return ResponseEntity.ok(service.getAll().stream()
                .map(PaymentMapper.INSTANCE::toJson)
                .collect(Collectors.toList()));
    }

    @GetMapping("/payments/{id}")
    public ResponseEntity<PaymentJson> getPayment(@PathVariable final UUID id) {
        return ResponseEntity.of(service.get(new PaymentId(id))
                .map(PaymentMapper.INSTANCE::toJson));
    }
}
