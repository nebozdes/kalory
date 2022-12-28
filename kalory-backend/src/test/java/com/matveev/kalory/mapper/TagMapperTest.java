package com.matveev.kalory.mapper;

import com.matveev.kalory.TagTestData;
import org.junit.jupiter.api.Test;

import static com.matveev.kalory.mapper.TagMapper.map;
import static com.matveev.kalory.model.id.TagId.tagId;
import static org.assertj.core.api.Assertions.assertThat;

public class TagMapperTest implements TagTestData {

    @Test
    public void should_map_all_the_fields_from_entity_to_dto () {
        // given
        final var input = aTagEntity().name("Some name").id(1L).build();
        final var output = aTag().id(tagId(1L)).name("Some name").build();

        // when
        final var result = map(input);

        // then
        assertThat(output).isEqualTo(result);
    }
}
