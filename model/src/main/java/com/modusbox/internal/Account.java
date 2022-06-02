package com.modusbox.internal;

import com.modusbox.types.AccountId;
import com.modusbox.types.CustomerId;

public record Account(AccountId id,
                      CustomerId customerId,
                      AccountType type,
                      String number) {
}
