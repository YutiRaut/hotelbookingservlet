package com.example.hotelbookingservlet.DAO;

import com.example.hotelbookingservlet.Model.Room;

import java.sql.PreparedStatement;
import java.sql.SQLException;

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
}

