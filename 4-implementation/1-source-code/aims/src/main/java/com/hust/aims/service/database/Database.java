package com.hust.aims.service.database;

import java.sql.*;

public class Database {
    private static Connection connection;
    private static final String DATABASE_URL = "jdbc:sqlite:" + Database.class.getResource("/com/hust/aims/database/aims.sqlite");
    private Database() {
    }

    public static Connection getConnection() {
        try {
            if (connection == null || connection.isClosed()) {
                // Create a new connection if it doesn't exist or is closed
                connection = DriverManager.getConnection(DATABASE_URL);

                if (connection != null) {
                    System.out.println("Connected to database");
                } else {
                    System.out.println("Connect to database fail");
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return connection;
    }

    public static void closeConnection() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
