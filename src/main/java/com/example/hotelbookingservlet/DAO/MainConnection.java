package com.example.hotelbookingservlet.DAO;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Connection;
import java.sql.SQLException;

import static java.sql.DriverManager.getConnection;

public class MainConnection extends HttpServlet{

        private static MainConnection dbConnection = new MainConnection();
        private Connection connection = null;
        private final String URL = "jdbc:mysql://localhost:3306/hotel-booking";
        private final String USERNAME = "root";
        private final String PASSWORD = "root";

    private MainConnection() {

        }

        public static MainConnection getInstance () {
            return dbConnection;
        }

        public Connection getMainConnection () {
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

