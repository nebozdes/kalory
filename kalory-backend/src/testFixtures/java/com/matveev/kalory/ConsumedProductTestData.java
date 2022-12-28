package com.matveev.kalory;

import com.matveev.kalory.domain.entity.ConsumedProductEntity;
import com.matveev.kalory.model.ConsumedProduct;
import com.matveev.kalory.model.id.ProductId;

import static com.matveev.kalory.RandomUtils.aRandomId;
import static com.matveev.kalory.model.id.ConsumedProductId.consumedProductId;
import static com.matveev.kalory.model.id.UserId.userId;
import static java.math.BigDecimal.valueOf;
import static java.time.LocalDate.now;
import static java.util.Optional.of;

public interface ConsumedProductTestData {

    default ConsumedProduct.ConsumedProductBuilder aConsumedProduct(ProductId productId) {
        return ConsumedProduct.builder()
                .id(consumedProductId(aRandomId()))
                .userId(userId(aRandomId()))
                .comment(of("Some comment"))
                .productId(productId)
                .calculatedProteins(valueOf(10))
                .calculatedCarbs(valueOf(20))
                .calculatedLipids(valueOf(30))
                .calculatedCalories(valueOf(500))
                .consumptionAmount(valueOf(1000))
                .consumptionDate(now());
    }

    default ConsumedProductEntity.ConsumedProductEntityBuilder aConsumedProductEntity(Long productId) {
        return ConsumedProductEntity.builder()
                .id(aRandomId())
                .userId(aRandomId())
                .comment("Some comment")
                .productId(productId)
                .calculatedProteins(valueOf(10))
                .calculatedCarbs(valueOf(20))
                .calculatedLipids(valueOf(30))
                .calculatedCalories(valueOf(500))
                .consumptionAmount(valueOf(1000))
                .consumptionDate(now());
    }
}
