package com.matveev.kalory.mapper;

import com.matveev.kalory.ConsumedProductTestData;
import org.junit.jupiter.api.Test;

import static com.matveev.kalory.mapper.ConsumedProductMapper.map;
import static com.matveev.kalory.model.id.ConsumedProductId.consumedProductId;
import static com.matveev.kalory.model.id.ProductId.productId;
import static com.matveev.kalory.model.id.UserId.userId;
import static org.assertj.core.api.Assertions.assertThat;

public class ConsumedProductMapperTest implements ConsumedProductTestData {

    @Test
    public void should_map_all_the_fields_from_entity_to_dto() {
        // given
        final var input = aConsumedProductEntity(1L).id(2L)
                .userId(3L).build();
        final var output = aConsumedProduct(productId(1L))
                .id(consumedProductId(2L)).userId(userId(3L)).build();

        // when
        final var result = map(input);

        // then
        assertThat(output).isEqualTo(result);
    }
}
