package com.matveev.kalory.model.id;

import com.fasterxml.jackson.annotation.JsonCreator;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;

@Validated
public class WeightCheckId extends Id {

    private WeightCheckId(Long value) {
        super(value);
    }

    @JsonCreator
    public static WeightCheckId weightCheckId(@NotNull Long value) {
        return new WeightCheckId(value);
    }
}
