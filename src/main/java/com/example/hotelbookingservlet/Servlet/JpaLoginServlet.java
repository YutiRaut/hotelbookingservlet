package com.example.hotelbookingservlet.Servlet;

import com.example.hotelbookingservlet.Common.Constant;
import com.example.hotelbookingservlet.Common.ErrorUtil;
import com.example.hotelbookingservlet.Common.Validation;
import com.example.hotelbookingservlet.DAO.DAOException;
import com.example.hotelbookingservlet.DAO.UserDao;
import com.example.hotelbookingservlet.JPAModel.JPAUser;
import com.example.hotelbookingservlet.Model.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class JpaLoginServlet extends HttpServlet {
    ErrorUtil errorUtil = new ErrorUtil();


    @Override
    public void init() throws ServletException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String email = request.getParameter("username");
        String password = request.getParameter("password");

        JPAUser user = null;
        try {
          user = UserDao.jpaCheckUserCredentials(email, password);
        } catch (DAOException e) {
            e.printStackTrace();
        }


        if (Validation.isEmpty(email) && Validation.isEmpty(password)) {
            errorUtil.addErrorMessage("Email Or Password can't be Empty");
            request.setAttribute("errorUtil", errorUtil);
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("Login.jsp");
            requestDispatcher.forward(request, response);
        }else {
            if (user != null) {
                HttpSession session = request.getSession();
                session.setAttribute("CurrentUser", user);
                if (!(user.isVerified())) {
                    request.getRequestDispatcher("VerifyUser.jsp").forward(request, response);
                } else {
                    checkUserRole(request, response);
                }
            } else {
                errorUtil.addErrorMessage("Invalid Credentials!!");
                request.setAttribute("InvalidError", errorUtil);
                RequestDispatcher requestDispatcher = request.getRequestDispatcher("Login.jsp");
                requestDispatcher.forward(request, response);
            }
        }

    }

    public void checkUserRole(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession();
        JPAUser user = (JPAUser) session.getAttribute("CurrentUser");

        if (user.getRole() == Constant.SYSTEM_ADMIN) {
            request.getRequestDispatcher("index.jsp").forward(request,response);
        } else if (user.getRole() == Constant.HOTEL_ADMIN) {
            request.getRequestDispatcher("Welcome.jsp").forward(request,response);
        } else if (user.getRole() == Constant.Customer) {
            request.getRequestDispatcher("index.jsp").forward(request,response);
        }

    }


}

