package com.example.hotelbookingservlet.Servlet;

import com.example.hotelbookingservlet.DAO.DAOException;
import com.example.hotelbookingservlet.DAO.UserDao;
import com.example.hotelbookingservlet.JPAModel.JPAUser;
import com.example.hotelbookingservlet.Model.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class VerifyUserByCodeServlet extends HttpServlet {

    UserDao userDao = new UserDao();
JpaLoginServlet jpaLoginServlet = new JpaLoginServlet();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        JPAUser user = (JPAUser) req.getSession().getAttribute("CurrentUser");
        String code = req.getParameter("VerificationCode");

        if (code.equals(user.getVerificationCode())) {

                 userDao.jpaUpdateUserIsVerified(user.setVerified(true), user.getEmail());
                jpaLoginServlet.checkUserRole(req, resp);
        } else {
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("VerifyUser.jsp");
            requestDispatcher.forward(req,resp);

        }

    }
}
