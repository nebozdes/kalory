package com.matveev.kalory.command;

import com.matveev.kalory.model.Tag;
import com.matveev.kalory.model.id.TagId;
import com.matveev.kalory.model.request.create.CreateTag;

public interface TagCommands {

    Tag create(CreateTag createTagRequest);
    void delete(TagId tagId);
}
