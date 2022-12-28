package com.matveev.kalory.model.request.create;

import com.matveev.kalory.model.id.ProductId;
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
public class CreateConsumedProduct {

    @NotNull
    private ProductId productId;
    private UserId userId;

    @NotNull
    private LocalDate consumptionDate;

    @NotNull
    @Positive
    private BigDecimal consumptionAmount;
    private Optional<String> comment = empty();

    public CreateConsumedProduct withUserId(UserId userId) {
        this.userId = userId;
        return this;
    }
}
