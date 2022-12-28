package com.matveev.kalory.command.impl;

import com.matveev.kalory.command.ProductCommands;
import com.matveev.kalory.domain.entity.ProductEntity;
import com.matveev.kalory.domain.repository.ProductRepository;
import com.matveev.kalory.model.Product;
import com.matveev.kalory.model.id.ProductId;
import com.matveev.kalory.model.request.create.CreateProduct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import static com.matveev.kalory.mapper.ProductMapper.map;

@Service
@RequiredArgsConstructor
public class ProductCommandsBean implements ProductCommands {

    private final ProductRepository productRepository;

    @Override
    public Product create(@Valid @NotNull CreateProduct createProductRequest) {
        final var entity = new ProductEntity();

        entity.setName(createProductRequest.getName());
        entity.setBaseAmount(createProductRequest.getBaseAmount());
        entity.setBaseAmountType(createProductRequest.getBaseAmountType());
        entity.setBaseLipids(createProductRequest.getBaseLipids());
        entity.setBaseCalories(createProductRequest.getBaseCalories());
        entity.setBaseCarbs(createProductRequest.getBaseCarbs());
        entity.setBaseProteins(createProductRequest.getBaseProteins());

        return map(productRepository.save(entity));
    }

    @Override
    public void delete(@Valid @NotNull ProductId productId) {
        productRepository.deleteById(productId.value());
    }
}
