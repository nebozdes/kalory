package com.matveev.kalory.model.request.list;

import lombok.Getter;

@Getter
public class ListProducts extends BaseList {

    public ListProducts(int page, int limit) {
        super(page, limit);
    }
}
