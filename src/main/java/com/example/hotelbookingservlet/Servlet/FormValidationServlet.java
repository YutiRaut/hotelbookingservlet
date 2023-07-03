package com.example.hotelbookingservlet.Servlet;

import com.example.hotelbookingservlet.Common.ErrorUtil;
import com.example.hotelbookingservlet.Common.Validation;
import com.example.hotelbookingservlet.Model.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class FormValidationServlet extends HttpServlet {
    ErrorUtil errorUtil = new ErrorUtil();
    User user = new User();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("username");
        String password = req.getParameter("password");

        if (Validation.isEmpty(email) && Validation.isEmpty(password)) {
            errorUtil.addErrorMessage("Email Or Password can't be Empty");
            req.setAttribute("errorUtil", errorUtil);
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("Login.jsp");
            requestDispatcher.forward(req, resp);
        } else {
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("/LoginServlet");
            requestDispatcher.forward(req, resp);

        }

    }


}







