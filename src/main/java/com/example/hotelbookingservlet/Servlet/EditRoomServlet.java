package com.example.hotelbookingservlet.Servlet;

import com.example.hotelbookingservlet.DAO.DAOException;
import com.example.hotelbookingservlet.DAO.RoomDao;
import com.example.hotelbookingservlet.Model.Room;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class EditRoomServlet extends HttpServlet {
Room room= new Room();
RoomDao roomDao = new RoomDao();
    int roomId;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try {


            roomId = Integer.parseInt(req.getParameter("id"));
            RoomDao.getRoomDataForEdit(roomId);
            List<Room> roomdataList = null;
            roomdataList = RoomDao.getRoomDataForEdit(roomId);
            req.setAttribute("roomList1", roomdataList);

        } catch (DAOException e) {
            e.printStackTrace();
        }

        req.getRequestDispatcher("EditRoomDetails.jsp").forward(req, resp);
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        int preCount = Integer.parseInt(req.getParameter("Count"));
        int preNoP = Integer.parseInt(req.getParameter("NOP"));
        String preFacility = req.getParameter("aminities");
        int prePrice = Integer.parseInt(req.getParameter("roomPrice"));

        room.setRoomCount(preCount);
        room.setRoomPrice(prePrice);
        room.setNoOfPeople(preNoP);
        room.setAminities(preFacility);

        try {
            roomDao.editedRoomData(room, roomId);
        } catch (DAOException e) {
            e.printStackTrace();
        }
        req.getRequestDispatcher("Welcome.jsp").forward(req, resp);
    }
}
