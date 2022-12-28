package com.matveev.kalory.command;

import com.matveev.kalory.ProductTestData;
import com.matveev.kalory.command.impl.ProductCommandsBean;
import com.matveev.kalory.domain.entity.ProductEntity;
import com.matveev.kalory.domain.repository.ProductRepository;
import com.matveev.kalory.model.request.create.CreateProduct;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import static com.matveev.kalory.domain.entity.AmountType.GRAM;
import static com.matveev.kalory.model.id.ProductId.productId;
import static java.math.BigDecimal.valueOf;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ProductCommandsTest implements ProductTestData {

    private final ProductRepository repository = mock(ProductRepository.class);
    private final ProductCommandsBean commands = new ProductCommandsBean(repository);

    @Test
    public void should_call_repository_method_for_create() {
        // given
        final var id = 123L;
        final var expectedResult = aProduct().id(productId(id)).name("Some name").build();

        when(repository.save(any())).thenAnswer(invocation -> {
            final var firstParam = (ProductEntity) invocation.getArguments()[0];
            firstParam.setId(id);
            return firstParam;
        });

        // when
        final var result = commands.create(createRequest());

        // then
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    public void should_call_repository_method_for_delete() {
        // given
        final var id = 123L;
        doNothing().when(repository).delete(any());

        // when
        commands.delete(productId(id));

        // then
        verify(repository, times(1)).deleteById(id);
        verifyNoMoreInteractions(repository);
    }

    private CreateProduct createRequest() {
        final var request = new CreateProduct();
        request.setName("Some name");
        request.setBaseAmount(valueOf(100));
        request.setBaseAmountType(GRAM);
        request.setBaseCalories(valueOf(100));
        request.setBaseProteins(valueOf(1));
        request.setBaseCarbs(valueOf(2));
        request.setBaseLipids(valueOf(3));
        return request;
    }
}
