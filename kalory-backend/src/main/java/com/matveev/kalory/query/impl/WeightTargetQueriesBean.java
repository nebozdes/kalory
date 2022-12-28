package com.matveev.kalory.query.impl;

import com.matveev.kalory.domain.repository.WeightTargetRepository;
import com.matveev.kalory.mapper.WeightTargetMapper;
import com.matveev.kalory.model.WeightTarget;
import com.matveev.kalory.model.id.WeightTargetId;
import com.matveev.kalory.model.request.list.ListWeightTargets;
import com.matveev.kalory.query.WeightTargetQueries;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import static com.matveev.kalory.mapper.WeightTargetMapper.map;

@Service
@RequiredArgsConstructor
public class WeightTargetQueriesBean implements WeightTargetQueries {

    private final WeightTargetRepository weightTargetRepository;

    @Override
    public Page<WeightTarget> list(ListWeightTargets listWeightTargets) {
        final var pageObject = PageRequest.of(listWeightTargets.page, listWeightTargets.limit);
        return weightTargetRepository.findAll(pageObject).map(WeightTargetMapper::map);
    }

    @Override
    public WeightTarget getById(WeightTargetId id) {
        return map(weightTargetRepository.getById(id.value()));
    }
}
