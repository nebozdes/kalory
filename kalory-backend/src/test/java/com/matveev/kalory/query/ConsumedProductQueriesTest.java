package com.matveev.kalory.query;

import com.matveev.kalory.ConsumedProductTestData;
import com.matveev.kalory.domain.repository.ConsumedProductRepository;
import com.matveev.kalory.error.EntityNotFoundException;
import com.matveev.kalory.model.request.list.ListConsumedProducts;
import com.matveev.kalory.query.impl.ConsumedProductQueriesBean;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.util.List;

import static com.matveev.kalory.model.id.ConsumedProductId.consumedProductId;
import static com.matveev.kalory.model.id.ProductId.productId;
import static com.matveev.kalory.model.id.UserId.userId;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ConsumedProductQueriesTest implements ConsumedProductTestData {

    private final ConsumedProductRepository repository = mock(ConsumedProductRepository.class);
    private final ConsumedProductQueries queries = new ConsumedProductQueriesBean(repository);

    @Test
    public void should_return_product_if_found_by_id() {
        // given
        final var id = 123L;
        final var entity = aConsumedProductEntity(1L).id(id).userId(3L).build();
        final var expectedResult = aConsumedProduct(productId(1L))
                .id(consumedProductId(id))
                .userId(userId(3L))
                .build();
        when(repository.getById(id)).thenReturn(entity);

        // when
        final var result = queries.getById(consumedProductId(id));

        // then
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    public void should_throw_exception_if_not_found_by_id() {
        // given
        final var id = 123L;
        when(repository.getById(id)).thenThrow(new EntityNotFoundException(id));

        // when/then
        assertThatThrownBy(() -> queries.getById(consumedProductId(id)))
                .isInstanceOf(EntityNotFoundException.class)
                .hasMessage("Entity with ID=123 not found");
    }

    @Test
    public void should_return_empty_page_if_no_product_found_for_list() {
        // given;
        final var pageObject = PageRequest.of(1, 10);
        when(repository.findAll(pageObject)).thenReturn(Page.empty());

        // when
        final var result = queries.list(new ListConsumedProducts(1, 10));

        // then
        assertThat(result).isEqualTo(Page.empty());
    }


    @Test
    public void should_return_mapped_dtos_if_any_products_found_for_list() {
        // given;
        final var pageObject = PageRequest.of(1, 10);
        final var input = new PageImpl<>(
                List.of(
                        aConsumedProductEntity(4L).id(1L).userId(3L).build(),
                        aConsumedProductEntity(4L).id(2L).userId(3L).build(),
                        aConsumedProductEntity(4L).id(3L).userId(3L).build()
                ),
                pageObject,
                3
        );
        final var expectedResult = new PageImpl<>(
                List.of(
                        aConsumedProduct(productId(4L)).id(consumedProductId(1L)).userId(userId(3L)).build(),
                        aConsumedProduct(productId(4L)).id(consumedProductId(2L)).userId(userId(3L)).build(),
                        aConsumedProduct(productId(4L)).id(consumedProductId(3L)).userId(userId(3L)).build()
                        ),
                pageObject,
                3
        );

        when(repository.findAll(pageObject)).thenReturn(input);

        // when
        final var result = queries.list(new ListConsumedProducts(1, 10));

        // then
        assertThat(result.getTotalElements()).isEqualTo(expectedResult.getTotalElements());
        assertThat(result.getPageable()).isEqualTo(expectedResult.getPageable());
        assertThat(result.getContent().size()).isEqualTo(expectedResult.getContent().size());

        for (int i = 0; i < result.getContent().size(); i++) {
            assertThat(result.getContent().get(i)).isEqualTo(expectedResult.getContent().get(i));
        }
    }
}
