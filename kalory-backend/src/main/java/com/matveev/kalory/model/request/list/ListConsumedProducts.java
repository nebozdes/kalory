package com.matveev.kalory.model.request.list;

import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.Optional;

public class ListConsumedProducts extends BaseList{

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    public Optional<LocalDate> consumptionDate;
    public ListConsumedProducts(int page, int limit, Optional<LocalDate> consumptionDate) {
        super(page, limit);
        this.consumptionDate = consumptionDate;
    }
}
