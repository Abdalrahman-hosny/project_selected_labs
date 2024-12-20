package com.mycompany.restaurantmanagement.singleton.singleton3;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
/**
 *
 * @author PC
 */
// Singleton Design Pattern
public class DatabaseConnection {
    // Database connection details
    private static final String URL = "jdbc:mysql://localhost:3306/restaurant_management";
        private static final String USER = "root";
        private static final String PASSWORD = "12345";


        // Static reference to the single instance of the connection
        private static Connection connection;
        
        
        public static Connection getConnection() throws SQLException {
          // Double-checked locking to ensure a single instance is created

            if (connection == null){
                synchronized (DatabaseConnection.class){
                    if (connection == null){
                        connection = DriverManager.getConnection(URL, USER, PASSWORD);
                    }
                }
            }
            return connection;
        }
}
