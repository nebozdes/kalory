package com.matveev.kalory.api;

import com.matveev.kalory.command.WeightTargetCommands;
import com.matveev.kalory.model.WeightTarget;
import com.matveev.kalory.model.request.create.CreateWeightTarget;
import com.matveev.kalory.model.request.list.ListWeightTargets;
import com.matveev.kalory.query.WeightTargetQueries;
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

import static com.matveev.kalory.model.id.WeightTargetId.weightTargetId;
import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequiredArgsConstructor
@RequestMapping("/weight-target")
public class WeightTargetController {

    private final WeightTargetCommands weightTargetCommands;
    private final WeightTargetQueries weightTargetQueries;
    private final SecurityService service;

    @GetMapping
    public Page<WeightTarget> list(ListWeightTargets listWeightTargets) {
        return weightTargetQueries.list(listWeightTargets);
    }

    @PostMapping
    public ResponseEntity<WeightTarget> create(@RequestBody @Valid CreateWeightTarget createWeightTarget) {
        final var creatorId = service.getCurrentUser().getId();
        final var newEntity = weightTargetCommands.create(createWeightTarget.withUserId(creatorId));
        return ResponseEntity.created(URI.create("/weight-target/" + newEntity.getId())).body(newEntity);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        final var actor = service.getCurrentUser();
        final var weightTarget = weightTargetQueries.getById(weightTargetId(id));

        if (actor.getId().equals(weightTarget.getUserId())) {
            weightTargetCommands.delete(weightTargetId(id));
        }

        return ok().build();
    }
}
