package com.example.hotelbookingservlet.Servlet;

import com.example.hotelbookingservlet.DAO.DAOException;
import com.example.hotelbookingservlet.DAO.RoomDao;
import com.example.hotelbookingservlet.DAO.ViewDetailsDao;
import com.example.hotelbookingservlet.Model.Hotel;
import com.example.hotelbookingservlet.Model.Room;
import com.example.hotelbookingservlet.Model.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class ViewRoomInformationServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ViewDetailsDao viewDetailsDao = new ViewDetailsDao();

        Hotel hotel = new Hotel();
        RoomDao roomDao = new RoomDao();


        try {

            HttpSession session = req.getSession();
            User user = (User) session.getAttribute("CurrentUser");
            int userId = user.getUserId();
            RoomDao.getAllRoomData(userId);
            List<Room> roomList = null;
            roomList = RoomDao.getAllRoomData(userId);
            req.setAttribute("roomList", roomList);

        } catch (DAOException e) {
            e.printStackTrace();
        }


        req.getRequestDispatcher("ViewRoomInformation.jsp").forward(req, resp);


    }
}

