package com.modusbox.payment;

import com.modusbox.mapper.PaymentMapper;
import com.modusbox.internal.CreatePayment;
import com.modusbox.internal.Payment;
import com.modusbox.repository.PaymentRepository;
import com.modusbox.types.PaymentId;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {
    private final PaymentRepository paymentRepository;

    @Override
    public void create(final CreatePayment payment) {

    }

    @Override
    public Optional<Payment> get(final PaymentId id) {
        return paymentRepository.findById(id.raw())
                .map(PaymentMapper::toModel);
    }
}
