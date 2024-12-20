package com.mycompany.restaurantmanagement.prototype;

import java.sql.Timestamp;

// Prototype Design Pattern
public class Payment implements Prototype{
    private Integer id;
    private String customerName;
    private String paymentMethod;
    private double amount;
    private Timestamp payment_date;

    public Payment(Payment payment){
        this.id = payment.id;
        this.customerName = payment.customerName;
        this.paymentMethod = payment.paymentMethod;
        this.amount = payment.amount;
        this.payment_date = payment.payment_date;
    }

    public Payment(String customerName, String paymentMethod, double amount, Timestamp payment_date) {
        this.customerName = customerName;
        this.paymentMethod = paymentMethod;
        this.amount = amount;
        this.payment_date = payment_date;
    }


    @Override
    public Payment clone() {
        return new Payment(this);
    }

    public Integer getId() {
        return id;
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public double getAmount() {
        return amount;
    }

    public Timestamp getPayment_date() {
        return payment_date;
    }

    @Override
    public String toString() {
        return "Payment{" +
                "id=" + id +
                ", customerName='" + customerName + '\'' +
                ", paymentMethod='" + paymentMethod + '\'' +
                ", amount=" + amount +
                ", payment_date=" + payment_date +
                '}';
    }
}
