package com.matveev.kalory.api;

import com.matveev.kalory.command.WeightCheckCommands;
import com.matveev.kalory.model.WeightCheck;
import com.matveev.kalory.model.request.create.CreateWeightCheck;
import com.matveev.kalory.model.request.list.ListWeightChecks;
import com.matveev.kalory.query.WeightCheckQueries;
import com.matveev.kalory.service.SecurityService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import java.net.URI;

import static com.matveev.kalory.model.id.WeightCheckId.weightCheckId;
import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequiredArgsConstructor
@RequestMapping("/weight-check")
public class WeightCheckController {

    private final WeightCheckCommands weightCheckCommands;
    private final WeightCheckQueries weightCheckQueries;
    private final SecurityService service;

    @GetMapping
    public Page<WeightCheck> list(ListWeightChecks listWeightChecks) {
        return weightCheckQueries.list(listWeightChecks);
    }

    @PostMapping
    public ResponseEntity<WeightCheck> create(@RequestBody @Valid CreateWeightCheck createWeightCheck) {
        final var creatorId = service.getCurrentUser().getId();
        final var newEntity = weightCheckCommands.create(createWeightCheck.withUserId(creatorId));
        return ResponseEntity.created(URI.create("/weight-check/" + newEntity.getId())).body(newEntity);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        final var actor = service.getCurrentUser();
        final var weightCheck = weightCheckQueries.getById(weightCheckId(id));

        if (actor.getId().equals(weightCheck.getUserId())) {
            weightCheckCommands.delete(weightCheckId(id));
        }

        return ok().build();
    }
}
