package com.modusbox.json;

import com.modusbox.internal.PaymentStatus;
import com.modusbox.types.AccountId;
import com.modusbox.types.CustomerId;
import com.modusbox.types.PaymentId;

import java.math.BigInteger;

public record PaymentJson(PaymentId id,
                          String currency,
                          BigInteger amount,
                          CustomerId originator,
                          CustomerId beneficiary,
                          AccountId sender,
                          AccountId receiver,
                          PaymentStatus status) {
}
