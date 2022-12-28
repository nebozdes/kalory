package com.matveev.kalory.command.impl;

import com.matveev.kalory.command.WeightTargetCommands;
import com.matveev.kalory.domain.entity.WeightTargetEntity;
import com.matveev.kalory.domain.repository.WeightTargetRepository;
import com.matveev.kalory.model.WeightTarget;
import com.matveev.kalory.model.id.WeightTargetId;
import com.matveev.kalory.model.request.create.CreateWeightTarget;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import static com.matveev.kalory.mapper.WeightTargetMapper.map;
import static java.util.Objects.requireNonNull;

@Service
@RequiredArgsConstructor
public class WeightTargetCommandsBean implements WeightTargetCommands {

    private final WeightTargetRepository weightTargetRepository;

    @Override
    public WeightTarget create(@Valid @NotNull CreateWeightTarget createWeightTarget) {
        requireNonNull(createWeightTarget.getUserId(), "userId should not be null!");

        final var entity = new WeightTargetEntity();

        entity.setDeadline(createWeightTarget.getDeadline().orElse(null));
        entity.setValue(createWeightTarget.getValue());
        entity.setUserId(createWeightTarget.getUserId().value());

        return map(weightTargetRepository.save(entity));
    }

    @Override
    public void delete(@Valid @NotNull WeightTargetId weightTargetId) {
        weightTargetRepository.deleteById(weightTargetId.value());
    }
}
