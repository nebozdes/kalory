package com.matveev.kalory.query;

import com.matveev.kalory.model.WeightCheck;
import com.matveev.kalory.model.id.WeightCheckId;
import com.matveev.kalory.model.request.list.ListWeightChecks;
import org.springframework.data.domain.Page;

public interface WeightCheckQueries {

    Page<WeightCheck> list(ListWeightChecks listWeightChecks);

    WeightCheck getById(WeightCheckId id);
}

