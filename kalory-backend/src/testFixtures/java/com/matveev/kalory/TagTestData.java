package com.matveev.kalory;

import com.matveev.kalory.domain.entity.TagEntity;
import com.matveev.kalory.model.Tag;

import static com.matveev.kalory.RandomUtils.aRandomId;
import static com.matveev.kalory.RandomUtils.aRandomString;
import static com.matveev.kalory.model.id.TagId.tagId;

public interface TagTestData {

    default Tag.TagBuilder aTag() {
        return Tag.builder()
                .id(tagId(aRandomId()))
                .name(aRandomString(10));
    }

    default TagEntity.TagEntityBuilder aTagEntity() {
        return TagEntity.builder()
                .id(aRandomId())
                .name(aRandomString(10));
    }
}
