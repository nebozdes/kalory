package com.matveev.kalory.model.request.create;

import com.matveev.kalory.model.id.UserId;
import lombok.Getter;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@Validated
public class CreateWeightCheck {

    private UserId userId;

    @NotNull
    @Positive
    private BigDecimal value;

    @NotNull
    private LocalDate checkTime;
    public CreateWeightCheck withUserId(UserId userId) {
        this.userId = userId;
        return this;
    }
}
