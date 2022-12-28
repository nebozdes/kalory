package com.matveev.kalory.model.request.list;

import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Validated
@RequiredArgsConstructor
public abstract class BaseList {

    @Min(0)
    @NotNull
    public final int page;

    @Min(1)
    @NotNull
    public final int limit;
}
