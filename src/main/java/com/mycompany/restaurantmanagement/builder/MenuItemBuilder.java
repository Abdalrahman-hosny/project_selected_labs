package com.mycompany.restaurantmanagement.builder;

import com.mycompany.restaurantmanagement.entity.MenuItem;

// Builder Design Pattern
public class MenuItemBuilder {
    private Integer id;
    private String name;
    private String type;
    private double price;

    public MenuItemBuilder setId(Integer id){
        this.id = id;
        return this;
    }
    public MenuItemBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public MenuItemBuilder setType(String type) {
        this.type = type;
        return this;
    }

    public MenuItemBuilder setPrice(double price) {
        this.price = price;
        return this;
    }

    public MenuItem build() {
        return new MenuItem(id, name, type, price);
    }
}
