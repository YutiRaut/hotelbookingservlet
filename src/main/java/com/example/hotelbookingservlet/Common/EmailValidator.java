package com.example.hotelbookingservlet.Common;


import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class EmailValidator {

    public boolean sendsOtp(String email, String verificationCode, String content) throws MessagingException {

        String username = "travellerapptesting@gmail.com";
        String password = "wjdjunrnxljqfesu";
        String receiptMail = email;
        String msg = "your welcome code is " + verificationCode;
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(receiptMail));
            message.setSubject("Welcome to Traveller");
            message.setText(msg);
            message.setContent(content, "text/html");
            Transport.send(message);

        } catch (MessagingException e) {
            throw new MessagingException("Something went wrong. Please try again later.", e);
        }
        return true;

    }
}
