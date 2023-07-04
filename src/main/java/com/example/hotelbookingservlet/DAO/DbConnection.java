package com.example.hotelbookingservlet.DAO;

import javax.servlet.http.HttpServlet;
import java.sql.Connection;
import java.sql.SQLException;

import static java.sql.DriverManager.getConnection;

public class DbConnection extends HttpServlet {

    private static DbConnection dbConnection = new DbConnection();
    private Connection connection = null;
    private final String URL = "jdbc:mysql://localhost:3306/hotel-booking";
    private final String USERNAME = "root";
    private final String PASSWORD = "";

    private DbConnection() {

    }

    public static DbConnection getInstance() {
        return dbConnection;
    }

    public Connection getMainConnection() {
        try {
            if (connection == null) {
                Class.forName("com.mysql.cj.jdbc.Driver");
                connection = getConnection(URL, USERNAME, PASSWORD);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }
}

