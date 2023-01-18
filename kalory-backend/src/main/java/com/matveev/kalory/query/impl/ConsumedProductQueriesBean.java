package com.matveev.kalory.query.impl;

import com.matveev.kalory.domain.repository.ConsumedProductRepository;
import com.matveev.kalory.domain.repository.ProductRepository;
import com.matveev.kalory.model.ConsumedProduct;
import com.matveev.kalory.model.id.ConsumedProductId;
import com.matveev.kalory.model.request.list.ListConsumedProducts;
import com.matveev.kalory.query.ConsumedProductQueries;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import static com.matveev.kalory.mapper.ConsumedProductMapper.map;
import static org.springframework.data.domain.PageRequest.of;
import static org.springframework.data.domain.Pageable.unpaged;

@Service
@RequiredArgsConstructor
public class ConsumedProductQueriesBean implements ConsumedProductQueries {

    private final ConsumedProductRepository consumedProductRepository;
    private final ProductRepository productRepository;

    @Override
    public Page<ConsumedProduct> list(ListConsumedProducts listProducts) {
        final var pageObject = of(listProducts.page, listProducts.limit);
        return listProducts.consumptionDate
                .map(date -> consumedProductRepository.findAllByConsumptionDate(date, unpaged()))
                .orElseGet(() -> consumedProductRepository.findAll(pageObject))
                .map(consumedProductEntity -> {
                    final var product = productRepository.getById(consumedProductEntity.getProductId());
                    return map(consumedProductEntity, product);
                });
    }

    @Override
    public ConsumedProduct getById(ConsumedProductId id) {
        final var entity = consumedProductRepository.getById(id.value());
        final var product = productRepository.getById(entity.getProductId());
        return map(entity, product);
    }
}
