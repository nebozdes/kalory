package com.matveev.kalory.query;

import com.matveev.kalory.model.ConsumedProduct;
import com.matveev.kalory.model.id.ConsumedProductId;
import com.matveev.kalory.model.request.list.ListConsumedProducts;
import org.springframework.data.domain.Page;

public interface ConsumedProductQueries {

    Page<ConsumedProduct> list(ListConsumedProducts listProducts);

    ConsumedProduct getById(ConsumedProductId id);
}

