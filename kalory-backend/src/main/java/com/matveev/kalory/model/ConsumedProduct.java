package com.matveev.kalory.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.matveev.kalory.model.id.ConsumedProductId;
import com.matveev.kalory.model.id.ProductId;
import com.matveev.kalory.model.id.UserId;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

@Getter
@Builder
@ToString
@Validated
@EqualsAndHashCode
public class ConsumedProduct {

    @NotNull
    private final ConsumedProductId id;

    @NotNull
    private final ProductId productId;

    @JsonIgnore
    private final UserId userId;

    @NotNull
    private final LocalDate consumptionDate;

    @NotNull
    @Positive
    private final BigDecimal consumptionAmount;

    @NotNull
    @Positive
    private final BigDecimal calculatedCalories;

    @NotNull
    @PositiveOrZero
    private final BigDecimal calculatedCarbs;

    @NotNull
    @PositiveOrZero
    private final BigDecimal calculatedLipids;

    @NotNull
    @PositiveOrZero
    private final BigDecimal calculatedProteins;

    private final Optional<String> comment;
}
