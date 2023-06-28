package com.example.hotelbookingservlet.DAO;

import com.example.hotelbookingservlet.Model.Address;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class AddressDao {
    public List<Address> getState() throws DAOException, SQLException {
        List<Address> addresses = new ArrayList<>();
        Statement statement = MainConnection.getInstance().getMainConnection().createStatement();
        ResultSet rs = statement.executeQuery("select * From state ");
        while (rs.next()) {
            Address address = new Address();
            address.setState_id(rs.getInt(1));
            address.setViewState(rs.getString(2));
            addresses.add(address);
        }
        return addresses;
    }
}
