package com.example.hotelbookingservlet.DAO;

import com.example.hotelbookingservlet.Model.Hotel;
import com.example.hotelbookingservlet.Model.Room;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RoomDao {

    public void roomRegistration(Room room, int hotelId) throws DAOException {
        try {
            String addQuery = "INSERT INTO room(room_type,room_count,no_of_people,aminities,room_price,hotel_id) values(?,?,?,?,?,?)";
            PreparedStatement stmt1;
            stmt1 = DbConnection.getInstance().getMainConnection().prepareStatement(addQuery);
            stmt1.setString(1, room.getRoomType());
            stmt1.setInt(2, room.getRoomCount());
            stmt1.setInt(3, room.getNoOfPeople());
            stmt1.setString(4, room.getAminities());
            stmt1.setInt(5, room.getRoomPrice());
            stmt1.setInt(6, hotelId);
            stmt1.executeUpdate();
        } catch (SQLException ex) {
            throw new DAOException("Opps something went wrong...", ex);
        } catch (Exception ex) {
            throw new DAOException("Opps something went wrong...", ex);
        }

    }
    public static List<Room> getAllRoomData(int hotelId) throws DAOException {

        List<Room> roomList = new ArrayList<>();
        try {
            String viewDetails = "SELECT h.hotel_name,r.id,r.room_type,r.room_count,r.no_of_people,r.aminities,r.room_price FROM hotel h INNER JOIN room r on r.hotel_id = h.id WHERE h.id=?";
            PreparedStatement statement = DbConnection.getInstance().getMainConnection().prepareStatement(viewDetails);
            statement.setInt(1, hotelId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Room room = new Room();
                Hotel hotel= new Hotel();
                hotel.setHotelName(resultSet.getString("hotel_name"));
                room.setHoteldata(hotel);
                room.setRoomid(resultSet.getInt("id"));
                room.setRoomType(resultSet.getString("room_type"));
                room.setRoomCount(resultSet.getInt("room_count"));
                room.setNoOfPeople(resultSet.getInt("no_of_people"));
                room.setAminities(resultSet.getString("aminities"));
                room.setRoomPrice(resultSet.getInt("room_price"));

                roomList.add(room);
            }
        } catch (SQLException e) {
            throw new DAOException("error occured", e);
        }

        return roomList;
    }


    public static List<Room> getRoomDataForEdit(int roomId) throws DAOException {

        List<Room> roomdataList = new ArrayList<>();
        try {
            String viewDetails = "SELECT h.hotel_name,r.id,r.room_type,r.room_count,r.no_of_people,r.aminities,r.room_price FROM hotel h INNER JOIN room r on r.hotel_id = h.id WHERE r.id=?";
            PreparedStatement statement = DbConnection.getInstance().getMainConnection().prepareStatement(viewDetails);
            statement.setInt(1, roomId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Room room = new Room();
                Hotel hotel= new Hotel();
                hotel.setHotelName(resultSet.getString("hotel_name"));
                room.setHoteldata(hotel);
                room.setRoomid(resultSet.getInt("id"));
                room.setRoomType(resultSet.getString("room_type"));
                room.setRoomCount(resultSet.getInt("room_count"));
                room.setNoOfPeople(resultSet.getInt("no_of_people"));
                room.setAminities(resultSet.getString("aminities"));
                room.setRoomPrice(resultSet.getInt("room_price"));

                roomdataList.add(room);
            }
        } catch (SQLException e) {
            throw new DAOException("error occured", e);
        }

        return roomdataList;
    }

    public void editedRoomData(Room room,int roomId) throws DAOException {
        try {
            String addQuery = "UPDATE room SET room_count=?,no_of_people=?,aminities=?,room_price=? WHERE id=?";
            PreparedStatement stmt1;
            stmt1 = DbConnection.getInstance().getMainConnection().prepareStatement(addQuery);
            stmt1.setInt(1, room.getRoomCount());
            stmt1.setInt(2, room.getNoOfPeople());
            stmt1.setString(3, room.getAminities());
            stmt1.setInt(4, room.getRoomPrice());
            stmt1.setInt(5, roomId);

            stmt1.executeUpdate();
        } catch (SQLException ex) {
            throw new DAOException("Opps something went wrong...", ex);
        } catch (Exception ex) {
            throw new DAOException("Opps something went wrong...", ex);
        }

    }

}

