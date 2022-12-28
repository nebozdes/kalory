package com.matveev.kalory.command.impl;

import com.matveev.kalory.command.ConsumedProductCommands;
import com.matveev.kalory.domain.entity.ConsumedProductEntity;
import com.matveev.kalory.domain.repository.ConsumedProductRepository;
import com.matveev.kalory.model.ConsumedProduct;
import com.matveev.kalory.model.id.ConsumedProductId;
import com.matveev.kalory.model.request.create.CreateConsumedProduct;
import com.matveev.kalory.query.ProductQueries;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import static com.matveev.kalory.mapper.ConsumedProductMapper.map;
import static java.math.RoundingMode.CEILING;
import static java.util.Objects.requireNonNull;

@Service
@RequiredArgsConstructor
public class ConsumedProductCommandsBean implements ConsumedProductCommands {

    private final ConsumedProductRepository consumedProductRepository;
    private final ProductQueries productQueries;

    @Override
    public ConsumedProduct create(@Valid @NotNull CreateConsumedProduct createConsumedProduct) {
        requireNonNull(createConsumedProduct.getUserId(), "userId should not be null!");

        final var entity = new ConsumedProductEntity();

        entity.setComment(createConsumedProduct.getComment().orElse(null));
        entity.setConsumptionDate(createConsumedProduct.getConsumptionDate());
        entity.setConsumptionAmount(createConsumedProduct.getConsumptionAmount());
        entity.setUserId(createConsumedProduct.getUserId().value());

        final var product = productQueries.getById(createConsumedProduct.getProductId());

        final var amount = createConsumedProduct.getConsumptionAmount();
        final var amountFracture = amount.divide(product.getBaseAmount(), 4, CEILING);

        entity.setCalculatedCalories(amountFracture.multiply(product.getBaseCalories()));
        entity.setCalculatedCarbs(amountFracture.multiply(product.getBaseCarbs()));
        entity.setCalculatedLipids(amountFracture.multiply(product.getBaseLipids()));
        entity.setCalculatedProteins(amountFracture.multiply(product.getBaseProteins()));
        entity.setProductId(product.getId().value());

        return map(consumedProductRepository.save(entity));
    }

    @Override
    public void delete(@Valid @NotNull ConsumedProductId productId) {
        consumedProductRepository.deleteById(productId.value());
    }
}
