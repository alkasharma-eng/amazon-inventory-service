package com.alka.inventory.dto;

import jakarta.validation.constraints.*;

public class UpdateStockRequest {
    @Min(0) @Max(1_000_000)
    private int stock;

    public int getStock() { return stock; }
    public void setStock(int stock) { this.stock = stock; }
}