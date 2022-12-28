package com.matveev.kalory.mapper;

import com.matveev.kalory.ProductTestData;
import org.junit.jupiter.api.Test;

import static com.matveev.kalory.mapper.ProductMapper.map;
import static com.matveev.kalory.model.id.ProductId.productId;
import static org.assertj.core.api.Assertions.assertThat;

public class ProductMapperTest implements ProductTestData {

    @Test
    public void should_map_all_the_fields_from_entity_to_dto() {
        // given
        final var input = aProductEntity().id(1L).name("Some product").build();
        final var output = aProduct().id(productId(1L)).name("Some product").build();

        // when
        final var result = map(input);

        // then
        assertThat(output).isEqualTo(result);
    }
}
