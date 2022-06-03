package com.modusbox.types;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Getter;

import java.util.UUID;

@JsonSerialize(using = RawValueSerializer.class)
public record SenderId(@Getter UUID raw) implements RawValue<UUID> {
}
