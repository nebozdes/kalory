package com.matveev.kalory.query.impl;

import com.matveev.kalory.domain.repository.ConsumedProductRepository;
import com.matveev.kalory.mapper.ConsumedProductMapper;
import com.matveev.kalory.model.ConsumedProduct;
import com.matveev.kalory.model.id.ConsumedProductId;
import com.matveev.kalory.model.request.list.ListConsumedProducts;
import com.matveev.kalory.query.ConsumedProductQueries;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import static com.matveev.kalory.mapper.ConsumedProductMapper.map;
import static org.springframework.data.domain.PageRequest.of;

@Service
@RequiredArgsConstructor
public class ConsumedProductQueriesBean implements ConsumedProductQueries {

    private final ConsumedProductRepository consumedProductRepository;

    @Override
    public Page<ConsumedProduct> list(ListConsumedProducts listProducts) {
        final var pageObject = of(listProducts.page, listProducts.limit);
        return consumedProductRepository.findAll(pageObject).map(ConsumedProductMapper::map);
    }

    @Override
    public ConsumedProduct getById(ConsumedProductId id) {
        return map(consumedProductRepository.getById(id.value()));
    }
}
