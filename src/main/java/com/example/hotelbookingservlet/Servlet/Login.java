package com.example.hotelbookingservlet.Servlet;

import com.example.hotelbookingservlet.Common.Constant;
import com.example.hotelbookingservlet.DAO.DAOException;
import com.example.hotelbookingservlet.DAO.LoginDao;
import com.example.hotelbookingservlet.Model.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

public class Login extends HttpServlet {
    @Override
    public void init() throws ServletException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        PrintWriter out = response.getWriter();
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
            } else if(user.getRole()==Constant.HOTEL_ADMIN) {
                request.setAttribute("name_key", user.getName());
                RequestDispatcher requestDispatcher = request.getRequestDispatcher("/Welcome.jsp");
                requestDispatcher.forward(request, response);
            }
        } else {
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("/Login.jsp");
            requestDispatcher.include(request, response);
            response.setContentType("text/html");
            out.println("<h2 style='color:red'>Invalid Credentials!!!</h2>");
        }


    }
}
