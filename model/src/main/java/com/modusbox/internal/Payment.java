package com.modusbox.internal;

import com.modusbox.json.PaymentJson;
import com.modusbox.types.*;
import lombok.Builder;

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
                      PaymentStatus status) {
    public PaymentJson toJson() {
        return PaymentJson.builder()
                .id(id().raw())
                .key(key().raw())
                .currency(currency())
                .amount(amount())
                .originator(originator().raw())
                .beneficiary(beneficiary().raw())
                .sender(sender().raw())
                .receiver(receiver().raw())
                .status(status())
                .build();
    }
}
