package com.mycompany.restaurantmanagement.factory.factory2;

public class OutdoorTable implements TableType {
    @Override
    public void setup() {
        System.out.println("Setting up an outdoor table...");
    }
}
