package com.gdu.letranbaosuong.dhpm11.volleynodejs.Models;

import androidx.annotation.NonNull;

public class Product {
    private int id;
    private String name;
    private String color;
    private long price;

    public Product() {
    }

    public Product(int id, String name, String color, long price) {
        this.id = id;
        this.name = name;
        this.color = color;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    @NonNull
    @Override
    public String toString() {
        return "Product [id = " + id + ", name = " + name + ", color = " + color + ", price = " + price + "]";
    }
}
