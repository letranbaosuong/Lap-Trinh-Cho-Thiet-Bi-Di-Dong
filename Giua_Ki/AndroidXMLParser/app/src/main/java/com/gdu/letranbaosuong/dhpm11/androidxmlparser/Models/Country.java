package com.gdu.letranbaosuong.dhpm11.androidxmlparser.Models;

public class Country {
    private String name;
    private String capital;
    private String id;

    public Country() {
    }

    public Country(String name, String capital, String id) {
        this.name = name;
        this.capital = capital;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCapital() {
        return capital;
    }

    public void setCapital(String capital) {
        this.capital = capital;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
