package com.matveev.kalory.config;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.matveev.kalory.model.id.Id;

import java.io.IOException;

public class IdSerializer extends JsonSerializer<Id> {

    @Override
    public Class<Id> handledType() {
        return Id.class;
    }

    @Override
    public void serialize(Id id, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        gen.writeNumber(id.value());
    }
}
