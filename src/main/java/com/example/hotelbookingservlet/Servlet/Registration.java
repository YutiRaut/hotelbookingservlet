package com.example.hotelbookingservlet.Servlet;

import com.example.hotelbookingservlet.DAO.DAOException;
import com.example.hotelbookingservlet.DAO.LoginDao;
import com.example.hotelbookingservlet.Model.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Registration extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String name = req.getParameter("name");
        String email = req.getParameter("email");
        String contact = req.getParameter("contact");
        String password = req.getParameter("password");
        int role = Integer.parseInt(req.getParameter("role"));
        User user = new User();
        user.setName(name);
        user.setEmail(email);
        user.setContact(contact);
        user.setPassword(password);
        user.setRole(role);


        try {
            LoginDao.addUser(user);
        } catch (DAOException e) {
            e.printStackTrace();
        }
        resp.sendRedirect("Login.jsp");
    }
}
