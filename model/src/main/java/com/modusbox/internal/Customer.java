package com.modusbox.internal;

import com.modusbox.types.CustomerId;

public record Customer(CustomerId id,
                       String name) {
}
