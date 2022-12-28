package com.matveev.kalory.model.id;

import com.fasterxml.jackson.annotation.JsonCreator;

public class UserId extends Id {

    private UserId(Long value) {
        super(value);
    }

    @JsonCreator
    public static UserId userId(Long value) {
        return new UserId(value);
    }
}
