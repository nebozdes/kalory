package com.matveev.kalory.mapper;

import com.matveev.kalory.domain.entity.ConsumedProductEntity;
import com.matveev.kalory.domain.entity.ProductEntity;
import com.matveev.kalory.model.ConsumedProduct;
import lombok.experimental.UtilityClass;

import static com.matveev.kalory.model.id.ConsumedProductId.consumedProductId;
import static com.matveev.kalory.model.id.ProductId.productId;
import static com.matveev.kalory.model.id.UserId.userId;
import static java.util.Optional.ofNullable;

@UtilityClass
public class ConsumedProductMapper {

    public static ConsumedProduct map(ConsumedProductEntity entity, ProductEntity productEntity) {
        return ConsumedProduct.builder()
                .id(consumedProductId(entity.getId()))
                .userId(userId(entity.getUserId()))
                .comment(ofNullable(entity.getComment()))
                .productId(productId(entity.getProductId()))
                .productName(productEntity.getName())
                .consumptionAmount(entity.getConsumptionAmount())
                .consumptionDate(entity.getConsumptionDate())
                .calculatedCalories(entity.getCalculatedCalories())
                .calculatedCarbs(entity.getCalculatedCarbs())
                .calculatedProteins(entity.getCalculatedProteins())
                .calculatedLipids(entity.getCalculatedLipids())
                .build();
    }
}
