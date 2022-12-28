package com.matveev.kalory.mapper;

import com.matveev.kalory.domain.entity.ProductEntity;
import com.matveev.kalory.model.Product;
import lombok.experimental.UtilityClass;

import static com.matveev.kalory.model.id.ProductId.productId;

@UtilityClass
public class ProductMapper {

    public static Product map(ProductEntity productEntity) {
        return Product.builder()
                .id(productId(productEntity.getId()))
                .name(productEntity.getName())
                .baseAmount(productEntity.getBaseAmount())
                .baseAmountType(productEntity.getBaseAmountType())
                .baseLipids(productEntity.getBaseLipids())
                .baseCalories(productEntity.getBaseCalories())
                .baseCarbs(productEntity.getBaseCarbs())
                .baseProteins(productEntity.getBaseProteins())
                .build();
    }
}
