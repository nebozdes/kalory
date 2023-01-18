package com.matveev.kalory.command;

import com.matveev.kalory.ConsumedProductTestData;
import com.matveev.kalory.ProductTestData;
import com.matveev.kalory.command.impl.ConsumedProductCommandsBean;
import com.matveev.kalory.domain.entity.ConsumedProductEntity;
import com.matveev.kalory.domain.repository.ConsumedProductRepository;
import com.matveev.kalory.domain.repository.ProductRepository;
import com.matveev.kalory.model.request.create.CreateConsumedProduct;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.util.Optional;

import static com.matveev.kalory.model.id.ConsumedProductId.consumedProductId;
import static com.matveev.kalory.model.id.ProductId.productId;
import static com.matveev.kalory.model.id.UserId.userId;
import static java.math.BigDecimal.valueOf;
import static java.time.LocalDate.now;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ConsumedProductCommandsTest implements ConsumedProductTestData, ProductTestData {

    private final ConsumedProductRepository repository = mock(ConsumedProductRepository.class);
    private final ProductRepository productRepository = mock(ProductRepository.class);
    private final ConsumedProductCommands commands = new ConsumedProductCommandsBean(repository, productRepository);

    private final Long productId = 321L;

    @Test
    public void should_call_repository_method_for_create() {
        // given
        final var id = 123L;
        final var productEntity = aProductEntity()
                .id(productId)
                .baseProteins(valueOf(1))
                .baseCarbs(valueOf(2))
                .baseLipids(valueOf(3))
                .baseCalories(valueOf(100))
                .build();
        final var product = aProduct()
                .id(productId(productId))
                .baseProteins(valueOf(1))
                .baseCarbs(valueOf(2))
                .baseLipids(valueOf(3))
                .baseCalories(valueOf(100))
                .build();


        final var expectedResult = aConsumedProduct(product)
                .id(consumedProductId(id))
                .userId(userId(123L))
                .productId(product.getId())
                .consumptionAmount(valueOf(500.0D))
                .calculatedProteins(valueOf(5.0D))
                .calculatedCarbs(valueOf(10.0D))
                .calculatedLipids(valueOf(15.0D))
                .calculatedCalories(valueOf(500))
                .build();

        when(productRepository.getById(product.getId().value())).thenReturn(productEntity);
        when(repository.save(any())).thenAnswer(invocation -> {
            final var firstParam = (ConsumedProductEntity) invocation.getArguments()[0];
            firstParam.setId(id);
            return firstParam;
        });

        // when
        final var result = commands.create(createRequest());

        // then
        assertThat(result.getCalculatedCarbs()).usingComparator(BigDecimal::compareTo).isEqualTo(expectedResult.getCalculatedCarbs());
        assertThat(result.getCalculatedProteins()).usingComparator(BigDecimal::compareTo).isEqualTo(expectedResult.getCalculatedProteins());
        assertThat(result.getCalculatedCalories()).usingComparator(BigDecimal::compareTo).isEqualTo(expectedResult.getCalculatedCalories());
        assertThat(result.getCalculatedProteins()).usingComparator(BigDecimal::compareTo).isEqualTo(expectedResult.getCalculatedProteins());
    }

    @Test
    public void should_call_repository_method_for_delete() {
        // given
        final var id = 123L;
        doNothing().when(repository).delete(any());

        // when
        commands.delete(consumedProductId(id));

        // then
        verify(repository, times(1)).deleteById(id);
        verifyNoMoreInteractions(repository);
    }

    private CreateConsumedProduct createRequest() {
        final var request = new CreateConsumedProduct();
        request.setUserId(userId(123L));
        request.setConsumptionAmount(valueOf(500));
        request.setConsumptionDate(now());
        request.setProductId(productId(productId));
        request.setComment(Optional.of("Some comment"));
        return request;
    }
}
