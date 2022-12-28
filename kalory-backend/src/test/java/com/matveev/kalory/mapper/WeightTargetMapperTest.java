package com.matveev.kalory.mapper;

import com.matveev.kalory.WeightTargetTestData;
import org.junit.jupiter.api.Test;

import static com.matveev.kalory.mapper.WeightTargetMapper.map;
import static com.matveev.kalory.model.id.UserId.userId;
import static com.matveev.kalory.model.id.WeightTargetId.weightTargetId;
import static org.assertj.core.api.Assertions.assertThat;

public class WeightTargetMapperTest implements WeightTargetTestData {

    @Test
    public void should_map_all_the_fields_from_entity_to_dto () {
        // given
        final var input = aWeightTargetEntity().id(1L).userId(123L).build();
        final var output = aWeightTarget().id(weightTargetId(1L)).userId(userId(123L)).build();

        // when
        final var result = map(input);

        // then
        assertThat(output).isEqualTo(result);
    }
}
