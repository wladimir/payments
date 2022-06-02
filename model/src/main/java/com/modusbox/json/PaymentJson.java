package com.modusbox.json;

import com.modusbox.internal.PaymentStatus;
import lombok.Builder;

import java.math.BigInteger;
import java.util.UUID;

@Builder
public record PaymentJson(UUID id,
                          String key,
                          String currency,
                          BigInteger amount,
                          UUID originator,
                          UUID beneficiary,
                          UUID sender,
                          UUID receiver,
                          PaymentStatus status) {
}
