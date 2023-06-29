package com.example.hotelbookingservlet.DAO;

import com.example.hotelbookingservlet.Model.Role;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class RoleDao {

    public  List<Role> getRole() throws SQLException {
        List<Role> role = new ArrayList<>();
        Statement statement = DbConnection.getInstance().getMainConnection().createStatement();
        ResultSet rs = statement.executeQuery("select * From role ");
        while (rs.next()) {
            Role role1= new Role();
            role1.setRole(rs.getInt(1));
            role1.setRoleName(rs.getString(2));
            role.add(role1);
        }
        return role;

    }
}
