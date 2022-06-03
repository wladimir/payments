package com.modusbox.types;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;

public class RawValueSerializer<T> extends StdSerializer<RawValue<T>> {
    public RawValueSerializer() {
        this(null);
    }

    public RawValueSerializer(Class<RawValue<T>> t) {
        super(t);
    }

    @Override
    public void serialize(RawValue<T> value, JsonGenerator jgen, SerializerProvider provider) throws IOException {
        jgen.writeString(value.getRaw().toString());
    }
}
