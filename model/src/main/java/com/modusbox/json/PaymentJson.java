package com.modusbox.json;

import com.modusbox.internal.PaymentStatus;
import com.modusbox.types.*;
import lombok.Builder;

import java.math.BigInteger;

@Builder
public record PaymentJson(PaymentId id,
                          PaymentKey key,
                          String currency,
                          BigInteger amount,
                          OriginatorId originator,
                          BeneficiaryId beneficiary,
                          SenderId sender,
                          ReceiverId receiver,
                          PaymentStatus status) {
}
