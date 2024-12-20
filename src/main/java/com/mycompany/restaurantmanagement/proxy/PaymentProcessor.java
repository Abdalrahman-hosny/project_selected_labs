package com.mycompany.restaurantmanagement.proxy;

// Proxy Design Pattern
public interface PaymentProcessor {
    boolean processPayment(String customerName, String paymentMethod, double amount);
}
