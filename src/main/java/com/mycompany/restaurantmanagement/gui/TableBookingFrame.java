package com.mycompany.restaurantmanagement.gui;

import com.mycompany.restaurantmanagement.singleton.singleton3.DatabaseConnection;
import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

/**
 * The TableBookingFrame class represents a GUI interface for booking a table in a restaurant.
 * It demonstrates the usage of design patterns to improve code organization, maintainability, and flexibility.
 * 
 * <p>
 * Design Patterns Used:
 * <ul>
 *     <li><b>Observer Pattern:</b> 
 *     The `ActionListener` attached to the `bookButton` is an example of the Observer Pattern. The button listens for user actions (click events) and reacts by executing the table booking logic. The listener updates the UI based on user interaction and abstracts the business logic, allowing for better separation of concerns.</li>
 *     
 *     <li><b>Factory Method Pattern:</b> 
 *     The `DatabaseConnection.getConnection()` method can be considered an implementation of the Factory Method Pattern. It abstracts the creation and retrieval of the database connection, making it easy to modify the connection logic in the future without changing the code that uses the connection.</li>
 * </ul>
 * </p>
 */
public class TableBookingFrame extends JFrame {

    /**
     * Constructs the TableBookingFrame, initializing the GUI components and event handlers.
     */
    public TableBookingFrame() {
        setTitle("Table Booking");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new GridLayout(3, 2, 10, 10));

        // GUI components
        JLabel tableTypeLabel = new JLabel("Select Table Type:");
        JComboBox<String> tableTypeComboBox = new JComboBox<>(new String[]{"Regular", "VIP", "Outdoor"});

        JButton bookButton = new JButton("Book Table");

        // Adding components to the frame
        add(tableTypeLabel);
        add(tableTypeComboBox);
        add(new JLabel()); // Empty space
        add(bookButton);

        // Button click event for booking a table
        bookButton.addActionListener(e -> {
            String selectedType = (String) tableTypeComboBox.getSelectedItem();

            try (Connection connection = DatabaseConnection.getConnection()) {
                // Insert selected table type into the database
                String query = "INSERT INTO Tables (type) VALUES (?)";
                PreparedStatement statement = connection.prepareStatement(query);
                statement.setString(1, selectedType);
                statement.executeUpdate();

                JOptionPane.showMessageDialog(this, selectedType + " Table Booked Successfully!");
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Error: Unable to book table.");
            }
        });

        setVisible(true);
    }
}

