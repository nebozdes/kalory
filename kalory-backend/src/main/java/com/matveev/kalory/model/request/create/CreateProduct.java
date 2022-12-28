package com.matveev.kalory.model.request.create;

import com.matveev.kalory.domain.entity.AmountType;
import lombok.Getter;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import java.math.BigDecimal;

@Getter
@Setter
@Validated
public class CreateProduct {

    @NotNull
    @NotEmpty
    private String name;

    @NotNull
    @Positive
    private BigDecimal baseAmount;

    @NotNull
    private AmountType baseAmountType;

    @NotNull
    @Positive
    private BigDecimal baseCalories;

    @NotNull
    @PositiveOrZero
    private BigDecimal baseCarbs;

    @NotNull
    @PositiveOrZero
    private BigDecimal baseLipids;

    @NotNull
    @PositiveOrZero
    private BigDecimal baseProteins;
}
