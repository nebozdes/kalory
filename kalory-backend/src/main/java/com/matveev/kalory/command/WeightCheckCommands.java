package com.matveev.kalory.command;

import com.matveev.kalory.model.WeightCheck;
import com.matveev.kalory.model.id.WeightCheckId;
import com.matveev.kalory.model.request.create.CreateWeightCheck;

public interface WeightCheckCommands {
    WeightCheck create(CreateWeightCheck createWeightCheck);

    void delete(WeightCheckId weightCheckId);
}
