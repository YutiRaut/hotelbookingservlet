package com.example.hotelbookingservlet.DAO;

import com.example.hotelbookingservlet.Model.Address;
import com.example.hotelbookingservlet.Model.Hotel;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class RegisterHotelDao {

    public void addHotelAdmin(int userId, Hotel hotels, int addressId) throws DAOException {
        try {
            String addQuery = "INSERT INTO hotel(hotel_name,licence_no,star_rating,GST_No,permites,user_id,address_id) values(?,?,?,?,?,?,?)";
            PreparedStatement stmt1;
            stmt1 = DbConnection.getInstance().getMainConnection().prepareStatement(addQuery);

            stmt1.setString(1, hotels.getHotelName());
            stmt1.setInt(2, hotels.getLicenceNo());
            stmt1.setInt(3, hotels.getStarRating());
            stmt1.setString(4, hotels.getGstNo());
            stmt1.setString(5, hotels.getPermits());
            stmt1.setInt(6,userId);
            stmt1.setInt(7,addressId);
            stmt1.executeUpdate();
        } catch (SQLException ex) {
            throw new DAOException("Opps something went wrong...", ex);
        } catch (Exception ex) {
            throw new DAOException("Opps something went wrong...", ex);
        }

    }






}
