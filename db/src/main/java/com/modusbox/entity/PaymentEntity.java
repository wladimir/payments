package com.modusbox.entity;

import com.modusbox.internal.Payment;
import com.modusbox.internal.PaymentStatus;
import com.modusbox.types.AccountId;
import com.modusbox.types.CustomerId;
import com.modusbox.types.PaymentId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigInteger;
import java.util.UUID;

@Entity
@Table(name = "payments")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class PaymentEntity {
    @Id
    @GeneratedValue
    private UUID id;

    @Column
    private String currency;

    @Column
    private BigInteger amount;

    @Column
    private UUID originator;

    @Column
    private UUID beneficiary;

    @Column
    private UUID sender;

    @Column
    private UUID receiver;

    @Column
    private PaymentStatus status;
}
