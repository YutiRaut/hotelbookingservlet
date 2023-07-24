package com.example.hotelbookingservlet.Servlet;


import com.example.hotelbookingservlet.Common.EmailValidator;
import com.example.hotelbookingservlet.Common.OtpGenarator;
import com.example.hotelbookingservlet.DAO.DAOException;
import com.example.hotelbookingservlet.DAO.UserDao;
import com.example.hotelbookingservlet.DAO.RoleDao;
import com.example.hotelbookingservlet.JPAModel.JPAUser;
import com.example.hotelbookingservlet.Model.Role;


import javax.mail.MessagingException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class JPARegistrationServlet extends HttpServlet {
    UserDao userDao= new UserDao();
    OtpGenarator sendOtp = new OtpGenarator();
    RoleDao roleDao = new RoleDao();
    JPAUser user = new JPAUser();

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

        user.setName(name);
        user.setEmail(email);
        user.setContact(contact);
        user.setPassword(password);
        user.setRole(Integer.parseInt(role));
        String code = sendOtp.generatesOtp();
        user.setVerificationCode(code);

        userDao.JPAaddUser(user);
        try {
            sendMail(user);
        } catch (MessagingException e) {
            e.printStackTrace();
        }

        req.getRequestDispatcher("Login.jsp").forward(req,resp);

    }

    private void fillSignUpMasterData(HttpServletRequest request) throws SQLException {
        RoleDao roleDao = new RoleDao();
        List<Role> roles = roleDao.getRole();
        request.setAttribute("Role", roles);

    }

    private void sendMail(JPAUser user) throws MessagingException {
        EmailValidator sendmail = new EmailValidator();
        StringBuilder mailContent = new StringBuilder();
        mailContent.append("<H1>").append("Hi, ").append(user.getName()).append(" ").append("</H1>").append("Your verification code :").append(user.getVerificationCode());
        sendmail.sendsOtp(user.getEmail(), "Traveller:Account verification code", mailContent.toString());
    }


}

