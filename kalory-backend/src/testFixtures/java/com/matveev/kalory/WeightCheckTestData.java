package com.matveev.kalory;

import com.matveev.kalory.domain.entity.WeightCheckEntity;
import com.matveev.kalory.model.WeightCheck;

import static com.matveev.kalory.RandomUtils.aRandomId;
import static com.matveev.kalory.model.id.UserId.userId;
import static com.matveev.kalory.model.id.WeightCheckId.weightCheckId;
import static java.math.BigDecimal.valueOf;
import static java.time.Instant.now;

public interface WeightCheckTestData {

    default WeightCheck.WeightCheckBuilder aWeightCheck() {
        return WeightCheck.builder()
                .id(weightCheckId(aRandomId()))
                .checkTime(now())
                .value(valueOf(65))
                .userId(userId(aRandomId()));
    }

    default WeightCheckEntity.WeightCheckEntityBuilder aWeightCheckEntity() {
        return WeightCheckEntity.builder()
                .id(aRandomId())
                .checkTime(now())
                .value(valueOf(65))
                .userId(aRandomId());
    }
}
