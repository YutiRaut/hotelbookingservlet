package com.example.hotelbookingservlet.DAO;

import com.example.hotelbookingservlet.Model.Address;
import com.example.hotelbookingservlet.Model.Hotel;
import com.example.hotelbookingservlet.Model.Room;
import com.example.hotelbookingservlet.Model.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ViewDetailsDao {

    public List<User> getAllData(int userId) throws DAOException {

        List<User> datalist = new ArrayList<>();
        try {
            String viewDetails = "select user_name,email,contact_no,password from user where id=?";
            PreparedStatement statement = DbConnection.getInstance().getMainConnection().prepareStatement(viewDetails);
            statement.setInt(1, userId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                User user = new User();
                user.setName(resultSet.getString(1));
                user.setEmail(resultSet.getString(2));
                user.setContact(resultSet.getString(3));
                user.setPassword(resultSet.getString(4));
                datalist.add(user);
            }
        } catch (SQLException e) {
            throw new DAOException("error occured", e);
        }

        return datalist;
    }






}

