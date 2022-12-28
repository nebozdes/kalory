package com.matveev.kalory.mapper;

import com.matveev.kalory.domain.entity.WeightTargetEntity;
import com.matveev.kalory.model.WeightTarget;
import lombok.experimental.UtilityClass;

import java.util.Optional;

import static com.matveev.kalory.model.id.UserId.userId;
import static com.matveev.kalory.model.id.WeightTargetId.weightTargetId;

@UtilityClass
public class WeightTargetMapper {

    public static WeightTarget map(WeightTargetEntity entity) {
        return WeightTarget.builder()
                .id(weightTargetId(entity.getId()))
                .value(entity.getValue())
                .deadline(Optional.ofNullable(entity.getDeadline()))
                .userId(userId(entity.getUserId()))
                .build();
    }
}
