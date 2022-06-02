package com.modusbox.payment;

import com.modusbox.internal.CreatePayment;
import com.modusbox.internal.Payment;
import com.modusbox.types.PaymentId;

import java.util.Optional;

public interface PaymentService {
    void create(CreatePayment payment);

    Optional<Payment> get(PaymentId id);
}
