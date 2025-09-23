package com.alka.inventory.repo;

import com.alka.inventory.model.Item;
import java.util.Optional;

public interface ItemRepository {
    Optional<Item> findBySku(String sku);
    Item save(Item item);
    boolean existsBySku(String sku);
    void updateStock(String sku, int stock);
}
