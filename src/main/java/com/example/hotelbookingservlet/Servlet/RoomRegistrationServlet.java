package com.example.hotelbookingservlet.Servlet;

import com.example.hotelbookingservlet.DAO.DAOException;
import com.example.hotelbookingservlet.DAO.HotelDao;
import com.example.hotelbookingservlet.DAO.RoomDao;
import com.example.hotelbookingservlet.Model.Hotel;
import com.example.hotelbookingservlet.Model.Room;
import com.example.hotelbookingservlet.Model.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.*;
import java.io.*;
import java.util.List;
@MultipartConfig

public class RoomRegistrationServlet extends HttpServlet {

    Room room = new Room();
    RoomDao roomDao = new RoomDao();
    HotelDao hotelDao = new HotelDao();
    int hotelId;


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

        RequestDispatcher requestDispatcher = req.getRequestDispatcher("RoomRegistration.jsp");
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServletContext servletContext = getServletContext();
        Part imagePart = req.getPart("preimage");

        String imageFileName = getFileName(imagePart);
        String preuploadDirectory = uploadFileAndGetImagePath(imagePart, imageFileName, servletContext);




        hotelId = Integer.parseInt(req.getParameter("hotel"));
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
        room.setImage(preuploadDirectory);
        try {

            roomDao.roomRegistration(room, hotelId);

        } catch (DAOException e) {
            e.printStackTrace();
        }

                    //for semi deluxe
        Part semiimagePart = req.getPart("semiimage");

        String semiimageFileName = getFileName(semiimagePart);
        String semiuploadDirectory = uploadFileAndGetImagePath(semiimagePart, semiimageFileName, servletContext);

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
        room.setImage(semiuploadDirectory);
        try {

            roomDao.roomRegistration(room, hotelId);
        } catch (DAOException e) {
            e.printStackTrace();
        }

                        //for deluxe
        Part deimagePart = req.getPart("deimage");

        String deimageFileName = getFileName(deimagePart);
        String deuploadDirectory = uploadFileAndGetImagePath(deimagePart, deimageFileName, servletContext);
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
        room.setImage(deuploadDirectory);
        try {

            roomDao.roomRegistration(room, hotelId);
        } catch (DAOException e) {
            e.printStackTrace();
        }
        Part suitimagePart = req.getPart("suitimage");

        String suitimageFileName = getFileName(suitimagePart);
        String suituploadDirectory = uploadFileAndGetImagePath(suitimagePart, suitimageFileName, servletContext);
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
        room.setImage(suituploadDirectory);
        try {
            roomDao.roomRegistration(room, hotelId);
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
