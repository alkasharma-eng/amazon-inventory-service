package com.alka.inventory.repo;

import com.alka.inventory.model.Item;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Repository
@Profile("!dynamo")
public class InMemoryItemRepository implements ItemRepository {
    private final Map<String, Item> store = new ConcurrentHashMap<>();

    @Override public Optional<Item> findBySku(String sku) { return Optional.ofNullable(store.get(sku)); }
    @Override public Item save(Item item) { store.put(item.getSku(), item); return item; }
    @Override public boolean existsBySku(String sku) { return store.containsKey(sku); }
    @Override public void updateStock(String sku, int stock) {
        Item it = store.get(sku);
        if (it != null) it.setStock(stock);
    }
}
