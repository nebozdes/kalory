package com.matveev.kalory.api;

import com.matveev.kalory.command.ConsumedProductCommands;
import com.matveev.kalory.model.ConsumedProduct;
import com.matveev.kalory.model.request.create.CreateConsumedProduct;
import com.matveev.kalory.model.request.list.ListConsumedProducts;
import com.matveev.kalory.query.ConsumedProductQueries;
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

import static com.matveev.kalory.model.id.ConsumedProductId.consumedProductId;
import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequiredArgsConstructor
@RequestMapping("/consumed-product")
public class ConsumedProductController {

    private final ConsumedProductCommands consumedProductCommands;
    private final ConsumedProductQueries consumedProductQueries;
    private final SecurityService service;

    @GetMapping
    public Page<ConsumedProduct> list(ListConsumedProducts listProducts) {
        return consumedProductQueries.list(listProducts);
    }

    @PostMapping
    public ResponseEntity<ConsumedProduct> create(@RequestBody @Valid CreateConsumedProduct createProduct) {
        final var creatorId = service.getCurrentUser().getId();
        final var newEntity = consumedProductCommands.create(createProduct.withUserId(creatorId));
        return ResponseEntity.created(URI.create("/consumed-product/" + newEntity.getId())).body(newEntity);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        final var actor = service.getCurrentUser();
        final var consumedProduct = consumedProductQueries.getById(consumedProductId(id));

        if (actor.getId().equals(consumedProduct.getUserId())) {
            consumedProductCommands.delete(consumedProductId(id));
        }

        return ok().build();
    }
}
