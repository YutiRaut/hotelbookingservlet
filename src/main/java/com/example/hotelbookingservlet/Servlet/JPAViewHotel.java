package com.example.hotelbookingservlet.Servlet;

import com.example.hotelbookingservlet.DAO.DAOException;
import com.example.hotelbookingservlet.DAO.HotelDao;
import com.example.hotelbookingservlet.DAO.ViewDetailsDao;
import com.example.hotelbookingservlet.JPAModel.HotelEntity;
import com.example.hotelbookingservlet.JPAModel.UserEntity;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;


    public class JPAViewHotel extends HttpServlet {

        @Override
        protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HotelDao hotelDao = new HotelDao();

            HttpSession session = req.getSession();
            UserEntity user = (UserEntity) session.getAttribute("CurrentUser");

            int userId = user.getUserId();
        hotelDao.getHotelDetails(userId);

            List<HotelEntity> hotelEntities = null;

                hotelEntities = hotelDao.getHotelDetails(userId);

            req.setAttribute("hotellist", hotelEntities);
            req.getRequestDispatcher("ViewHotelInformation.jsp").forward(req, resp);
        }
    }



