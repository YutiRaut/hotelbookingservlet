package com.example.hotelbookingservlet.Servlet;

import com.example.hotelbookingservlet.DAO.DAOException;
import com.example.hotelbookingservlet.DAO.RoomDao;
import com.example.hotelbookingservlet.DAO.ViewDetailsDao;
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

public class ViewRoomInformationServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
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

        // Forward the request to the next page for displaying the room data
        RequestDispatcher dispatcher = req.getRequestDispatcher("RoomInformation.jsp");
        dispatcher.forward(req, resp);
    }


    }


