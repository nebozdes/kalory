package com.matveev.kalory.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.matveev.kalory.model.id.UserId;
import com.matveev.kalory.model.id.WeightCheckId;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Builder
@ToString
@Validated
@EqualsAndHashCode
public class WeightCheck {

    @NotNull
    private final WeightCheckId id;

    @JsonIgnore
    @NotNull
    private final UserId userId;

    @NotNull
    @Positive
    private final BigDecimal value;

    @NotNull
    private final LocalDate checkTime;
}
