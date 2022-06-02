package com.modusbox.internal;

import com.modusbox.types.AccountId;
import com.modusbox.types.CustomerId;
import com.modusbox.types.PaymentId;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.math.BigInteger;

public record CreatePayment(
        @NotBlank PaymentId id,
        @NotBlank @Size(min = 3, max = 3)
        String currency,
        @NotBlank
        BigInteger amount,
        @NotBlank
        CustomerId originator,
        @NotBlank
        CustomerId beneficiary,
        @NotBlank
        AccountId sender,
        @NotBlank
        AccountId receiver) {
}
