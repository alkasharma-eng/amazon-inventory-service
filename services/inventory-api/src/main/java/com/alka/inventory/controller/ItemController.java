package com.alka.inventory.controller;

import com.alka.inventory.model.Item;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/items")
public class ItemController {

    // GET /items/{sku} -> return stub data for now
    @GetMapping("/{sku}")
    public ResponseEntity<Item> getItemBySku(@PathVariable String sku) {
        // Stub: return a sample item (later, fetch from DB)
        Item item = new Item(sku, "Yamaha P525", 3);
        return ResponseEntity.ok(item);
    }
}