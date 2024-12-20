package com.mycompany.restaurantmanagement.entity;
import java.sql.Timestamp;
import java.util.Date;

public class Order {
    private int id;
    private String customerName;
    private int tableNumber;
    private double totalPrice;
    private Timestamp orderDate;

    // Constructor
    public Order(int id, String customerName, int tableNumber, double totalPrice, Timestamp orderDate) {
        this.id = id;
        this.customerName = customerName;
        this.tableNumber = tableNumber;
        this.totalPrice = totalPrice;
        this.orderDate = orderDate;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public int getTableNumber() {
        return tableNumber;
    }

    public void setTableNumber(int tableNumber) {
        this.tableNumber = tableNumber;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Timestamp orderDate) {
        this.orderDate = orderDate;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", customerName='" + customerName + '\'' +
                ", tableNumber=" + tableNumber +
                ", totalPrice=" + totalPrice +
                ", orderDate=" + orderDate +
                '}';
    }
}
