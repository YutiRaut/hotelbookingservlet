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

    public List<Hotel> getAllHotelData(int userId) throws DAOException {

        List<Hotel> hotelList = new ArrayList<>();
        try {
            String viewDetails = "SELECT h.hotel_name,h.licence_no,h.star_rating,h.GST_No," +
                    "h.permites,a.address_line1,a.pincode,c.city_name, s.state_name FROM " +
                    "hotel h INNER JOIN address a on h.address_id = a.id INNER JOIN city c " +
                    "on a.city_id=c.id INNER JOIN state s on c.state_id =s.id WHERE h.user_id=?;";

            PreparedStatement statement = DbConnection.getInstance().getMainConnection().prepareStatement(viewDetails);
            statement.setInt(1, userId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Hotel hotel = new Hotel();
                hotel.setHotelName(resultSet.getString("hotel_name"));
                hotel.setLicenceNo(resultSet.getString("licence_no"));
                hotel.setStarRating(resultSet.getInt("star_rating"));
                hotel.setGstNo(resultSet.getString("GST_No"));
                hotel.setPermits(resultSet.getString("permites"));
                Address address = new Address(resultSet.getString("address_line1"),resultSet.getInt("pincode"),resultSet.getString("city_name"),resultSet.getString("state_name"));
                hotel.setAddress(address);
                hotelList.add(hotel);
            }
        } catch (SQLException e) {
            throw new DAOException("error occured", e);
        }

        return hotelList;
    }

    public List<Room> getAllRoomData(int hotelId) throws DAOException {

        List<Room> roomList = new ArrayList<>();
        try {
            String viewDetails = "select room_type,room_count,no_of_people,aminities,room_price from room where hotel_id=?";
            PreparedStatement statement = DbConnection.getInstance().getMainConnection().prepareStatement(viewDetails);
            statement.setInt(1, hotelId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Room room = new Room();
                room.setRoomType(resultSet.getString(1));
                room.setRoomCount(resultSet.getInt(2));
                room.setNoOfPeople(resultSet.getInt(3));
                room.setAminities(resultSet.getString(4));
                room.setRoomPrice(resultSet.getInt(5));

                roomList.add(room);
            }
        } catch (SQLException e) {
            throw new DAOException("error occured", e);
        }

        return roomList;
    }


}

