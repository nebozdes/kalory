package com.matveev.kalory.api;

import com.matveev.kalory.command.TagCommands;
import com.matveev.kalory.model.Tag;
import com.matveev.kalory.model.request.create.CreateTag;
import com.matveev.kalory.model.request.list.ListTags;
import com.matveev.kalory.query.TagQueries;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import java.net.URI;

import static com.matveev.kalory.model.id.TagId.tagId;
import static org.springframework.http.ResponseEntity.created;
import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequiredArgsConstructor
@RequestMapping("/tag")
public class TagController {

    private final TagCommands tagCommands;
    private final TagQueries tagQueries;

    @GetMapping
    public Page<Tag> list(ListTags listTags) {
        return tagQueries.list(listTags);
    }

    @PostMapping
    @PreAuthorize("hasAnyAuthority('MODERATOR', 'ADMINISTRATOR')")
    public ResponseEntity<Tag> create(@RequestBody @Valid CreateTag createTag) {
        final var newEntity = tagCommands.create(createTag);
        return created(URI.create("/tag/" + newEntity.getId())).body(newEntity);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('MODERATOR', 'ADMINISTRATOR')")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        tagCommands.delete(tagId(id));
        return ok().build();
    }
}
