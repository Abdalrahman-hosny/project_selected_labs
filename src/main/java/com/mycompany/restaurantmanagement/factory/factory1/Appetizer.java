package com.mycompany.restaurantmanagement.factory.factory1;

import com.mycompany.restaurantmanagement.builder.MenuItemBuilder;
import com.mycompany.restaurantmanagement.entity.MenuItem;

public class Appetizer implements MenuItemInterface {
    @Override
    public MenuItem prepare() {
        return new MenuItemBuilder()
                .setType("Appetizer")
                .build();
    }
}

