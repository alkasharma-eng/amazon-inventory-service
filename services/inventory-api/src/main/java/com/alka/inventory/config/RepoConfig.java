package com.alka.inventory.config;

import com.alka.inventory.repo.InMemoryItemRepository;
import com.alka.inventory.repo.ItemRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RepoConfig {
    @Bean
    public ItemRepository itemRepository() {
        return new InMemoryItemRepository();
    }
}
