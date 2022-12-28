package com.matveev.kalory;

import com.matveev.kalory.domain.entity.ProductEntity;
import com.matveev.kalory.model.Product;

import static com.matveev.kalory.RandomUtils.aRandomId;
import static com.matveev.kalory.RandomUtils.aRandomString;
import static com.matveev.kalory.domain.entity.AmountType.GRAM;
import static com.matveev.kalory.model.id.ProductId.productId;
import static java.math.BigDecimal.valueOf;

public interface ProductTestData {

    default Product.ProductBuilder aProduct() {
        return Product.builder()
                .id(productId(aRandomId()))
                .name(aRandomString(10))
                .baseProteins(valueOf(1))
                .baseCarbs(valueOf(2))
                .baseLipids(valueOf(3))
                .baseCalories(valueOf(100))
                .baseAmountType(GRAM)
                .baseAmount(valueOf(100));
    }

    default ProductEntity.ProductEntityBuilder aProductEntity() {
        return ProductEntity.builder()
                .id(aRandomId())
                .name(aRandomString(10))
                .baseProteins(valueOf(1))
                .baseCarbs(valueOf(2))
                .baseLipids(valueOf(3))
                .baseCalories(valueOf(100))
                .baseAmountType(GRAM)
                .baseAmount(valueOf(100));
    }
}
