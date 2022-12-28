package com.matveev.kalory.model.id;

import lombok.EqualsAndHashCode;

import static java.util.Objects.requireNonNull;

@EqualsAndHashCode
public class Id {

    private final Long value;

    public Id(Long value) {
        this.value = requireNonNull(value, "Value should not be null!");
    }

    public Long value() {
        return value;
    }

    public String toString() {
        return value.toString();
    }
}
