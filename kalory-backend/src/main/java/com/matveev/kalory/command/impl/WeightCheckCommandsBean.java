package com.matveev.kalory.command.impl;

import com.matveev.kalory.command.WeightCheckCommands;
import com.matveev.kalory.domain.entity.WeightCheckEntity;
import com.matveev.kalory.domain.repository.WeightCheckRepository;
import com.matveev.kalory.mapper.WeightCheckMapper;
import com.matveev.kalory.model.WeightCheck;
import com.matveev.kalory.model.id.WeightCheckId;
import com.matveev.kalory.model.request.create.CreateWeightCheck;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import static java.util.Objects.requireNonNull;

@Service
@RequiredArgsConstructor
public class WeightCheckCommandsBean implements WeightCheckCommands {

    private final WeightCheckRepository weightCheckRepository;

    @Override
    public WeightCheck create(@Valid @NotNull CreateWeightCheck createWeightCheck) {
        requireNonNull(createWeightCheck.getUserId(), "userId should not be null!");

        final var entity = new WeightCheckEntity();

        entity.setCheckTime(createWeightCheck.getCheckTime());
        entity.setValue(createWeightCheck.getValue());
        entity.setUserId(createWeightCheck.getUserId().value());

        return WeightCheckMapper.map(weightCheckRepository.save(entity));
    }

    @Override
    public void delete(@Valid @NotNull WeightCheckId weightCheckId) {
        weightCheckRepository.deleteById(weightCheckId.value());
    }
}
