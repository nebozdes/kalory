package com.matveev.kalory.model.request.list;

import lombok.Getter;

@Getter
public class ListTags extends BaseList {

    public ListTags(int page, int limit) {
        super(page, limit);
    }
}
