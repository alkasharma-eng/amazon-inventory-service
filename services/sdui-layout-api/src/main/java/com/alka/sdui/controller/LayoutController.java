package com.alka.sdui.controller;

import com.alka.sdui.dto.ItemDto;
import com.alka.sdui.dto.SduiComponent;
import com.alka.sdui.dto.SduiResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/sdui")
public class LayoutController {

    private final WebClient webClient;
    private final String inventoryBaseUrl;

    public LayoutController(@Value("${inventory.api.base-url}") String inventoryBaseUrl) {
        this.inventoryBaseUrl = inventoryBaseUrl;
        this.webClient = WebClient.builder().baseUrl(inventoryBaseUrl).build();
    }

    @GetMapping(value = "/item/{sku}", produces = MediaType.APPLICATION_JSON_VALUE)
    public SduiResponse getItemCard(@PathVariable String sku) {
        // 1) Fetch item from inventory-api (fallback to stub if unavailable)
        ItemDto item = webClient.get()
                .uri("/items/{sku}", sku)
                .retrieve()
                .bodyToMono(ItemDto.class)
                .onErrorReturn(fallbackItem(sku))
                .block();

        // 2) Build SDUI JSON
        SduiResponse resp = new SduiResponse();
        resp.setScreen("InventoryItem");

        // Card
        Map<String, Object> cardProps = new HashMap<>();
        cardProps.put("title", item.getName());
        cardProps.put("subtitle", "SKU " + item.getSku());
        resp.getComponents().add(new SduiComponent("Card", cardProps));

        // Text
        Map<String, Object> textProps = new HashMap<>();
        textProps.put("value", "In stock: " + item.getStock());
        resp.getComponents().add(new SduiComponent("Text", textProps));

        // Button
        Map<String, Object> buttonProps = new HashMap<>();
        buttonProps.put("label", "Reserve");
        Map<String, Object> action = new HashMap<>();
        action.put("type", "POST");
        action.put("url", "/reserve");
        Map<String, Object> body = new HashMap<>();
        body.put("sku", item.getSku());
        action.put("body", body);
        buttonProps.put("action", action);
        resp.getComponents().add(new SduiComponent("Button", buttonProps));

        return resp;
    }

    private ItemDto fallbackItem(String sku) {
        ItemDto it = new ItemDto();
        it.setSku(sku);
        it.setName("Sample Item");
        it.setStock(3);
        return it;
    }
}