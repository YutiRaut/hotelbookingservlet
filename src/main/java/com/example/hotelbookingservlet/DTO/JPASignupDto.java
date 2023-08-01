package com.example.hotelbookingservlet.DTO;



import com.example.hotelbookingservlet.JPAModel.RoleEntity;

import javax.persistence.*;

@Entity(name = "User")
@Table(name = "user" ,uniqueConstraints = {@UniqueConstraint(columnNames = {"email"})})
public class JPASignupDto {
    @Id

    @Column(name = "id")
    private int userId;

    @Column(name = "user_name")
    private String name;

    @Column(name = "email", unique = true)
    private String email;

    @Column(name = "contact_no")
    private String contact;

    @Column(name = "password")
    private String password;

    @Column(name = "verification_code")
    private String verificationCode;

    @Column(name = "is_verified")
    private boolean isVerified;


//    private String role;

    @OneToOne
    @JoinColumn(name ="role_id",referencedColumnName = "id")
    private RoleEntity jpaRole;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getVerificationCode() {
        return verificationCode;
    }

    public void setVerificationCode(String verificationCode) {
        this.verificationCode = verificationCode;
    }

    public boolean isVerified() {
        return isVerified;
    }

    public boolean setVerified(boolean verified) {
        isVerified = verified;
        return true;
    }

//    public String getRole() {
//        return role;
//    }
//
//    public void setRole(String role) {
//        this.role = role;
//    }

    public RoleEntity getJpaRole() {
        return jpaRole;
    }

    public void setJpaRole(RoleEntity jpaRole) {
        this.jpaRole = jpaRole;
    }
}

