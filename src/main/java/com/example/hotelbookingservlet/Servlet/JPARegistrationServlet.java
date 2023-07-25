package com.example.hotelbookingservlet.Servlet;

import com.example.hotelbookingservlet.Common.EmailValidator;
import com.example.hotelbookingservlet.Common.Error;
import com.example.hotelbookingservlet.Common.OtpGenarator;
import com.example.hotelbookingservlet.Common.UserValidation;
import com.example.hotelbookingservlet.DAO.RoleDao;
import com.example.hotelbookingservlet.DAO.UserDao;
import com.example.hotelbookingservlet.DTO.JPASignupDto;
import com.example.hotelbookingservlet.JPAModel.JPARole;

import javax.mail.MessagingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class JPARegistrationServlet extends HttpServlet {
    UserDao userDao = new UserDao();
    OtpGenarator sendOtp = new OtpGenarator();
    RoleDao roleDao = new RoleDao();
    JPASignupDto user = new JPASignupDto();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        List<JPARole> roles = roleDao.roleList();
        req.setAttribute("Role", roles);
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("Registration.jsp");
        requestDispatcher.forward(req, resp);

    }

    @Override

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String name = req.getParameter("name");
        String email = req.getParameter("email");
        String contact = req.getParameter("contact");
        String password = req.getParameter("password");
        String role = req.getParameter("role");
        String verification = req.getParameter("VerificationCode");

        user.setName(name);
        user.setEmail(email);
        user.setContact(contact);
        user.setPassword(password);
        user.setRole(role);
        String code = sendOtp.generatesOtp();
        user.setVerificationCode(code);

        List<Error> errorList = UserValidation.JPAvalidateUser(user);
        if (!errorList.isEmpty()) {
            try {
                fillSignUpMasterData(req);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            req.setAttribute("errorList", errorList);
            req.getRequestDispatcher("Registration.jsp").forward(req, resp);

        } else {
            userDao.JPAaddUser(user);
            try {
                sendMail(user);
            } catch (MessagingException e) {
                e.printStackTrace();
            }
        }
        req.getRequestDispatcher("Login.jsp").forward(req, resp);

    }

    private void fillSignUpMasterData(HttpServletRequest request) throws SQLException {
        RoleDao roleDao = new RoleDao();
        List<JPARole> roles = roleDao.roleList();
        request.setAttribute("Role", roles);

    }

    private void sendMail(JPASignupDto user) throws MessagingException {
        EmailValidator sendmail = new EmailValidator();
        StringBuilder mailContent = new StringBuilder();
        mailContent.append("<H1>").append("Hi, ").append(user.getName()).append(" ").append("</H1>").append("Your verification code :").append(user.getVerificationCode());
        sendmail.sendsOtp(user.getEmail(), "Traveller:Account verification code", mailContent.toString());
    }


}

