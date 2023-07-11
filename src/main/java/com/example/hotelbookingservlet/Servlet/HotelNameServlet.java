package com.example.hotelbookingservlet.Servlet;

import com.example.hotelbookingservlet.DAO.DAOException;
import com.example.hotelbookingservlet.DAO.HotelDao;
import com.example.hotelbookingservlet.DAO.RoomDao;
import com.example.hotelbookingservlet.Model.Address;
import com.example.hotelbookingservlet.Model.Hotel;
import com.example.hotelbookingservlet.Model.Room;
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
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("CurrentUser");

        int userId = user.getUserId();
        try {
            HotelDao.getHotelName(userId);
            List<Hotel> hotelName = hotelDao.getHotelName(userId);
            req.setAttribute("hotelName", hotelName);
        } catch (DAOException e) {
            e.printStackTrace();
        }




        RequestDispatcher requestDispatcher = req.getRequestDispatcher("ViewRoomInformation.jsp");
        requestDispatcher.forward(req, resp);
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("CurrentUser");

        int userId = user.getUserId();
            // Retrieve the hotel ID from the request
            int hotelId = Integer.parseInt(req.getParameter("hotel"));

            // Fetch room data based on the hotel ID
        List<Room> roomList = null;
        try {
            roomList = RoomDao.getAllRoomData(hotelId);
        } catch (DAOException e) {
            e.printStackTrace();
        }

        // Set the roomList attribute in the request
            req.setAttribute("roomList", roomList);

            // Retrieve the hotel name list again to redisplay the dropdown on the page
        List<Hotel> hotelNameList = null;
        try {
            hotelNameList = HotelDao.getHotelName(userId);
        } catch (DAOException e) {
            e.printStackTrace();
        }
        req.setAttribute("hotelName", hotelNameList);

            // Forward the request to the same page for redisplaying the dropdown and showing the room list
            RequestDispatcher dispatcher = req.getRequestDispatcher("ViewRoomInformation.jsp");
            dispatcher.forward(req, resp);
        }

    }


