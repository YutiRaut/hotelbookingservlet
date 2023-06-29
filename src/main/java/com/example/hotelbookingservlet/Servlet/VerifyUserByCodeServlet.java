package com.example.hotelbookingservlet.Servlet;

import com.example.hotelbookingservlet.DAO.DAOException;
import com.example.hotelbookingservlet.DAO.LoginDao;
import com.example.hotelbookingservlet.Model.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class VerifyUserByCodeServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        LoginDao loginDao = new LoginDao();
        LoginServlet loginServlet = new LoginServlet();
        User user = (User) req.getSession().getAttribute("CurrentUser");
        String code = req.getParameter("VerificationCode");

        if (code.equals(user.getVerificationCode())) {
            try {
                loginDao.updateUserIsVerified(user.setVerified(true), user.getEmail());
            } catch (DAOException e) {
                e.printStackTrace();
            }
            loginServlet.checkUserRole(req, resp);
        } else {
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("/VerifyUser.jsp");
            requestDispatcher.include(req, resp);
            resp.setContentType("text/html");
            out.println("<h2 style='color:red'>Invalid Otp!!!</h2>");
        }

    }
}
