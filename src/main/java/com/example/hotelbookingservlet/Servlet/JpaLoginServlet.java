package com.example.hotelbookingservlet.Servlet;

import com.example.hotelbookingservlet.Common.Constant;
import com.example.hotelbookingservlet.Common.ErrorUtil;
import com.example.hotelbookingservlet.JPAModel.JPAUser;
import com.example.hotelbookingservlet.Model.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Date;
import java.util.List;

public class JpaLoginServlet extends HttpServlet {
    ErrorUtil errorUtil = new ErrorUtil();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("Login");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        String name = req.getParameter("username");
        String password = req.getParameter("password");


        List<JPAUser> existingRecord = entityManager.createQuery("SELECT s FROM User s WHERE s.email = :email AND s.password =:password", JPAUser.class)
                .setParameter("email", name)
                .setParameter("password", password)
                .getResultList();

        if (!(existingRecord.isEmpty())) {
            req.getRequestDispatcher("index.jsp").forward(req, resp);
        } else {
            errorUtil.addErrorMessage("Invalid Credentials!!");
            req.setAttribute("InvalidError", errorUtil);
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("Login.jsp");
            requestDispatcher.forward(req, resp);

        }
    }


    public void checkUserRole(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("CurrentUser");

        if (user.getRole() == Constant.SYSTEM_ADMIN) {
            request.getRequestDispatcher("index.jsp").forward(request, response);
        } else if (user.getRole() == Constant.HOTEL_ADMIN) {
            request.getRequestDispatcher("Welcome.jsp").forward(request, response);
        } else if (user.getRole() == Constant.Customer) {
            request.getRequestDispatcher("index.jsp").forward(request, response);
        }


    }
}