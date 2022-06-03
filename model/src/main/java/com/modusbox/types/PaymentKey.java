package com.modusbox.types;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Getter;

@JsonSerialize(using = RawValueSerializer.class)
public record PaymentKey(@Getter String raw) implements RawValue<String> {
}
