package com.modusbox.internal;

import com.modusbox.validation.ValidUUID;
import lombok.Builder;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.math.BigInteger;

@Builder
public record CreatePayment(
        @NotBlank String key,
        @NotBlank @Size(min = 3, max = 3)
        String currency,
        BigInteger amount,
        @ValidUUID
        String originator,
        @ValidUUID
        String beneficiary,
        @ValidUUID
        String sender,
        @ValidUUID
        String receiver) {
}
