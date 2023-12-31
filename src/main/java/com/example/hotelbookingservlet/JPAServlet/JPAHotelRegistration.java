package com.example.hotelbookingservlet.JPAServlet;

import com.example.hotelbookingservlet.DAO.AddressDao;
import com.example.hotelbookingservlet.DAO.DAOException;
import com.example.hotelbookingservlet.DAO.HotelDao;
import com.example.hotelbookingservlet.JPAModel.*;

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

    AddressEntity addressEntity = new AddressEntity();
    AddressDao addressDao = new AddressDao();
    HotelEntity hotel = new HotelEntity();
    HotelDao hotelDao = new HotelDao();
    CityEntity cityEntity = new CityEntity();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try {
            List<StateEntity> state = addressDao.jpaGetState();
            req.setAttribute("stateID", state);
            if (req.getParameter("stateid") != null) {
                int stateId = Integer.parseInt(req.getParameter("stateid"));
                List<CityEntity> cityEntityList = addressDao.jpaGetCity(stateId);
                req.setAttribute("cityID", cityEntityList);
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
        addressEntity.setAddress(address);
        addressEntity.setPincode(Integer.parseInt(pincode));
        addressEntity.getAddressId();

        try {
            int cityId = Integer.parseInt(req.getParameter("City"));

            CityEntity cityEntity = new CityEntity();
            cityEntity.setCityId(cityId);
            addressEntity.setCityEntity(cityEntity);
            addressDao.jpaAddAddress(addressEntity);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        hotel.setHotelName(hotelName);
        hotel.setLicenceNo(licenceNo);
        hotel.setStarRating(Integer.parseInt(starRating));
        hotel.setGstNo(GstNo);
        hotel.setPermits(permits);
        hotel.setImage(uploadDirectory);

        HttpSession session = req.getSession();
        UserEntity user = (UserEntity) session.getAttribute("CurrentUser");

        addressEntity.setAddressId(addressEntity.getAddressId());
        hotel.setAddressEntity(addressEntity);
        UserEntity userEntity = new UserEntity();
        userEntity.setUserId(user.getUserId());
        hotel.setUserEntity(userEntity);
        hotelDao.JPAaddHotel(hotel);


        RequestDispatcher requestDispatcher = req.getRequestDispatcher("Welcome.jsp");
        requestDispatcher.forward(req, resp);

    }

//    @Override
//    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        ServletContext servletContext = getServletContext();
//        Part imagePart = req.getPart("image");
//
//        String imageFileName = getFileName(imagePart);
//        String uploadDirectory = uploadFileAndGetImagePath(imagePart, imageFileName, servletContext);
//
//        String hotelName = req.getParameter("HotelName");
//        String licenceNo = req.getParameter("LicenceNo");
//        String starRating = req.getParameter("StarRating");
//        String GstNo = req.getParameter("GstNo");
//        String permits = req.getParameter("permits");
//        String address = req.getParameter("address");
//        String pincode = req.getParameter("pincode");
//
//        addressEntity.setAddress(address);
//        addressEntity.setPincode(Integer.parseInt(pincode));
//
//        try {
//            int cityId = Integer.parseInt(req.getParameter("City"));
//            CityEntity cityEntity = new CityEntity();
//            cityEntity.setCityId(cityId);
//            addressEntity.setCityEntity(cityEntity);
//
//            // Merge the addressEntity to make sure it's managed
//            addressEntity=addressDao.jpaAddAddress(addressEntity);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//
//        hotel.setHotelName(hotelName);
//        hotel.setLicenceNo(licenceNo);
//        hotel.setStarRating(Integer.parseInt(starRating));
//        hotel.setGstNo(GstNo);
//        hotel.setPermits(permits);
//        hotel.setImage(uploadDirectory);
//
//        HttpSession session = req.getSession();
//        UserEntity user = (UserEntity) session.getAttribute("CurrentUser");
//
//        // Set the merged addressEntity into the hotel entity
//        hotel.setAddressEntity(addressEntity);
//
//        hotelDao.JPAaddHotel(user, hotel);
//
//        RequestDispatcher requestDispatcher = req.getRequestDispatcher("Welcome.jsp");
//        requestDispatcher.forward(req, resp);
//    }


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

