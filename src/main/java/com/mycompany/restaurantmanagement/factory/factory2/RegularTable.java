package com.mycompany.restaurantmanagement.factory.factory2;

public class RegularTable implements TableType {
    @Override
    public void setup() {
        System.out.println("Setting up a regular table...");
    }
}
