package com.example.hotelbookingservlet.Servlet;
import com.example.hotelbookingservlet.Common.EmailValidator;
import com.example.hotelbookingservlet.Common.Error;
import com.example.hotelbookingservlet.Common.OtpGenarator;
import com.example.hotelbookingservlet.Common.UserValidation;
import com.example.hotelbookingservlet.Common.Validation;
import com.example.hotelbookingservlet.DAO.DAOException;
import com.example.hotelbookingservlet.DAO.UserDao;
import com.example.hotelbookingservlet.DAO.RoleDao;

import com.example.hotelbookingservlet.DTO.JPASignupDto;
import com.example.hotelbookingservlet.DTO.SignupDto;
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
    UserDao loginDao = new UserDao();
    OtpGenarator sendOtp = new OtpGenarator();
    RoleDao roleDao = new RoleDao();
 SignupDto signupDto = new SignupDto();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try {
            List<Role> roles = roleDao.getRole();
            req.setAttribute("Role", roles);
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("Registration.jsp");
            requestDispatcher.forward(req, resp);
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

       signupDto.setName(name);
         signupDto.setEmail(email);
       signupDto.setContact(contact);
       signupDto.setPassword(password);
        signupDto.setRole(role);
        String code = sendOtp.generatesOtp();
       signupDto.setVerificationCode(code);
        List<Error>errorList= UserValidation.validateUser(signupDto);
        if(!errorList.isEmpty()){
            try {
                fillSignUpMasterData(req);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            req.setAttribute("errorList",errorList);
            req.getRequestDispatcher("Registration.jsp").forward(req,resp);

        }
        try {
            loginDao.addUser(signupDto);
            sendMail(signupDto);
        } catch (MessagingException e) {
            e.printStackTrace();
        } catch (DAOException e) {
            e.printStackTrace();
        }

        req.getRequestDispatcher("Login.jsp").forward(req,resp);

    }

    private void fillSignUpMasterData(HttpServletRequest request) throws SQLException {
        RoleDao roleDao = new RoleDao();
        List<Role> roles = roleDao.getRole();
        request.setAttribute("Role", roles);

    }

    private void sendMail(SignupDto user) throws MessagingException {
        EmailValidator sendmail = new EmailValidator();
        StringBuilder mailContent = new StringBuilder();
        mailContent.append("<H1>").append("Hi, ").append(user.getName()).append(" ").append("</H1>").append("Your verification code :").append(user.getVerificationCode());
        sendmail.sendsOtp(user.getEmail(), "Traveller:Account verification code", mailContent.toString());
    }


}

