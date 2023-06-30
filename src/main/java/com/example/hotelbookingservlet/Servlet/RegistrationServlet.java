package com.example.hotelbookingservlet.Servlet;


import com.example.hotelbookingservlet.Common.EmailValidator;
import com.example.hotelbookingservlet.Common.ErrorUtil;
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
import java.sql.SQLException;
import java.util.List;

public class RegistrationServlet extends HttpServlet {
    LoginDao loginDao = new LoginDao();
    OtpGenarator sendOtp = new OtpGenarator();
    RoleDao roleDao=new RoleDao();


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try {
            List<Role> roles=roleDao.getRole();
            req.setAttribute("Role",roles);
            RequestDispatcher requestDispatcher= req.getRequestDispatcher("Registration.jsp");
            requestDispatcher.forward(req,resp);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String name = req.getParameter("name");
        String email = req.getParameter("email");
        String contact = req.getParameter("contact");
        String password = req.getParameter("password");
        String role = (req.getParameter("role"));
        String verification = req.getParameter("VerificationCode");
        User user = new User();
        user.setName(name);
        System.out.println(name);
        user.setEmail(email);
        user.setContact(contact);
        user.setPassword(password);
        user.setRole(Integer.parseInt(role));
        String code = sendOtp.generatesOtp();
        user.setVerificationCode(code);
        //List<ErrorUtil> errorList = UserValidation.validateUser(user);
        //if(!errorList.isEmpty()){

        try {
            loginDao.addUser(user);
            sendMail(user);
        } catch (DAOException e) {
            e.printStackTrace();
        } catch (MessagingException e) {
            e.printStackTrace();
        }


        resp.sendRedirect("Login.jsp");

    }

    private void fillSignUpMasterData(HttpServletRequest request) throws SQLException {
        RoleDao roleDao = new RoleDao();
        List<Role> roles= roleDao.getRole();
        request.setAttribute("Role",roles);

    }

    private void sendMail(User user) throws MessagingException {
        EmailValidator sendmail = new EmailValidator();
        StringBuilder mailContent = new StringBuilder();
        mailContent.append("<H1>").append("Hi, ").append(user.getName()).append(" ").append("</H1>").append("Your verification code :").append(user.getVerificationCode());
        sendmail.sendsOtp(user.getEmail(), "Traveller:Account verification code", mailContent.toString());
    }


}

