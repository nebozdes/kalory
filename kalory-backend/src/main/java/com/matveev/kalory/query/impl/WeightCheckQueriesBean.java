package com.matveev.kalory.query.impl;

import com.matveev.kalory.domain.repository.WeightCheckRepository;
import com.matveev.kalory.mapper.WeightCheckMapper;
import com.matveev.kalory.model.WeightCheck;
import com.matveev.kalory.model.id.WeightCheckId;
import com.matveev.kalory.model.request.list.ListWeightChecks;
import com.matveev.kalory.query.WeightCheckQueries;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import static com.matveev.kalory.mapper.WeightCheckMapper.map;
import static org.springframework.data.domain.PageRequest.of;

@Service
@RequiredArgsConstructor
public class WeightCheckQueriesBean implements WeightCheckQueries {

    private final WeightCheckRepository weightCheckRepository;

    @Override
    public Page<WeightCheck> list(ListWeightChecks listWeightChecks) {
        final var pageObject = of(listWeightChecks.page, listWeightChecks.limit);
        return weightCheckRepository.findAll(pageObject).map(WeightCheckMapper::map);
    }

    @Override
    public WeightCheck getById(WeightCheckId id) {
        return map(weightCheckRepository.getById(id.value()));
    }
}
