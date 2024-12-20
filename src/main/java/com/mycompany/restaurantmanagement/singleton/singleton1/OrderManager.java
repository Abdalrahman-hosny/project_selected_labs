package com.mycompany.restaurantmanagement.singleton.singleton1;

import com.mycompany.restaurantmanagement.entity.Order;
import java.util.ArrayList;
import java.util.List;

// Singleton Design Pattern
public class OrderManager {
    private static OrderManager instance;
    private final List<Order> orders;

    // Private constructor to prevent instantiation
    private OrderManager() {
        orders = new ArrayList<>();
    }

    // Double-Checked Locking for [Singleton]
    public static OrderManager getInstance() {
        if (instance == null) {
            synchronized (OrderManager.class) {
                if (instance == null) {
                    instance = new OrderManager();
                }
            }
        }
        return instance;
    }

    // Add an order
    public void addOrder(Order order) {
        orders.add(order);
        System.out.println("Order added: " + order);
    }

    // Get all orders
    public List<Order> getOrders() {
        return new ArrayList<>(orders);
    }
}
