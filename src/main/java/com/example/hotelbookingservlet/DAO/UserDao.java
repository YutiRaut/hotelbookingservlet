package com.example.hotelbookingservlet.DAO;

import com.example.hotelbookingservlet.DTO.JPASignupDto;
import com.example.hotelbookingservlet.DTO.SignupDto;
import com.example.hotelbookingservlet.JPAModel.UserEntity;
import com.example.hotelbookingservlet.Model.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDao {
    EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("Login");
    EntityManager entityManager = entityManagerFactory.createEntityManager();
    EntityTransaction transaction = entityManager.getTransaction();


    public static User checkUserCredentials(String email, String password) throws DAOException {
        User user = null;
        try {
            String loginQuery = "select * from user where email=? AND password=?";
            PreparedStatement stmt2;
            stmt2 = DbConnection.getInstance().getMainConnection().prepareStatement(loginQuery);
            stmt2.setString(1, email);
            stmt2.setString(2, password);
            ResultSet resultSet = stmt2.executeQuery();
            if (resultSet.next()) {
                user = new User();
                user.setUserId(resultSet.getInt(1));
                user.setRole(resultSet.getInt(2));
                user.setName(resultSet.getString(3));
                user.setEmail(resultSet.getString(4));
                user.setContact(resultSet.getString(5));
                user.setPassword(resultSet.getString(6));
                user.setVerificationCode(resultSet.getString(7));
                user.setVerified(resultSet.getBoolean(8));
            }
            return user;
        } catch (SQLException ex) {
            throw new DAOException("Something went wrong...", ex);
        } catch (Exception ex) {
            throw new DAOException("Something went wrong...", ex);
        }
    }

    public static int addUser(SignupDto signupDto) throws DAOException {
        try {
            String insertQuery = "INSERT INTO user(user_name,email,contact_no,password,verification_code,is_verified,role_id) values(?,?,?,?,?,?,?)";
            PreparedStatement stmt3;
            stmt3 = DbConnection.getInstance().getMainConnection().prepareStatement(insertQuery);
            stmt3.setString(1, signupDto.getName());
            stmt3.setString(2, signupDto.getEmail());
            stmt3.setString(3, signupDto.getContact());
            stmt3.setString(4, signupDto.getPassword());
            stmt3.setString(5, signupDto.getVerificationCode());
            stmt3.setBoolean(6, signupDto.isVerified());
            stmt3.setInt(7, Integer.parseInt(signupDto.getRole()));
            return stmt3.executeUpdate();
        } catch (SQLException ex) {
            throw new DAOException("Something went wrong...", ex);
        } catch (Exception ex) {
            throw new DAOException("Something went wrong...", ex);
        }
    }

    public void updateUserIsVerified(boolean isVerified, String email) throws DAOException {
        try {
            String update = "update user set is_verified=? where email=?";
            PreparedStatement stmt0 = DbConnection.getInstance().getMainConnection().prepareStatement(update);
            stmt0.setBoolean(1, isVerified);
            stmt0.setString(2, email);
            stmt0.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("something went wrong...", e);
        } catch (Exception e) {
            throw new DAOException("Something went wrong...", e);
        }
    }
//============================================================================================================
    //JPA QUERY STARTED HERE




    public UserEntity loginUser(String email, String password) throws DAOException {

        transaction.begin();
        UserEntity user = null;
        try {
            String jpql = "SELECT u FROM User u WHERE u.email = :email AND u.password = :password";
            user = entityManager.createQuery(jpql, UserEntity.class)
                    .setParameter("email", email)
                    .setParameter("password", password)
                    .getSingleResult();

        } catch (Exception e) {
            throw new DAOException("Something went wrong...", e);
        }
        return user;
    }

    //Update to is verified
    public void jpaUpdateUserIsVerified(boolean isVerified, String email) {
        transaction.begin();
        int updatedRows = entityManager.createQuery("UPDATE User u SET u.isVerified = :is_verified WHERE u.email = :email").setParameter("is_verified", isVerified).setParameter("email", email).executeUpdate();
        transaction.commit();
    }

    public UserEntity getUser(int userId) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("Login");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        return entityManager.find(UserEntity.class, userId);
    }

    //Update to is verified
    public void updateUser(UserEntity user) {
        transaction.begin();
        entityManager.merge(user);
        transaction.commit();
    }

    //Register User

    public void JPAaddUser(JPASignupDto user) {
        entityManager.getTransaction().begin();
        entityManager.persist(user);
        entityManager.getTransaction().commit();
    }

}
