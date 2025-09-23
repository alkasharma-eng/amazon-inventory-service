package com.alka.inventory.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Pattern;


public class CreateItemRequest {
    @NotBlank @Pattern(regexp = "^[A-Z0-9_-]{2,32}$")
    private String sku;

    @NotBlank @Size(max = 120)
    private String name;

    @Min(0) @Max(1_000_000)
    private int stock;

    // getters/setters
    public String getSku() { return sku; }
    public void setSku(String sku) { this.sku = sku; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public int getStock() { return stock; }
    public void setStock(int stock) { this.stock = stock; }
}
