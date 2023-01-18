package com.matveev.kalory.mapper;

import com.matveev.kalory.WeightCheckTestData;
import org.junit.jupiter.api.Test;

import static com.matveev.kalory.mapper.WeightCheckMapper.map;
import static com.matveev.kalory.model.id.UserId.userId;
import static com.matveev.kalory.model.id.WeightCheckId.weightCheckId;
import static java.time.LocalDate.now;
import static org.assertj.core.api.Assertions.assertThat;

public class WeightCheckMapperTest implements WeightCheckTestData {

    @Test
    public void should_map_all_the_fields_from_entity_to_dto () {
        // given
        final var now = now();
        final var input = aWeightCheckEntity()
                .id(1L)
                .userId(123L)
                .checkTime(now).build();
        final var output = aWeightCheck()
                .id(weightCheckId(1L))
                .userId(userId(123L))
                .checkTime(now)
                .build();

        // when
        final var result = map(input);

        // then
        assertThat(output).isEqualTo(result);
    }
}
