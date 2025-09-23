package com.alka.inventory.controller;

import com.alka.inventory.dto.CreateItemRequest;
import com.alka.inventory.dto.UpdateStockRequest;
import com.alka.inventory.model.Item;
import com.alka.inventory.service.ItemService;
import jakarta.validation.Valid;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/items")
public class ItemController {
    private final ItemService svc;
    public ItemController(ItemService svc) { this.svc = svc; }

    @GetMapping("/{sku}")
    public ResponseEntity<Item> getItemBySku(@PathVariable String sku) {
        return svc.get(sku)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @PostMapping
    public ResponseEntity<Item> create(@Valid @RequestBody CreateItemRequest req) {
        if (svc.exists(req.getSku())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
        Item created = svc.create(new Item(req.getSku(), req.getName(), req.getStock()));
        return ResponseEntity.created(URI.create("/items/" + created.getSku())).body(created);
    }

    @PatchMapping("/{sku}/stock")
    public ResponseEntity<Void> updateStock(@PathVariable String sku, @Valid @RequestBody UpdateStockRequest req) {
        if (!svc.exists(sku)) return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        svc.updateStock(sku, req.getStock());
        return ResponseEntity.noContent().build();
    }
}
