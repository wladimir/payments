package com.modusbox.entity;

import com.modusbox.internal.AccountType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "accounts")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class AccountEntity {
    @Id
    @GeneratedValue
    private UUID id;

    @Column
    private UUID customerId;

    @Column
    private AccountType type;

    @Column
    private String number;
}
