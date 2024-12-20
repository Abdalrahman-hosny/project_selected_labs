package com.mycompany.restaurantmanagement.gui;

import com.mycompany.restaurantmanagement.prototype.Payment;
import com.mycompany.restaurantmanagement.proxy.PaymentProcessor;
import com.mycompany.restaurantmanagement.proxy.PaymentProxy;
import com.mycompany.restaurantmanagement.singleton.singleton2.PaymentSystem;
import javax.swing.*;
import java.awt.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;

/**
 * The PaymentFrame class represents a graphical user interface (GUI) for processing and cloning payments.
 * It demonstrates the use of various design patterns for clean and maintainable code.
 * 
 * <p>
 * Design Patterns Used:
 * <ul>
 *     <li><b>Proxy Pattern:</b> 
 *     The `PaymentProxy` class acts as a proxy to the `PaymentSystem` instance. This pattern is used to add additional behavior (e.g., logging, access control) without modifying the `PaymentSystem` directly.</li>
 *     
 *     <li><b>Singleton Pattern:</b> 
 *     The `PaymentSystem` class uses the Singleton pattern to ensure only one instance is created and shared across the application.</li>
 *     
 *     <li><b>Prototype Pattern:</b> 
 *     The `Payment` class implements the `clone()` method, allowing for easy duplication of an existing payment object. This is used for cloning the last payment.</li>
 * </ul>
 * </p>
 */
public class PaymentFrame extends JFrame {
    private Payment lastPayment; // To store the last processed payment

    /**
     * Constructs the PaymentFrame, initializing the GUI components and event handlers.
     */
    public PaymentFrame() {
        setTitle("Payment System");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new GridLayout(6, 2, 10, 10)); // Layout to arrange components in a grid

        // GUI components
        JLabel customerLabel = new JLabel("Customer Name:");
        JTextField customerField = new JTextField();

        JLabel methodLabel = new JLabel("Payment Method:");
        JComboBox<String> methodComboBox = new JComboBox<>(new String[]{"Cash", "Credit Card", "Mobile Payment"});

        JLabel amountLabel = new JLabel("Amount:");
        JTextField amountField = new JTextField();

        JButton processButton = new JButton("Process Payment");
        JButton cloneButton = new JButton("Clone Payment");

        // Adding components to the frame
        add(customerLabel);
        add(customerField);
        add(methodLabel);
        add(methodComboBox);
        add(amountLabel);
        add(amountField);
        add(new JLabel()); // Empty space
        add(processButton);
        add(new JLabel()); // Empty space
        add(cloneButton);

        // Create the Proxy with the real PaymentSystem
        PaymentProcessor paymentProxy = new PaymentProxy(PaymentSystem.getInstance());

        // Button click event for processing payment
        processButton.addActionListener(e -> {
            String customerName = customerField.getText();
            String paymentMethod = (String) methodComboBox.getSelectedItem();
            String amountText = amountField.getText();

            try {
                double amount = Double.parseDouble(amountText);

                // Use the Proxy Design Pattern to process payment
                boolean success = paymentProxy.processPayment(customerName, paymentMethod, amount);

                if (success) {
                    JOptionPane.showMessageDialog(this, "Payment processed successfully!");

                    // Save the payment as the last processed payment
                    lastPayment = new Payment(customerName, paymentMethod, amount, new Timestamp(System.currentTimeMillis()));
                } else {
                    JOptionPane.showMessageDialog(this, "Failed to process payment.", "Error", JOptionPane.ERROR_MESSAGE);
                }

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Invalid amount. Please enter a valid number.", "Input Error", JOptionPane.ERROR_MESSAGE);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        // Button click event for cloning the last payment
        cloneButton.addActionListener(e -> {
            if (lastPayment != null) {
                // Clone the last payment
                Payment clonedPayment = lastPayment.clone();

                // Populate fields with cloned payment data
                customerField.setText(clonedPayment.getCustomerName());
                methodComboBox.setSelectedItem(clonedPayment.getPaymentMethod());
                amountField.setText(String.valueOf(clonedPayment.getAmount()));

                JOptionPane.showMessageDialog(this, "Last payment cloned successfully!");
            } else {
                JOptionPane.showMessageDialog(this, "No payment to clone.", "Info", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        setVisible(true);
    }
}
