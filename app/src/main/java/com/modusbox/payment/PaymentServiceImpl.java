package com.modusbox.payment;

import com.modusbox.internal.CreatePayment;
import com.modusbox.internal.Payment;
import com.modusbox.types.PaymentId;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {
    private final PaymentRepository repository;

    @Override
    public Payment create(final CreatePayment payment) {
        return repository.save(PaymentEntity.toEntity(payment)).toPayment();
    }

    @Override
    public List<Payment> getAll() {
        return StreamSupport
                .stream(repository.findAll().spliterator(), false)
                .map(PaymentEntity::toPayment)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Payment> get(final PaymentId id) {
        return repository.findById(id.raw()).map(PaymentEntity::toPayment);
    }
}
