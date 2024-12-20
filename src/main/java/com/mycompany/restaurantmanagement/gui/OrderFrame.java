/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.restaurantmanagement.gui;

import com.mycompany.restaurantmanagement.singleton.singleton3.DatabaseConnection;
import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.time.LocalDateTime;

/**
 * The OrderFrame class represents a graphical user interface (GUI) for creating an order in a restaurant.
 * This frame allows the user to input the customer name, table number, and total price for the order and saves it to the database.
 * The design patterns used in this class help improve the flexibility, reusability, and maintainability of the code.
 *
 * <p>
 * Design Patterns Used:
 * <ul>
 *     <li><b>Observer Pattern:</b>
 *     The `ActionListener` attached to the `createOrderButton` is an example of the Observer pattern. It listens for user interactions (button clicks) and reacts by executing the order creation logic. The observer pattern helps decouple the GUI components (like buttons) from the business logic.</li>
 *
 *     <li><b>Template Method Pattern:</b>
 *     The general structure of order creation, involving input validation, database insertion, and error handling, follows a clear template. The steps are predefined, and the details (such as customer information, table number, etc.) are provided by the user. This pattern helps maintain a consistent flow of operations while allowing flexibility in specific implementations.</li>
 *
 *     <li><b>Singleton Pattern:</b>
 *     The `DatabaseConnection.getConnection()` method likely utilizes the Singleton pattern to ensure that only one instance of the database connection exists throughout the application. This pattern is used to manage the connection resource efficiently.</li>
 * </ul>
 * </p>
 */
public class OrderFrame extends JFrame {

    /**
     * Constructs the OrderFrame and initializes the GUI components and event handlers.
     * This method sets up the user interface, including the input fields for the customer name, table number, and total price,
     * as well as the event listener for creating an order.
     */
    public OrderFrame() {
        setTitle("Create Order");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new GridLayout(5, 2, 10, 10));

        // GUI components
        JLabel customerLabel = new JLabel("Customer Name:");
        JTextField customerField = new JTextField();

        JLabel tableLabel = new JLabel("Table Number:");
        JTextField tableField = new JTextField();

        JLabel totalLabel = new JLabel("Total Price:");
        JTextField totalField = new JTextField();

        JButton createOrderButton = new JButton("Create Order");

        // Adding components to the frame
        add(customerLabel);
        add(customerField);
        add(tableLabel);
        add(tableField);
        add(totalLabel);
        add(totalField);
        add(new JLabel()); // Empty space
        add(createOrderButton);

        // Button click event for creating an order
        createOrderButton.addActionListener(e -> {
            String customerName = customerField.getText();
            String tableNumberText = tableField.getText();
            String totalPriceText = totalField.getText();

            // Validate inputs
            if (customerName.isEmpty() || tableNumberText.isEmpty() || totalPriceText.isEmpty()) {
                JOptionPane.showMessageDialog(this, "All fields must be filled!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            try {
                int tableNumber = Integer.parseInt(tableNumberText);
                double totalPrice = Double.parseDouble(totalPriceText);

                if (totalPrice <= 0) {
                    JOptionPane.showMessageDialog(this, "Total price must be greater than zero.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Database connection and order insertion
                try (Connection connection = DatabaseConnection.getConnection()) {
                    String query = "INSERT INTO Orders (customer_name, table_number, total_price, order_date) VALUES (?, ?, ?, ?)";
                    try (PreparedStatement statement = connection.prepareStatement(query)) {
                        statement.setString(1, customerName);
                        statement.setInt(2, tableNumber);
                        statement.setDouble(3, totalPrice);
                        statement.setTimestamp(4, Timestamp.valueOf(LocalDateTime.now()));
                        int rowsAffected = statement.executeUpdate();

                        if (rowsAffected > 0) {
                            JOptionPane.showMessageDialog(this, "Order Created Successfully!");
                        } else {
                            JOptionPane.showMessageDialog(this, "Failed to create order.");
                        }
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(this, "Error: Unable to create order in database.", "Database Error", JOptionPane.ERROR_MESSAGE);
                }

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Invalid input. Please enter valid numbers for table number and total price.", "Input Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        setVisible(true);
    }
}
