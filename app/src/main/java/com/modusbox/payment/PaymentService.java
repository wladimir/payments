package com.modusbox.payment;

import com.modusbox.internal.CreatePayment;
import com.modusbox.internal.Payment;
import com.modusbox.types.PaymentId;

import java.util.List;
import java.util.Optional;

public interface PaymentService {
    Payment create(CreatePayment payment);

    List<Payment> getAll();

    Optional<Payment> get(PaymentId id);
}
