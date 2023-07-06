package com.example.hotelbookingservlet.Servlet;

import com.example.hotelbookingservlet.DAO.DAOException;
import com.example.hotelbookingservlet.DAO.HotelDao;
import com.example.hotelbookingservlet.DAO.RoomDao;
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

public class RoomCountServlet extends HttpServlet {

    Room room = new Room();
    RoomDao roomDao = new RoomDao();
    Hotel hotel = new Hotel();
    HotelDao hotelDao = new HotelDao();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("CurrentUser");

        String roomType = req.getParameter("Premium");
        int preCount = Integer.parseInt(req.getParameter("premiumCount"));
        int prePrice = Integer.parseInt(req.getParameter("prePrice"));
        int preNoP = Integer.parseInt(req.getParameter("preNoP"));
        String preFacility = req.getParameter("preFacility");

        room.setRoomType(roomType);
        room.setRoomCount(preCount);
        room.setRoomPrice(prePrice);
        room.setNoOfPeople(preNoP);
        room.setAminities(preFacility);

        try {

            hotel = hotelDao.getHotelId(user.getUserId());
            int hotelId = hotel.getHotelId();
            roomDao.roomRegistration(room, hotelId);

        } catch (DAOException e) {
            e.printStackTrace();
        }


        String semiRoomType = req.getParameter("SemiDeluxe");
        int semiCount = Integer.parseInt(req.getParameter("semiCount"));
        int semiPrice = Integer.parseInt(req.getParameter("semiPrice"));
        int semiNoP = Integer.parseInt(req.getParameter("semiNoP"));
        String semiFacility = req.getParameter("semiFacility");

        room.setRoomType(semiRoomType);
        room.setRoomCount(semiCount);
        room.setRoomPrice(semiPrice);
        room.setNoOfPeople(semiNoP);
        room.setAminities(semiFacility);

        try {
            hotel = hotelDao.getHotelId(user.getUserId());
            int hotelId = hotel.getHotelId();
            roomDao.roomRegistration(room, hotelId);
        } catch (DAOException e) {
            e.printStackTrace();
        }

        String deluxe = req.getParameter("Deluxe");
        int deluxeCount = Integer.parseInt(req.getParameter("deluxeCount"));
        int deluxePrice = Integer.parseInt(req.getParameter("deluxePrice"));
        int deluxeNoP = Integer.parseInt(req.getParameter("deluxeNoP"));
        String deluxeFacility = req.getParameter("deluxeFacility");

        room.setRoomType(deluxe);
        room.setRoomCount(deluxeCount);
        room.setRoomPrice(deluxePrice);
        room.setNoOfPeople(deluxeNoP);
        room.setAminities(deluxeFacility);
        try {
            hotel = hotelDao.getHotelId(user.getUserId());
            int hotelId = hotel.getHotelId();
            roomDao.roomRegistration(room, hotelId);
        } catch (DAOException e) {
            e.printStackTrace();
        }

        String suite = req.getParameter("Suite");
        int suiteCount = Integer.parseInt(req.getParameter("suiteCount"));
        int suitePrice = Integer.parseInt(req.getParameter("suitePrice"));
        int suitNoP = Integer.parseInt(req.getParameter("suitNoP"));
        String suiteFacility = req.getParameter("suiteFacility");

        room.setRoomType(suite);
        room.setRoomCount(suiteCount);
        room.setRoomPrice(suitePrice);
        room.setNoOfPeople(suitNoP);
        room.setAminities(suiteFacility);

        try {
            hotel = hotelDao.getHotelId(user.getUserId());
            int hotelId = hotel.getHotelId();
            roomDao.roomRegistration(room, hotelId);
        } catch (DAOException e) {
            e.printStackTrace();
        }

        RequestDispatcher requestDispatcher = req.getRequestDispatcher("Welcome.jsp");
        requestDispatcher.forward(req, resp);

    }
}
