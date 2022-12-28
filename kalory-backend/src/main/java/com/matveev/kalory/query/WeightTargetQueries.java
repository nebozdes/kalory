package com.matveev.kalory.query;

import com.matveev.kalory.model.WeightTarget;
import com.matveev.kalory.model.id.WeightTargetId;
import com.matveev.kalory.model.request.list.ListWeightTargets;
import org.springframework.data.domain.Page;

public interface WeightTargetQueries {

    Page<WeightTarget> list(ListWeightTargets listWeightTargets);

    WeightTarget getById(WeightTargetId id);
}

