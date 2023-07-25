package com.example.hotelbookingservlet.Servlet;
import com.example.hotelbookingservlet.DAO.DAOException;
import com.example.hotelbookingservlet.DAO.HotelDao;
import com.example.hotelbookingservlet.DAO.ViewDetailsDao;
import com.example.hotelbookingservlet.Model.Hotel;
import com.example.hotelbookingservlet.Model.User;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class ViewHotelDetailsServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ViewDetailsDao viewDetailsDao = new ViewDetailsDao();

        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("CurrentUser");

        int userId = user.getUserId();
        try {
            HotelDao.getAllHotelData(userId);
        } catch (DAOException e) {
            e.printStackTrace();
        }
        List<Hotel> GethotelList = null;
        try {
            GethotelList = HotelDao.getAllHotelData(userId);
        } catch (DAOException e) {
            e.printStackTrace();
        }

        req.setAttribute("hotelList", GethotelList);
        req.getRequestDispatcher("ViewHotelInformation.jsp").forward(req, resp);
    }
}

