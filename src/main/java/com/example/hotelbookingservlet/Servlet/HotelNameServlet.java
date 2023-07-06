package com.example.hotelbookingservlet.Servlet;

import com.example.hotelbookingservlet.DAO.DAOException;
import com.example.hotelbookingservlet.DAO.HotelDao;
import com.example.hotelbookingservlet.Model.Hotel;
import com.example.hotelbookingservlet.Model.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;


public class HotelNameServlet extends HttpServlet {
HotelDao hotelDao = new HotelDao();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            HttpSession session = req.getSession();
            User user = (User) session.getAttribute("CurrentUser");

            int userId = user.getUserId();
            HotelDao.getHotelName(userId);
            List<Hotel> hotelName = hotelDao.getHotelName(userId);
            req.setAttribute("hotelName", hotelName);
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("ViewRoomInformationServlet");
            requestDispatcher.forward(req, resp);

        } catch (DAOException e) {
            e.printStackTrace();
        }
    }
}

