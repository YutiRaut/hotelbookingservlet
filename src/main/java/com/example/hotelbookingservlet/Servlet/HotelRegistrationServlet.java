package com.example.hotelbookingservlet.Servlet;

import com.example.hotelbookingservlet.DAO.AddressDao;
import com.example.hotelbookingservlet.DAO.DAOException;
import com.example.hotelbookingservlet.Model.Address;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class HotelRegistrationServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        AddressDao addressDao=new AddressDao();
        Address address=new Address();
        try {
            List<Address> addresses=addressDao.getState();
            req.setAttribute("stateID",addresses);
            if(req.getParameter("stateid")!=null) {
                int stateId = Integer.parseInt(req.getParameter("stateid"));
                List<Address> addresses1 = addressDao.getCity(stateId);
                req.setAttribute("cityID", addresses1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (DAOException e) {
            e.printStackTrace();
        }
        RequestDispatcher requestDispatcher= req.getRequestDispatcher("HotelRegistrationForm.jsp");
        requestDispatcher.forward(req,resp);


    }
}
