package com.matveev.kalory.query;

import com.matveev.kalory.model.Product;
import com.matveev.kalory.model.id.ProductId;
import com.matveev.kalory.model.request.list.ListProducts;
import org.springframework.data.domain.Page;

public interface ProductQueries {

    Page<Product> list(ListProducts listProducts);

    Product getById(ProductId id);
}

