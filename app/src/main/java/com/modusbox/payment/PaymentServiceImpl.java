package com.modusbox.payment;

import com.modusbox.internal.CreatePayment;
import com.modusbox.internal.Payment;
import com.modusbox.types.PaymentId;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {
    private final PaymentRepository repository;

    @Override
    public Payment create(final CreatePayment payment) {
        return repository.save(PaymentEntity.toEntity(payment)).toPayment();
    }

    @Override
    public Optional<Payment> get(final PaymentId id) {
        return repository.findById(id.raw()).map(PaymentEntity::toPayment);
    }
}
