package com.matveev.kalory.command;

import com.matveev.kalory.model.Product;
import com.matveev.kalory.model.id.ProductId;
import com.matveev.kalory.model.request.create.CreateProduct;

public interface ProductCommands {

    Product create(CreateProduct createProductRequest);

    void delete(ProductId productId);
}
