package com.example.hotelbookingservlet.Servlet;

import com.example.hotelbookingservlet.Common.Constant;
import com.example.hotelbookingservlet.DAO.UserDao;
import com.example.hotelbookingservlet.JPAModel.JPAUser;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class VerifyUserByCodeServlet extends HttpServlet {

    UserDao userDao = new UserDao();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        JPAUser user = (JPAUser) req.getSession().getAttribute("CurrentUser");
        String code = req.getParameter("VerificationCode");

        if (code.equals(user.getVerificationCode())) {
            JPAUser user1 = userDao.getUser(user.getUserId());
//            user1.setEmail("yutiraut2002@gmail.com");
            user1.setVerified(true);
            userDao.updateUser(user1);
            //userDao.jpaUpdateUserIsVerified(user.setVerified(true), user.getEmail());
            checkUserRole(req, resp);

        } else {
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("VerifyUser.jsp");
            requestDispatcher.forward(req, resp);

        }
    }

    public void checkUserRole(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession();
        JPAUser user = (JPAUser) session.getAttribute("CurrentUser");

        if (user.getRole() == Constant.SYSTEM_ADMIN) {
            request.getRequestDispatcher("index.jsp").forward(request, response);
        } else if (user.getRole() == Constant.HOTEL_ADMIN) {
            request.getRequestDispatcher("Welcome.jsp").forward(request, response);
        } else if (user.getRole() == Constant.Customer) {
            request.getRequestDispatcher("index.jsp").forward(request, response);
        }

    }


}
