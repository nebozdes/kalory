package com.matveev.kalory.model.id;

import com.fasterxml.jackson.annotation.JsonCreator;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;

@Validated
public class TagId extends Id {
    private TagId(Long value) {
        super(value);
    }

    @JsonCreator
    public static TagId tagId(@NotNull Long tagId) {
        return new TagId(tagId);
    }
}
