package com.matveev.kalory.model.request.create;

import lombok.Getter;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@Validated
public class CreateTag {

    @NotNull
    @NotEmpty
    private String name;
}
