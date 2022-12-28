package com.matveev.kalory.query;

import com.matveev.kalory.model.Tag;
import com.matveev.kalory.model.id.TagId;
import com.matveev.kalory.model.request.list.ListTags;
import org.springframework.data.domain.Page;

public interface TagQueries {
    Page<Tag> list(ListTags listProducts);

    Tag getById(TagId id);
}
