package com.alka.inventory.config;

import com.alka.inventory.repo.InMemoryItemRepository;
import com.alka.inventory.repo.ItemRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("!dynamo") // load only when NOT running with -Dspring.profiles.active=dynamo
public class RepoConfig {
    @Bean
    public ItemRepository itemRepository() {
        return new InMemoryItemRepository();
    }
}
