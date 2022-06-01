package com.modusbox.entity;

import com.modusbox.internal.AccountType;
import lombok.*;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "accounts")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Account {
    @Id
    @GeneratedValue
    private UUID id;

    @Column
    private AccountType type;
}
