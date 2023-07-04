package com.example.hotelbookingservlet.Servlet;

import com.example.hotelbookingservlet.DAO.DAOException;
import com.example.hotelbookingservlet.DAO.RegisterHotelDao;
import com.example.hotelbookingservlet.DAO.ViewDetailsDao;
import com.example.hotelbookingservlet.Model.Hotel;
import com.example.hotelbookingservlet.Model.Room;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ViewRoomInformationServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ViewDetailsDao viewDetailsDao = new ViewDetailsDao();

        Hotel hotel = new Hotel();
        RegisterHotelDao hotelDao = new RegisterHotelDao();


        try {

            hotel = hotelDao.getHotelId();
            int hotelId = hotel.getHotelId();
            viewDetailsDao.getAllRoomData(hotelId);
            List<Room> roomList = null;
            roomList = viewDetailsDao.getAllRoomData(hotelId);
            req.setAttribute("roomList", roomList);

        } catch (DAOException e) {
            e.printStackTrace();
        }


        req.getRequestDispatcher("ViewRoomInformation.jsp").forward(req, resp);


    }
}

