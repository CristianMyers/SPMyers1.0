package com.example.proyecto1;

public class Product {
    private String name;
    private int imageResId;
    private int stock;

    public Product(String name, int imageResId, int stock) {
        this.name = name;
        this.imageResId = imageResId;
        this.stock = stock;
    }

    public String getName() {
        return name;
    }

    public int getImageResId() {
        return imageResId;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }
}


