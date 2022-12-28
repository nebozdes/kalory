package com.matveev.kalory.model;

import com.matveev.kalory.model.id.UserId;
import com.matveev.kalory.model.id.WeightTargetId;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

@Getter
@Builder
@ToString
@Validated
@EqualsAndHashCode
public class WeightTarget {

    @NotNull
    private WeightTargetId id;

    @NotNull
    private UserId userId;

    @NotNull
    private BigDecimal value;
    private Optional<LocalDate> deadline;
}
