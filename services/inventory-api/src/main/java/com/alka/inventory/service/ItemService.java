package com.alka.inventory.service;

import com.alka.inventory.model.Item;
import com.alka.inventory.repo.ItemRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ItemService {
    private final ItemRepository repo;
    public ItemService(ItemRepository repo) { this.repo = repo; }

    public Optional<Item> get(String sku) { return repo.findBySku(sku); }
    public Item create(Item item) { return repo.save(item); }
    public boolean exists(String sku) { return repo.existsBySku(sku); }
    public void updateStock(String sku, int stock) { repo.updateStock(sku, stock); }
}
