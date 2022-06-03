package com.modusbox.internal;

import com.modusbox.types.*;
import lombok.Builder;
import lombok.Setter;

import java.math.BigInteger;

@Builder
public record Payment(PaymentId id,
                      PaymentKey key,

                      String currency,
                      BigInteger amount,
                      OriginatorId originator,
                      BeneficiaryId beneficiary,
                      SenderId sender,
                      ReceiverId receiver,
                      @Setter PaymentStatus status) {
}
