package com.matveev.kalory.query.impl;

import com.matveev.kalory.domain.repository.TagRepository;
import com.matveev.kalory.mapper.TagMapper;
import com.matveev.kalory.model.Tag;
import com.matveev.kalory.model.id.TagId;
import com.matveev.kalory.model.request.list.ListTags;
import com.matveev.kalory.query.TagQueries;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import static com.matveev.kalory.mapper.TagMapper.map;

@Service
@RequiredArgsConstructor
public class TagQueriesBean implements TagQueries {

    private final TagRepository tagRepository;

    @Override
    public Page<Tag> list(ListTags listTags) {
        final var pageObject = PageRequest.of(listTags.page, listTags.limit);
        return tagRepository.findAll(pageObject).map(TagMapper::map);
    }

    @Override
    public Tag getById(TagId id) {
        return map(tagRepository.getById(id.value()));
    }
}
