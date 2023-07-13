package com.example.hotelbookingservlet.Servlet;

import com.example.hotelbookingservlet.DAO.AddressDao;
import com.example.hotelbookingservlet.DAO.DAOException;
import com.example.hotelbookingservlet.DAO.HotelDao;
import com.example.hotelbookingservlet.Model.Address;
import com.example.hotelbookingservlet.Model.Hotel;
import com.example.hotelbookingservlet.Model.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class EditDetailsServlet extends HttpServlet {
    Hotel hotel= new Hotel();
    Address addressData= new Address();
    HotelDao hotelDao= new HotelDao();
    AddressDao addressDao= new AddressDao();
    int hotelID;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        hotelID= Integer.parseInt(req.getParameter("id"));
        try {
            HotelDao.getDataForEdit(hotelID);
            List<Hotel> EditList = null;
            EditList = HotelDao.getDataForEdit(hotelID);
            req.setAttribute("hotelList1", EditList);

            List<Address> addresses = addressDao.getState();
            req.setAttribute("stateID1", addresses);
            if (req.getParameter("stateid1") != null) {
                int stateId = Integer.parseInt(req.getParameter("stateid1"));

                List<Address> addresses1 = addressDao.getCity(stateId);
                req.setAttribute("cityID1", addresses1);
            }
        }catch (DAOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }


        req.getRequestDispatcher("EditHotelInformation.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String hotelName = req.getParameter("Hotelname");
        String licenceNo = req.getParameter("Licence");
        int starRating = Integer.parseInt(req.getParameter("starRating"));
        String GstNo = req.getParameter("GSTNo");
        String permits = req.getParameter("permities");
        String address = req.getParameter("Address");
        int pincode = Integer.parseInt(req.getParameter("Pincode"));

        hotel.setHotelName(hotelName);
        hotel.setLicenceNo(licenceNo);
        hotel.setStarRating(starRating);
        hotel.setGstNo(GstNo);
        hotel.setPermits(permits);
        addressData.setAddress(address);
        addressData.setPincode(pincode);
        hotel.setAddressline(addressData);

        try {
            hotelDao.addEditedData(hotel, hotelID);
        } catch (DAOException e) {
            e.printStackTrace();
        }

req.getRequestDispatcher("Welcome.jsp").forward(req,resp);
    }
}
