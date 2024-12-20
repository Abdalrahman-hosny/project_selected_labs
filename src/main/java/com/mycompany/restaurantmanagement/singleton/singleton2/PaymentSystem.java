package com.mycompany.restaurantmanagement.singleton.singleton2;

import com.mycompany.restaurantmanagement.proxy.PaymentProcessor;
import com.mycompany.restaurantmanagement.singleton.singleton3.DatabaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

// Singleton Design Pattern
public class PaymentSystem implements PaymentProcessor {
   // Static reference to the single instance of PaymentSystem
    private static PaymentSystem instance;

     /**
     * Private constructor to prevent instantiation from outside of this class. 
     * The Singleton pattern ensures that the instance is created only once.
     */
    private PaymentSystem() {
    }

    public static synchronized PaymentSystem getInstance() {
        if (instance == null) {
            instance = new PaymentSystem();
        }
        return instance;
    }

    // Method to process payment
    @Override
    public boolean processPayment(String customerName, String paymentMethod, double amount) {
        // Validate input
        if (customerName == null || customerName.isEmpty() || paymentMethod == null || paymentMethod.isEmpty() || amount <= 0) {
            throw new IllegalArgumentException("Invalid payment details.");
        }

        // Save the payment to the database
        try (Connection connection = DatabaseConnection.getConnection()) {
            String query = "INSERT INTO Payments (customer_name, payment_method, amount) VALUES (?, ?, ?)";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, customerName);
                statement.setString(2, paymentMethod);
                statement.setDouble(3, amount);
                int rowsInserted = statement.executeUpdate();
                return rowsInserted > 0; // Return true if payment was successfully inserted
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
