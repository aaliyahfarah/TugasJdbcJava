package utils;

import java.sql.Connection;
import java.sql.DriverManager;

public class DbConnection {
    private static Connection connection;

    public static Connection getConnection(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/db_purchase", "root", "0506");
            System.out.println("\n--- Connection established ---\n");
        } catch (Exception e) {
            System.out.println("Error " + e.getMessage());
            e.printStackTrace();
        }
        return connection;
    }
}
