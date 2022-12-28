package com.matveev.kalory.model.id;

import com.fasterxml.jackson.annotation.JsonCreator;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;

@Validated
public class ConsumedProductId extends Id {
    private ConsumedProductId(Long value) {
        super(value);
    }

    @JsonCreator
    public static ConsumedProductId consumedProductId(@NotNull Long id) {
        return new ConsumedProductId(id);
    }
}
