package com.matveev.kalory.model;

import com.matveev.kalory.domain.entity.AmountType;
import com.matveev.kalory.model.id.ProductId;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;

@Getter
@Builder
@ToString
@Validated
@EqualsAndHashCode
public class Product {

    @NotNull
    private final ProductId id;

    @NotNull
    @NotEmpty
    private final String name;

    @NotNull
    @Positive
    private final BigDecimal baseAmount;

    @NotNull
    private final AmountType baseAmountType;

    @NotNull
    @Positive
    private final BigDecimal baseCalories;

    @NotNull
    private final BigDecimal baseCarbs;

    @NotNull
    private final BigDecimal baseLipids;

    @NotNull
    private final BigDecimal baseProteins;
}
