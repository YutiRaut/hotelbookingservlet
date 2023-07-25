package com.example.hotelbookingservlet.JPAModel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name = "Role")
@Table(name = "role")
public class JPARole {
    @Id
    @Column(name = "id")
    private int role;

    @Column(name = "role_name")
    private String roleName;


    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
}
