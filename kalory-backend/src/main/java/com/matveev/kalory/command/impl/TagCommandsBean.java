package com.matveev.kalory.command.impl;

import com.matveev.kalory.command.TagCommands;
import com.matveev.kalory.domain.entity.TagEntity;
import com.matveev.kalory.domain.repository.TagRepository;
import com.matveev.kalory.model.Tag;
import com.matveev.kalory.model.id.TagId;
import com.matveev.kalory.model.request.create.CreateTag;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import static com.matveev.kalory.mapper.TagMapper.map;

@Service
@RequiredArgsConstructor
public class TagCommandsBean implements TagCommands {

    private final TagRepository tagRepository;

    @Override
    public Tag create(@Valid @NotNull CreateTag createTagRequest) {
        final var tag = new TagEntity();
        tag.setName(createTagRequest.getName());

        return map(tagRepository.save(tag));
    }

    @Override
    public void delete(@Valid @NotNull TagId tagId) {
        tagRepository.deleteById(tagId.value());
    }
}
