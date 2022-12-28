package com.matveev.kalory.command;

import com.matveev.kalory.model.WeightTarget;
import com.matveev.kalory.model.id.WeightTargetId;
import com.matveev.kalory.model.request.create.CreateWeightTarget;

public interface WeightTargetCommands {
    WeightTarget create(CreateWeightTarget createWeightTarget);

    void delete(WeightTargetId weightTargetId);
}
