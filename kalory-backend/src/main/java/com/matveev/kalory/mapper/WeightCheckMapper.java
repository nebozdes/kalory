package com.matveev.kalory.mapper;

import com.matveev.kalory.domain.entity.WeightCheckEntity;
import com.matveev.kalory.model.WeightCheck;
import lombok.experimental.UtilityClass;

import static com.matveev.kalory.model.id.UserId.userId;
import static com.matveev.kalory.model.id.WeightCheckId.weightCheckId;

@UtilityClass
public class WeightCheckMapper {

    public static WeightCheck map(WeightCheckEntity entity) {
        return WeightCheck.builder()
                .id(weightCheckId(entity.getId()))
                .value(entity.getValue())
                .checkTime(entity.getCheckTime())
                .userId(userId(entity.getUserId()))
                .build();
    }
}
