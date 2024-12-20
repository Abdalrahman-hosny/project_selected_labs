package com.mycompany.restaurantmanagement.gui;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {

    public MainFrame() {
        // Set window title
        setTitle("Restaurant Management System");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10)); // Added spacing between components

        // Header
        JLabel headerLabel = new JLabel("Welcome to Restaurant Management System", JLabel.CENTER);
        headerLabel.setFont(new Font("Arial", Font.BOLD, 20));
        headerLabel.setForeground(Color.WHITE);
        headerLabel.setBackground(new Color(0, 102, 204)); // Set background color
        headerLabel.setOpaque(true);
        headerLabel.setPreferredSize(new Dimension(600, 50)); // Adjust size of the header
        add(headerLabel, BorderLayout.NORTH);

        // Buttons Panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(4, 1, 10, 10));
        buttonPanel.setBackground(new Color(240, 240, 240)); // Light background for button panel
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // Add padding around buttons

        // Buttons
        JButton menuButton = createStyledButton("Manage Menu");
        JButton tableButton = createStyledButton("Book a Table");
        JButton orderButton = createStyledButton("Create an Order");
        JButton paymentButton = createStyledButton("Payment System");

        // Add buttons to the panel
        buttonPanel.add(menuButton);
        buttonPanel.add(tableButton);
        buttonPanel.add(orderButton);
        buttonPanel.add(paymentButton);

        add(buttonPanel, BorderLayout.CENTER);

        // Event Listeners
        menuButton.addActionListener(e -> new MenuManagementFrame());
        tableButton.addActionListener(e -> new TableBookingFrame());
        orderButton.addActionListener(e -> new OrderFrame());
        paymentButton.addActionListener(e -> new PaymentFrame());

        // Center the frame
        setLocationRelativeTo(null); // Centers the window
        setVisible(true);
    }

    /**
     * Helper method to create a styled button.
     */
    private JButton createStyledButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.PLAIN, 16));
        button.setForeground(Color.WHITE);
        button.setBackground(new Color(0, 102, 204)); // Blue color for buttons
        button.setFocusPainted(false); // Remove focus border when clicked
        button.setPreferredSize(new Dimension(200, 50)); // Set consistent button size
        button.setBorder(BorderFactory.createLineBorder(new Color(0, 102, 204), 2)); // Border for the button
        button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR)); // Change cursor to hand when hovering
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(0, 120, 255)); // Change button color on hover
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(0, 102, 204)); // Revert button color when hover ends
            }
        });
        return button;
    }

    public static void main(String[] args) {
        // Create and show the MainFrame
        new MainFrame();
    }
}
