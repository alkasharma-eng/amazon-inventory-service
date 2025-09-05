package com.alka.inventory.model;

public class Item {
    private String sku;
    private String name;
    private int stock;

    public Item() {}

    public Item(String sku, String name, int stock) {
        this.sku = sku;
        this.name = name;
        this.stock = stock;
    }

    // getters and setters...
}