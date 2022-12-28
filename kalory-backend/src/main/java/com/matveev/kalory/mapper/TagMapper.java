package com.matveev.kalory.mapper;

import com.matveev.kalory.domain.entity.TagEntity;
import com.matveev.kalory.model.Tag;
import lombok.experimental.UtilityClass;

import static com.matveev.kalory.model.id.TagId.tagId;

@UtilityClass
public class TagMapper {

    public static Tag map(TagEntity entity) {
        return Tag.builder()
                .id(tagId(entity.getId()))
                .name(entity.getName())
                .build();
    }
}
