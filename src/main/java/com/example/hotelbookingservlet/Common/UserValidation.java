package com.example.hotelbookingservlet.Common;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UserValidation extends HttpServlet {
        protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            // Retrieve form parameter values
            String name = request.getParameter("name");
            String email = request.getParameter("email");
            String password = request.getParameter("password");
            String contact=request.getParameter("contact");
            // Initialize a list to store validation errors
            List<String> errors = new ArrayList<>();

            // Validate name field
            if (name == null || name.trim().isEmpty()) {
                errors.add("Name is required.");
            }

            // Validate email field using a simple regular expression
            if (email == null || !email.matches("\\b[\\w.%-]+@[-.\\w]+\\.[A-Za-z]{2,4}\\b")) {
                errors.add("Invalid email address.");
            }

            // Validate password field
            if (password == null || password.length() < 8) {
                errors.add("Password must be at least 8 characters long.");
            }

            // Validate role field

            if(contact==null|| contact.trim().isEmpty()){
                errors.add("contact is required.");
            }

            if (errors.isEmpty()) {
                // Process successful registration
                // Add your registration logic here, such as storing user details in a database
                // You may also redirect the user to a success page
                request.getRequestDispatcher("Login.jsp").forward(request, response);
            } else {
                // Display validation errors to the user
                request.setAttribute("errors", errors);
                request.getRequestDispatcher("RegistrationServlet").forward(request, response);
            }
        }
    }

