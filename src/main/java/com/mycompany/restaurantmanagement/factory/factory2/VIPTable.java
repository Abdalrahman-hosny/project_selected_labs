package com.mycompany.restaurantmanagement.factory.factory2;

public class VIPTable implements TableType {
    @Override
    public void setup() {
        System.out.println("Setting up a VIP table...");
    }
}
