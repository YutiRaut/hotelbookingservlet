package com.example.hotelbookingservlet.DAO;

import com.example.hotelbookingservlet.JPAModel.AddressEntity;
import com.example.hotelbookingservlet.JPAModel.CityEntity;
import com.example.hotelbookingservlet.JPAModel.StateEntity;
import com.example.hotelbookingservlet.Model.Address;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class AddressDao {

    EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("Login");
    EntityManager entityManager = entityManagerFactory.createEntityManager();

    public List<Address> getState() throws DAOException, SQLException {
        List<Address> addresses = new ArrayList<>();
        Statement statement = DbConnection.getInstance().getMainConnection().createStatement();
        ResultSet rs = statement.executeQuery("select * From state ");
        while (rs.next()) {
            Address address = new Address();
            address.setStateId(rs.getInt(1));
            address.setStateList(rs.getString(2));
            addresses.add(address);
        }
        return addresses;
    }

    public List<Address> getCity(int stateId) throws DAOException, SQLException {
        List<Address> addresses1 = new ArrayList<>();
        try {
            String viewCity = "Select * From city where state_id=?";
            PreparedStatement statement = DbConnection.getInstance().getMainConnection().prepareStatement(viewCity);
            statement.setInt(1, stateId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Address address = new Address();
                address.setCityId(resultSet.getInt(1));
                address.setViewCity(resultSet.getString(2));
                address.setStateId(resultSet.getInt(3));
                addresses1.add(address);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return addresses1;
    }

    public Address addAddress(Address address, int cityId) throws SQLException {
        try {
            PreparedStatement statement = null;
            String insertAddress = "insert into address(address_line1,city_id,pincode) values(?,?,?)";
            statement = DbConnection.getInstance().getMainConnection().prepareStatement(insertAddress);
            statement.setString(1, address.getAddress());
            statement.setInt(2, cityId);
            statement.setInt(3, address.getPincode());
            statement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return address;
    }

    public Address getAddress() throws DAOException {
        Address address = new Address();
        try {
            String getAddressQuery = "select * from address";
            PreparedStatement statement = DbConnection.getInstance().getMainConnection().prepareStatement(getAddressQuery);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                address.setAddressId(resultSet.getInt(1));
            }
        } catch (SQLException e) {
            throw new DAOException("error occured", e);
        }

        return address;
    }
//================================================================================================================

//          JPA QUERY START FROM HERE

    public List<StateEntity> jpaGetState() throws DAOException, SQLException {
        List<StateEntity> stateEntities = new ArrayList<>();
        try {
            Query query = entityManager.createQuery("SELECT s FROM StateEntity s");
            stateEntities = query.getResultList();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return stateEntities;
    }


    public List<CityEntity> jpaGetCity(int stateId) throws DAOException, SQLException {
        List<CityEntity> getCity = new ArrayList<>();
        try {
            Query query = entityManager.createQuery("SELECT c FROM CityEntity c WHERE c.stateEntity.stateId = :stateId");
            query.setParameter("stateId", stateId);
            getCity = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return getCity;
    }


    public AddressEntity jpaAddAddress(AddressEntity address) throws SQLException {
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(address);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return address;
    }


//    public AddressEntity jpaGetAddress(String address, int pincode) throws DAOException {
//        AddressEntity addresses = new AddressEntity();
//        try {
//                Query query = entityManager.createQuery("SELECT a.addressId FROM AddressEntity a WHERE a.address = :address AND a.pincode = :pincode");
//                query.setParameter("address", address);
//                query.setParameter("pincode", pincode);
//
//            addresses  = (AddressEntity) query.getSingleResult();
//        } catch (Exception e){
//            e.printStackTrace();
//        }
//
//        return addresses;
//    }
public AddressEntity jpaGetAddress(String address, int pincode) throws DAOException {
    AddressEntity addresses = null;
    try {
        Query query = entityManager.createQuery("SELECT a FROM AddressEntity a WHERE a.address = :address AND a.pincode = :pincode", AddressEntity.class);
        query.setParameter("address", address);
        query.setParameter("pincode", pincode);

        addresses = (AddressEntity) query.getSingleResult();
    } catch (Exception e) {
        e.printStackTrace();
    }

    return addresses;
}

}
