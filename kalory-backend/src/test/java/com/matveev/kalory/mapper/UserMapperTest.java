package com.matveev.kalory.mapper;

import com.matveev.kalory.UserTestData;
import org.junit.jupiter.api.Test;

import static com.matveev.kalory.mapper.UserMapper.map;
import static com.matveev.kalory.model.id.UserId.userId;
import static org.assertj.core.api.Assertions.assertThat;

public class UserMapperTest implements UserTestData {

    @Test
    public void should_map_all_the_fields_from_entity_to_dto () {
        // given
        final var input = anUserEntity("client").id(123L).build();
        final var output = anUser("client").id(userId(123L)).build();

        // when
        final var result = map(input);

        // then
        assertThat(output).isEqualTo(result);
    }
}
