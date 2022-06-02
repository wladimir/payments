package com.modusbox.internal;

import com.modusbox.types.AccountId;
import com.modusbox.types.CustomerId;
import com.modusbox.types.PaymentId;

import java.math.BigInteger;

public record Payment(PaymentId id,
                      String currency,
                      BigInteger amount,
                      CustomerId originator,
                      CustomerId beneficiary,
                      AccountId sender,
                      AccountId receiver,
                      PaymentStatus status) {
}
