package com.example.hotelbookingservlet.Servlet;

import com.example.hotelbookingservlet.DAO.AddressDao;
import com.example.hotelbookingservlet.DAO.DAOException;
import com.example.hotelbookingservlet.DAO.HotelDao;
import com.example.hotelbookingservlet.Model.Address;
import com.example.hotelbookingservlet.Model.Hotel;
import com.example.hotelbookingservlet.Model.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class HotelRegistrationServlet extends HttpServlet {

    Address addressG = new Address();
    AddressDao addressDao = new AddressDao();
    Hotel hotel = new Hotel();
    HotelDao registerHotelDao = new HotelDao();


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try {
            List<Address> addresses = addressDao.getState();
            req.setAttribute("stateID", addresses);
            if (req.getParameter("stateid") != null) {
                int stateId = Integer.parseInt(req.getParameter("stateid"));
                List<Address> addresses1 = addressDao.getCity(stateId);
                req.setAttribute("cityID", addresses1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (DAOException e) {
            e.printStackTrace();
        }
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("HotelRegistrationForm.jsp");
        requestDispatcher.forward(req, resp);


    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String hotelName = req.getParameter("HotelName");
        String licenceNo = req.getParameter("LicenceNo");
        String starRating = req.getParameter("StarRating");
        String GstNo = req.getParameter("GstNo");
        String permits = req.getParameter("permits");
        String address = req.getParameter("address");
        String pincode = req.getParameter("pincode");

        addressG.setAddress(address);
        addressG.setPincode(Integer.parseInt(pincode));
        int cityId = Integer.parseInt(req.getParameter("City"));
        addressG.setCityId(cityId);
        try {
            addressDao.addAddress(addressG, cityId);
            addressG = addressDao.getAddress();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (DAOException e) {
            e.printStackTrace();
        }


        hotel.setHotelName(hotelName);
        hotel.setLicenceNo(licenceNo);
        hotel.setStarRating(Integer.parseInt(starRating));
        hotel.setGstNo(GstNo);
        hotel.setPermits(permits);

        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("CurrentUser");
        int addressId = addressG.getAddressId();
        try {
            registerHotelDao.addHotelAdmin(user.getUserId(), hotel, addressId);
        } catch (DAOException e) {
            e.printStackTrace();
        }

        RequestDispatcher requestDispatcher = req.getRequestDispatcher("Welcome.jsp");
        requestDispatcher.forward(req, resp);

    }
}
