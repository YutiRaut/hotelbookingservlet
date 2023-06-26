package com.example.hotelbookingservlet.DAO;

import com.example.hotelbookingservlet.Model.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginDao {

    public static User checkUserCredentials(String email, String password) throws DAOException {
        User user = null;
        try {
            String loginQuery = "select * from user where email=? AND password=?";
            PreparedStatement stmt2;
            stmt2 = MainConnection.getInstance().getMainConnection().prepareStatement(loginQuery);
            stmt2.setString(1, email);
            stmt2.setString(2, password);
            ResultSet resultSet = stmt2.executeQuery();
            if (resultSet.next()) {
                user = new User();
                user.setUserId(resultSet.getInt(1));
                user.setRole(resultSet.getInt(2));
                user.setName(resultSet.getString(3));
                user.setEmail(resultSet.getString(4));
                user.setContact(resultSet.getString(5));
                user.setPassword(resultSet.getString(6));
                user.setVerificationCode(resultSet.getString(7));
                user.setVerified(resultSet.getBoolean(8));
            }
            return user;
        } catch (SQLException ex) {
            throw new DAOException("Something went wrong...", ex);
        } catch (Exception ex) {
            throw new DAOException("Something went wrong...", ex);
        }
    }
}
