package com.example.hotelbookingservlet.Servlet;

import com.example.hotelbookingservlet.DAO.AddressDao;
import com.example.hotelbookingservlet.DAO.DAOException;
import com.example.hotelbookingservlet.DAO.HotelDao;
import com.example.hotelbookingservlet.Model.Address;
import com.example.hotelbookingservlet.Model.Hotel;
import com.example.hotelbookingservlet.Model.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.*;
import java.io.*;
import java.sql.SQLException;
import java.util.List;

@MultipartConfig

public class JPAHotelRegistration extends HttpServlet {

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
        ServletContext servletContext = getServletContext();
        Part imagePart = req.getPart("image");

        String imageFileName = getFileName(imagePart);
        String uploadDirectory = uploadFileAndGetImagePath(imagePart, imageFileName, servletContext);




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
        hotel.setImage(uploadDirectory);
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

    public static String getFileName(Part part) {
        String contentDisposition = part.getHeader("content-disposition");
        String[] elements = contentDisposition.split(";");
        for (String element : elements) {
            if (element.trim().startsWith("filename")) {
                return element.substring(element.indexOf('=') + 1).trim().replace("\"", "");
            }
        }
        return null;
    }


    public static String uploadFileAndGetImagePath(Part part, String fileName, ServletContext servletContext) throws IOException {
        String basePath = servletContext.getRealPath("/");
        String savePath = basePath + "hotel" + File.separator + fileName;
        File outputFile = new File(savePath);

        InputStream inputStream = part.getInputStream();
        OutputStream outputStream = new FileOutputStream(outputFile);
        int read;
        byte[] buffer = new byte[4096];
        while ((read = inputStream.read(buffer)) != -1) {
            outputStream.write(buffer, 0, read);
        }
        inputStream.close();
        outputStream.close();

        String imagePath = "hotel" + File.separator + fileName;
        return imagePath;
    }





}

