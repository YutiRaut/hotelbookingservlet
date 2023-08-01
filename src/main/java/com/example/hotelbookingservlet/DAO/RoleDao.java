package com.example.hotelbookingservlet.DAO;

import com.example.hotelbookingservlet.JPAModel.RoleEntity;
import com.example.hotelbookingservlet.Model.Role;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class RoleDao {

    public List<Role> getRole() throws SQLException {
        try {
            List<Role> role = new ArrayList<>();
            Statement statement = DbConnection.getInstance().getMainConnection().createStatement();
            ResultSet rs = statement.executeQuery("select * From role ");
            while (rs.next()) {
                Role role1 = new Role();
                role1.setRole(rs.getInt(1));
                role1.setRoleName(rs.getString(2));
                role.add(role1);
            }
            return role;
        } catch (SQLException ex) {
            throw new SQLException("Something went wrong...", ex);
        }

    }

//THIS IS JPA QUERY
    
    public List<RoleEntity> roleList(){
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("Login");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        List<RoleEntity> role = new ArrayList<>();
        String jpql = "SELECT r FROM Role r";
        Query query = entityManager.createQuery(jpql);
        role=query.getResultList();
        return role;
    }

}
