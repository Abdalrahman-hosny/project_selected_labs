CREATE DATABASE restaurant_management;

USE restaurant_management;

CREATE TABLE MenuItems (
    id INT AUTO_INCREMENT PRIMARY KEY,
    type VARCHAR(50),
    name VARCHAR(100),
    price DECIMAL(10, 2)
);

CREATE TABLE Tables (
    id INT AUTO_INCREMENT PRIMARY KEY,
    type VARCHAR(50)
);

CREATE TABLE Orders (
    id INT AUTO_INCREMENT PRIMARY KEY,
    customer_name VARCHAR(100),
    table_number INT,
    total_price DECIMAL(10, 2),
    order_date timestamp
);
CREATE TABLE Payments (
    id INT AUTO_INCREMENT PRIMARY KEY,
    customer_name VARCHAR(255) NOT NULL,
    payment_method VARCHAR(50) NOT NULL,
    amount DECIMAL(10, 2) NOT NULL,
    payment_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

