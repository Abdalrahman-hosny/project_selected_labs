package com.mycompany.restaurantmanagement.proxy;

public class PaymentProxy implements PaymentProcessor{
    private PaymentProcessor paymentProcessor;

    public PaymentProxy(PaymentProcessor paymentProcessor) {
        this.paymentProcessor = paymentProcessor;
    }
    @Override
    public boolean processPayment(String customerName, String paymentMethod, double amount) {
        // Validation: Check if amount is positive
        if (amount <= 0) {
            System.out.println("Invalid payment amount. Payment rejected.");
            return false;
        }

        // Logging: Record the payment attempt
        System.out.println("Logging: Attempting payment for " + customerName + " with amount $" + amount);

        // Delegating to the actual payment processor
        return paymentProcessor.processPayment(customerName, paymentMethod, amount);
    }
}
