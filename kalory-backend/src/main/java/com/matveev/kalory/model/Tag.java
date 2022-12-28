package com.matveev.kalory.model;

import com.matveev.kalory.model.id.TagId;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Builder
@ToString
@Validated
@EqualsAndHashCode
public class Tag {

    @NotNull
    private final TagId id;

    @NotNull
    @NotEmpty
    private final String name;
}
