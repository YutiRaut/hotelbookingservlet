package com.example.hotelbookingservlet.Servlet;

import com.example.hotelbookingservlet.Common.ErrorUtil;
import com.example.hotelbookingservlet.Common.Validation;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RegistrationFormValidation extends HttpServlet {
    ErrorUtil errorUtil = new ErrorUtil();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        String contact = req.getParameter("contact");

        if (Validation.isEmpty(name)) {
            errorUtil.addErrorMessage("Enter Name");
        }
        if (Validation.isEmpty(email)) {
            errorUtil.addErrorMessage("Enter email");
        }
        if (Validation.isEmpty(contact)) {
            errorUtil.addErrorMessage("Enter contact");
        }
        if (Validation.isEmpty(password)) {
            errorUtil.addErrorMessage("Enter password");
        }
        if (!(errorUtil.getErrorMessages().isEmpty())) {
            req.setAttribute("RegisterError", errorUtil);
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("Registration.jsp");
            requestDispatcher.forward(req, resp);
        } else {
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("RegistrationServlet");
            requestDispatcher.forward(req, resp);
        }


    }
}
