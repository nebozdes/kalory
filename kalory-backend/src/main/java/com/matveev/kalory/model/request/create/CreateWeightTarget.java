package com.matveev.kalory.model.request.create;

import com.matveev.kalory.model.id.UserId;
import lombok.Getter;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

import static java.util.Optional.empty;

@Getter
@Setter
@Validated
public class CreateWeightTarget {
    private UserId userId;

    @NotNull
    @Positive
    private BigDecimal value;

    private Optional<LocalDate> deadline = empty();

    public CreateWeightTarget withUserId(UserId userId) {
        this.userId = userId;
        return this;
    }
}
