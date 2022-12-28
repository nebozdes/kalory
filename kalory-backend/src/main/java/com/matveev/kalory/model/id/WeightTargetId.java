package com.matveev.kalory.model.id;

import com.fasterxml.jackson.annotation.JsonCreator;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;

@Validated
public class WeightTargetId extends Id {

    private WeightTargetId(Long value) {
        super(value);
    }

    @JsonCreator
    public static WeightTargetId weightTargetId(@NotNull Long value) {
        return new WeightTargetId(value);
    }
}
