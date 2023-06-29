package com.example.hotelbookingservlet.Servlet;

import com.example.hotelbookingservlet.Common.Constant;
import com.example.hotelbookingservlet.DAO.DAOException;
import com.example.hotelbookingservlet.DAO.LoginDao;
import com.example.hotelbookingservlet.Model.Role;
import com.example.hotelbookingservlet.Model.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

public class LoginServlet extends HttpServlet {



    @Override
    public void init() throws ServletException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String name = request.getParameter("username");
        String password = request.getParameter("password");

        User user = null;
        try {
            user = LoginDao.checkUserCredentials(name, password);
        } catch (DAOException e) {
            e.printStackTrace();
        }

        if (user != null) {
            HttpSession session = request.getSession();
            session.setAttribute("CurrentUser", user);
            if (!(user.isVerified())) {
                response.sendRedirect("VerifyUser.jsp");
            } else {
                checkUserRole(request,response);
            }
        } else {
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("/LoginServlet.jsp");
            requestDispatcher.include(request, response);
            response.setContentType("text/html");
        }



    }

    public void checkUserRole(HttpServletRequest request,HttpServletResponse response) throws IOException {
        HttpSession session =request.getSession();
        User user = (User) session.getAttribute("CurrentUser");

        if (user.getRole() == Constant.SYSTEM_ADMIN) {
            response.sendRedirect("index.jsp");
        } else if (user.getRole()==Constant.HOTEL_ADMIN) {
            response.sendRedirect("Welcome.jsp");
        } else if (user.getRole()==Constant.Customer) {
            response.sendRedirect("index.jsp");
        }

       }



    }

