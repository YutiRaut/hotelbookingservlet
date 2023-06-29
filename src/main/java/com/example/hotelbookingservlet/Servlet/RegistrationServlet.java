package com.example.hotelbookingservlet.Servlet;


import com.example.hotelbookingservlet.Common.EmailValidator;
import com.example.hotelbookingservlet.Common.OtpGenarator;
import com.example.hotelbookingservlet.DAO.DAOException;
import com.example.hotelbookingservlet.DAO.LoginDao;
import com.example.hotelbookingservlet.DAO.RoleDao;
import com.example.hotelbookingservlet.Model.Role;
import com.example.hotelbookingservlet.Model.User;

import javax.mail.MessagingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

public class RegistrationServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RoleDao roleDao=new RoleDao();
        try {
            List<Role> roles=roleDao.getRole();
            req.setAttribute("Role",roles);
            RequestDispatcher requestDispatcher= req.getRequestDispatcher("RegistrationServlet.jsp");
            requestDispatcher.forward(req,resp);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();

        LoginDao loginDao = new LoginDao();
        OtpGenarator sendOtp = new OtpGenarator();


        String name = req.getParameter("name");
        String email = req.getParameter("email");
        String contact = req.getParameter("contact");
        String password = req.getParameter("password");
        int role = Integer.parseInt(req.getParameter("role"));
        String verification = req.getParameter("VerificationCode");
        User user = new User();
        user.setName(name);
        user.setEmail(email);
        user.setContact(contact);
        user.setPassword(password);
        user.setRole(role);
        String code = sendOtp.generatesOtp();
        user.setVerificationCode(code);
        try {
            loginDao.addUser(user);
            sendMail(user);
        } catch (DAOException e) {
            e.printStackTrace();
        } catch (MessagingException e) {
            e.printStackTrace();
        }

        resp.sendRedirect("LoginServlet.jsp");

    }

    private void sendMail(User user) throws MessagingException {
        EmailValidator sendmail = new EmailValidator();
        StringBuilder mailContent = new StringBuilder();
        mailContent.append("<H1>").append("Hi, ").append(user.getName()).append(" ").append("</H1>").append("Your verification code :").append(user.getVerificationCode());
        sendmail.sendsOtp(user.getEmail(), "Traveller:Account verification code", mailContent.toString());
    }


}
