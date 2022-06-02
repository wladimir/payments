package com.modusbox.payment;

import com.modusbox.internal.CreatePayment;
import com.modusbox.internal.Payment;
import com.modusbox.internal.PaymentStatus;
import com.modusbox.types.*;
import lombok.*;

import javax.persistence.*;
import java.math.BigInteger;
import java.util.UUID;

@Entity
@Table(name = "payments")
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class PaymentEntity {
    @Id
    @GeneratedValue
    private UUID id;
    private String key;
    private String currency;
    private BigInteger amount;
    private UUID originator;
    private UUID beneficiary;
    private UUID sender;
    private UUID receiver;
    @Enumerated(EnumType.STRING)
    private PaymentStatus status;

    public Payment toPayment() {
        return Payment.builder()
                .id(new PaymentId(getId()))
                .key(new PaymentKey(getKey()))
                .currency(getCurrency())
                .amount(getAmount())
                .originator(new OriginatorId(getOriginator()))
                .beneficiary(new BeneficiaryId(getBeneficiary()))
                .sender(new SenderId(getSender()))
                .receiver(new ReceiverId(getReceiver()))
                .status(getStatus())
                .build();
    }

    public static PaymentEntity toEntity(final CreatePayment payment) {
        return PaymentEntity.builder()
                .key(payment.key())
                .currency(payment.currency())
                .amount(payment.amount())
                .originator(UUID.fromString(payment.originator()))
                .beneficiary(UUID.fromString(payment.beneficiary()))
                .sender(UUID.fromString(payment.sender()))
                .receiver(UUID.fromString(payment.receiver()))
                .status(PaymentStatus.created)
                .build();
    }
}
