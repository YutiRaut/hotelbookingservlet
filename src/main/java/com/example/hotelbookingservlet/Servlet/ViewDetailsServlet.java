package com.example.hotelbookingservlet.Servlet;

import com.example.hotelbookingservlet.DAO.DAOException;
import com.example.hotelbookingservlet.DAO.ViewDetailsDao;
import com.example.hotelbookingservlet.Model.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class ViewDetailsServlet extends HttpServlet {

    ViewDetailsDao viewDetailsDao = new ViewDetailsDao();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("CurrentUser");

        int userId = user.getUserId();
        try {
            viewDetailsDao.getAllData(userId);
        } catch (DAOException e) {
            e.printStackTrace();
        }
        List<User> datalist = null;
        try {
            datalist = viewDetailsDao.getAllData(userId);
        } catch (DAOException e) {
            e.printStackTrace();
        }
        req.setAttribute("datalist", datalist);

        req.getRequestDispatcher("EditHotelInformation.jsp").forward(req, resp);


    }
}
