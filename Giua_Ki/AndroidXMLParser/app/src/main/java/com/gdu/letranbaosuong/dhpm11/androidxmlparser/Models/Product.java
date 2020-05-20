package com.gdu.letranbaosuong.dhpm11.androidxmlparser.Models;

public class Product {
    private String id;
    private String name;
    private String color;
    private String price;

    public Product() {
    }

    public Product(String id, String name, String color, String price) {
        this.id = id;
        this.name = name;
        this.color = color;
        this.price = price;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
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

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
