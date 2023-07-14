package com.example.hotelbookingservlet;


import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;

public class HelloServlet extends HttpServlet {
    private String message;

    public void init() {
        message = "connection done!!";
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("text/html");

        // Hello
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/hotel_booking";
            String username = "root";
            String password = "";
            Connection connection;
            connection = DriverManager.getConnection(url, username, password);
            if (connection != null) {
                PrintWriter out = response.getWriter();
                out.println("<html><body>");
                out.println("<h1>" + message + "</h1>");
                out.println("</body></html>");
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Database exception");
        }
    }
}