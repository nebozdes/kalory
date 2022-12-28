package com.matveev.kalory.query;

import com.matveev.kalory.ProductTestData;
import com.matveev.kalory.domain.repository.ProductRepository;
import com.matveev.kalory.error.EntityNotFoundException;
import com.matveev.kalory.model.request.list.ListProducts;
import com.matveev.kalory.query.impl.ProductQueriesBean;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.util.List;

import static com.matveev.kalory.model.id.ProductId.productId;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ProductQueriesTest implements ProductTestData {

    private final ProductRepository repository = mock(ProductRepository.class);
    private final ProductQueries queries = new ProductQueriesBean(repository);

    @Test
    public void should_return_product_if_found_by_id() {
        // given
        final var id = 123L;
        final var entity = aProductEntity().id(id).name("Some product").build();
        final var expectedResult = aProduct().id(productId(id)).name("Some product").build();
        when(repository.getById(id)).thenReturn(entity);

        // when
        final var result = queries.getById(productId(id));

        // then
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    public void should_throw_exception_if_not_found_by_id() {
        // given
        final var id = 123L;
        when(repository.getById(id)).thenThrow(new EntityNotFoundException(id));

        // when/then
        assertThatThrownBy(() -> queries.getById(productId(id)))
                .isInstanceOf(EntityNotFoundException.class)
                .hasMessage("Entity with ID=123 not found");
    }

    @Test
    public void should_return_empty_page_if_no_product_found_for_list() {
        // given;
        final var pageObject = PageRequest.of(1, 10);
        when(repository.findAll(pageObject)).thenReturn(Page.empty());

        // when
        final var result = queries.list(new ListProducts(1, 10));

        // then
        assertThat(result).isEqualTo(Page.empty());
    }


    @Test
    public void should_return_mapped_dtos_if_any_products_found_for_list() {
        // given;
        final var pageObject = PageRequest.of(1, 10);
        final var input = new PageImpl<>(
                List.of(
                        aProductEntity().id(1L).name("Product 1").build(),
                        aProductEntity().id(2L).name("Product 2").build(),
                        aProductEntity().id(3L).name("Product 3").build()
                ),
                pageObject,
                3
        );
        final var expectedResult = new PageImpl<>(
                List.of(
                        aProduct().id(productId(1L)).name("Product 1").build(),
                        aProduct().id(productId(2L)).name("Product 2").build(),
                        aProduct().id(productId(3L)).name("Product 3").build()
                ),
                pageObject,
                3
        );

        when(repository.findAll(pageObject)).thenReturn(input);

        // when
        final var result = queries.list(new ListProducts(1, 10));

        // then
        assertThat(result.getTotalElements()).isEqualTo(expectedResult.getTotalElements());
        assertThat(result.getPageable()).isEqualTo(expectedResult.getPageable());
        assertThat(result.getContent().size()).isEqualTo(expectedResult.getContent().size());

        for (int i = 0; i < result.getContent().size(); i++) {
            assertThat(result.getContent().get(i)).isEqualTo(expectedResult.getContent().get(i));
        }
    }
}
