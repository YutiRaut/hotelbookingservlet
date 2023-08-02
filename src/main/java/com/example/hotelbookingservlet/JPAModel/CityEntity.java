package com.example.hotelbookingservlet.JPAModel;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "city")
public class CityEntity {
    @Id
    @Column(name = "id")
    private int cityId;

    @Column(name = "city_name")
    private String cityNames;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "state_id")
    private StateEntity stateEntity;


    public int getCityId() {
        return cityId;
    }

    public void setCityId(int cityId) {
        this.cityId = cityId;
    }

    public String getCityNames() {
        return cityNames;
    }

    public void setCityNames(String cityNames) {
        this.cityNames = cityNames;
    }

    public StateEntity getStateEntity() {
        return stateEntity;
    }

    public void setStateEntity(StateEntity stateEntity) {
        this.stateEntity = stateEntity;
    }


}
