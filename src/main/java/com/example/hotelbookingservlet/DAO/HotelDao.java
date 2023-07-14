package com.example.hotelbookingservlet.DAO;

import com.example.hotelbookingservlet.Model.Address;
import com.example.hotelbookingservlet.Model.Hotel;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class HotelDao {

    public void addHotelAdmin(int userId, Hotel hotels, int addressId) throws DAOException {
        try {
            String addQuery = "INSERT INTO hotel(hotel_name,licence_no,star_rating,GST_No,permites,images,user_id,address_id) values(?,?,?,?,?,?,?,?)";
            PreparedStatement stmt1;
            stmt1 = DbConnection.getInstance().getMainConnection().prepareStatement(addQuery);

            stmt1.setString(1, hotels.getHotelName());
            stmt1.setString(2, hotels.getLicenceNo());
            stmt1.setInt(3, hotels.getStarRating());
            stmt1.setString(4, hotels.getGstNo());
            stmt1.setString(5, hotels.getPermits());
            stmt1.setString(6,hotels.getImage());
            stmt1.setInt(7, userId);
            stmt1.setInt(8, addressId);
            stmt1.executeUpdate();
        } catch (SQLException ex) {
            throw new DAOException("Opps something went wrong...", ex);
        } catch (Exception ex) {
            throw new DAOException("Opps something went wrong...", ex);
        }

    }

    public Hotel getHotelId(int userId) throws DAOException {
        Hotel hotel = new Hotel();
        try {
            String getAddressQuery = "select * from hotel where user_id=?";
            PreparedStatement statement = DbConnection.getInstance().getMainConnection().prepareStatement(getAddressQuery);
            statement.setInt(1,userId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                hotel.setHotelId(resultSet.getInt(1));
            }
        } catch (SQLException e) {
            throw new DAOException("error occured", e);
        }

        return hotel;
    }

    public static List<Hotel> getAllHotelData(int userId) throws DAOException {

        List<Hotel> GethotelList = new ArrayList<>();
        try {
            String viewDetails = "SELECT h.id,h.hotel_name,h.licence_no,h.star_rating,h.GST_No," +
                    "h.permites,h.images,a.address_line1,a.pincode,c.city_name, s.state_name FROM " +
                    "hotel h INNER JOIN address a on h.address_id = a.id INNER JOIN city c " +
                    "on a.city_id=c.id INNER JOIN state s on c.state_id =s.id WHERE h.user_id=?;";

            PreparedStatement statement = DbConnection.getInstance().getMainConnection().prepareStatement(viewDetails);
            statement.setInt(1, userId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Hotel hotel = new Hotel();
                hotel.setHotelId(resultSet.getInt("id"));
                hotel.setHotelName(resultSet.getString("hotel_name"));
                hotel.setLicenceNo(resultSet.getString("licence_no"));
                hotel.setStarRating(resultSet.getInt("star_rating"));
                hotel.setGstNo(resultSet.getString("GST_No"));
                hotel.setPermits(resultSet.getString("permites"));
                hotel.setImage(resultSet.getString("images"));
                Address address = new Address();
                address.setAddress(resultSet.getString("address_line1"));
                address.setPincode(resultSet.getInt("pincode"));
                address.setViewCity(resultSet.getString("city_name"));
                address.setState(resultSet.getString("state_name"));
                hotel.setAddressline(address);
                GethotelList.add(hotel);
            }
        } catch (SQLException e) {
            throw new DAOException("error occured", e);
        }

        return GethotelList;
    }

    public static List<Hotel>getHotelName(int userId) throws DAOException {
        List<Hotel> hotelList1 = new ArrayList<>();
        try {
            String getAddressQuery = "SELECT * FROM hotel WHERE user_id=?";
            PreparedStatement statement = DbConnection.getInstance().getMainConnection().prepareStatement(getAddressQuery);
            statement.setInt(1, userId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Hotel hotel= new Hotel();
                hotel.setHotelId(resultSet.getInt("id"));
                hotel.setHotelName(resultSet.getString("hotel_name"));
                hotelList1.add(hotel);

            }
        } catch (SQLException e) {
            throw new DAOException("error occured", e);
        }

        return  hotelList1;
    }

    public static List<Hotel> getDataForEdit( int hotelID) throws DAOException {

        List<Hotel> EditList = new ArrayList<>();
        try {
            String viewDetails = "SELECT h.id,h.hotel_name,h.licence_no,h.star_rating,h.GST_No," +
                    "h.permites,a.address_line1,a.pincode,c.city_name, s.state_name FROM " +
                    "hotel h INNER JOIN address a on h.address_id = a.id INNER JOIN city c " +
                    "on a.city_id=c.id INNER JOIN state s on c.state_id =s.id WHERE h.id=?;";

            PreparedStatement statement = DbConnection.getInstance().getMainConnection().prepareStatement(viewDetails);
            statement.setInt(1, hotelID);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Hotel hotels = new Hotel();
                hotels.setHotelId(resultSet.getInt("id"));
                hotels.setHotelName(resultSet.getString("hotel_name"));
                hotels.setLicenceNo(resultSet.getString("licence_no"));
                hotels.setStarRating(resultSet.getInt("star_rating"));
                hotels.setGstNo(resultSet.getString("GST_No"));
                hotels.setPermits(resultSet.getString("permites"));
                Address address = new Address();
                address.setAddress(resultSet.getString("address_line1"));
                address.setPincode(resultSet.getInt("pincode"));
                address.setViewCity(resultSet.getString("city_name"));
                address.setState(resultSet.getString("state_name"));
                hotels.setAddressline(address);
                EditList.add(hotels);
            }
        } catch (SQLException e) {
            throw new DAOException("error occured", e);
        }

        return EditList;
    }

    public void addEditedData(Hotel hotel,int hotelID) throws DAOException {
        try {
            String addQuery = "UPDATE hotel h INNER JOIN address a on h.address_id = a.id  " +
                    "SET h.hotel_name=?,h.licence_no=?,h.star_rating=?," +
                    "h.GST_No=?,h.permites=?,a.address_line1=?," +
                    "a.pincode=? WHERE h.id=?";
            PreparedStatement stmt1;
            stmt1 = DbConnection.getInstance().getMainConnection().prepareStatement(addQuery);
            stmt1.setString(1, hotel.getHotelName());
            stmt1.setString(2, hotel.getLicenceNo());
            stmt1.setInt(3, hotel.getStarRating());
            stmt1.setString(4, hotel.getGstNo());
            stmt1.setString(5, hotel.getPermits());
            stmt1.setString(6,hotel.getAddressline().getAddress());
            stmt1.setInt(7,hotel.getAddressline().getPincode());
            stmt1.setInt(8, hotelID);

            stmt1.executeUpdate();
        } catch (SQLException ex) {
            throw new DAOException("Opps something went wrong...", ex);
        } catch (Exception ex) {
            throw new DAOException("Opps something went wrong...", ex);
        }

    }

}
