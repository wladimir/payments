package com.modusbox.account;

import com.modusbox.internal.AccountType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
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
    private UUID customerId;
    @Enumerated(EnumType.STRING)
    private AccountType type;
    private String number;
}
