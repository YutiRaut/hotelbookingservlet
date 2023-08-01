package com.example.hotelbookingservlet.JPAModel;


import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "state")

public class StateEntity {
    @Id
    @Column(name = "id")
    private int stateId;

    @Column(name = "state_name")
    private String state;


    @OneToMany(mappedBy = "stateEntity", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<CityEntity> cityEntityList;


    public int getStateId() {
        return stateId;
    }

    public void setStateId(int stateId) {
        this.stateId = stateId;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }



    public void setCityEntityList(List<CityEntity> cityEntityList) {
        this.cityEntityList = cityEntityList;
    }
}
