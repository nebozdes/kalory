package com.matveev.kalory.api;

import com.matveev.kalory.model.Product;
import com.matveev.kalory.model.request.create.CreateProduct;
import com.matveev.kalory.model.request.list.ListProducts;
import com.matveev.kalory.command.ProductCommands;
import com.matveev.kalory.query.ProductQueries;
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

import static com.matveev.kalory.model.id.ProductId.productId;
import static org.springframework.http.ResponseEntity.created;
import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequiredArgsConstructor
@RequestMapping("/product")
public class ProductController {

    private final ProductCommands productCommands;
    private final ProductQueries productQueries;

    @GetMapping
    public Page<Product> list(ListProducts listProducts) {
        return productQueries.list(listProducts);
    }

    @PostMapping
    @PreAuthorize("hasAnyAuthority('ROLE_MODERATOR', 'ROLE_ADMINISTRATOR')")
    public ResponseEntity<Product> create(@RequestBody @Valid CreateProduct createProduct) {
        final var newEntity = productCommands.create(createProduct);
        return created(URI.create("/product/" + newEntity.getId())).body(newEntity);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ROLE_MODERATOR', 'ROLE_ADMINISTRATOR')")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        productCommands.delete(productId(id));
        return ok().build();
    }
}
