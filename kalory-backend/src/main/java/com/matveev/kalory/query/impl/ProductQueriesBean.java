package com.matveev.kalory.query.impl;

import com.matveev.kalory.domain.repository.ProductRepository;
import com.matveev.kalory.mapper.ProductMapper;
import com.matveev.kalory.model.Product;
import com.matveev.kalory.model.id.ProductId;
import com.matveev.kalory.model.request.list.ListProducts;
import com.matveev.kalory.query.ProductQueries;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import static com.matveev.kalory.mapper.ProductMapper.map;
import static org.springframework.data.domain.PageRequest.of;

@Service
@RequiredArgsConstructor
public class ProductQueriesBean implements ProductQueries {

    private final ProductRepository productRepository;

    @Override
    public Page<Product> list(ListProducts listProducts) {
        final var pageObject = of(listProducts.page, listProducts.limit);
        return productRepository.findAll(pageObject).map(ProductMapper::map);
    }

    @Override
    public Product getById(ProductId id) {
        return map(productRepository.getById(id.value()));
    }
}
