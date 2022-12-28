package com.matveev.kalory.command;

import com.matveev.kalory.model.ConsumedProduct;
import com.matveev.kalory.model.id.ConsumedProductId;
import com.matveev.kalory.model.request.create.CreateConsumedProduct;

public interface ConsumedProductCommands {
    ConsumedProduct create(CreateConsumedProduct createConsumedProduct);

    void delete(ConsumedProductId productId);
}
