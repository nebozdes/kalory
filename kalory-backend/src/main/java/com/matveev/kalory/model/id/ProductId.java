package com.matveev.kalory.model.id;

import com.fasterxml.jackson.annotation.JsonCreator;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;

@Validated
public class ProductId extends Id {

    private ProductId(Long value) {
        super(value);
    }

    @JsonCreator
    public static ProductId productId(@NotNull Long value) {
        return new ProductId(value);
    }
}
