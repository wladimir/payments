package com.modusbox.entity;

import com.modusbox.types.CustomerId;

import java.time.LocalDateTime;

public record Customer(CustomerId id, String name, LocalDateTime createdAt, LocalDateTime updatedAt) {
}
