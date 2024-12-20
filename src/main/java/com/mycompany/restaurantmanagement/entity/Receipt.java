package com.mycompany.restaurantmanagement.entity;

import java.time.LocalDateTime;

public class Receipt {
    private double amount;
    private String paymentMethod;
    private LocalDateTime paymentDate;

    public Receipt(double amount, String paymentMethod, LocalDateTime paymentDate) {
        this.amount = amount;
        this.paymentMethod = paymentMethod;
        this.paymentDate = paymentDate;
    }

    public double getAmount() {
        return amount;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public LocalDateTime getPaymentDate() {
        return paymentDate;
    }

    @Override
    public String toString() {
        return "Receipt{" +
                "amount=" + amount +
                ", paymentMethod='" + paymentMethod + '\'' +
                ", paymentDate=" + paymentDate +
                '}';
    }
}
