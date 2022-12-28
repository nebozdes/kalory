package com.matveev.kalory;

import com.matveev.kalory.domain.entity.WeightTargetEntity;
import com.matveev.kalory.model.WeightTarget;

import static com.matveev.kalory.RandomUtils.aRandomId;
import static com.matveev.kalory.model.id.UserId.userId;
import static com.matveev.kalory.model.id.WeightTargetId.weightTargetId;
import static java.math.BigDecimal.valueOf;
import static java.time.LocalDate.now;
import static java.util.Optional.of;

public interface WeightTargetTestData {

    default WeightTarget.WeightTargetBuilder aWeightTarget() {
        return WeightTarget.builder()
                .id(weightTargetId(aRandomId()))
                .userId(userId(aRandomId()))
                .value(valueOf(60))
                .deadline(of(now().plusDays(90)));
    }

    default WeightTargetEntity.WeightTargetEntityBuilder aWeightTargetEntity() {
        return WeightTargetEntity.builder()
                .id(aRandomId())
                .userId(aRandomId())
                .value(valueOf(60))
                .deadline(now().plusDays(90));
    }
}
