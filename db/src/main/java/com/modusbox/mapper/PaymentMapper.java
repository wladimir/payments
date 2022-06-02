package com.modusbox.mapper;

import com.modusbox.entity.PaymentEntity;
import com.modusbox.internal.Payment;
import com.modusbox.types.AccountId;
import com.modusbox.types.CustomerId;
import com.modusbox.types.PaymentId;

public class PaymentMapper {
    public static Payment toModel(PaymentEntity payment) {
        return new Payment(new PaymentId(payment.getId()),
                payment.getCurrency(),
                payment.getAmount(),
                new CustomerId(payment.getOriginator()),
                new CustomerId(payment.getBeneficiary()),
                new AccountId(payment.getSender()),
                new AccountId(payment.getReceiver()),
                payment.getStatus());
    }
}
