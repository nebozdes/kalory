package com.matveev.kalory.domain.repository;

import com.matveev.kalory.domain.entity.ConsumedProductEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;

public interface ConsumedProductRepository extends CustomRepository<ConsumedProductEntity> {

    Page<ConsumedProductEntity> findAllByConsumptionDate(LocalDate date, Pageable pageable);
}
