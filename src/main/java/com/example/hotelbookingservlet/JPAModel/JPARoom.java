package com.example.hotelbookingservlet.JPAModel;
import com.example.hotelbookingservlet.Model.Hotel;

import javax.persistence.*;


@Entity
@Table(name = "room")
public class JPARoom {

   @Id
    int roomid;
   @Column(name = "room_type")
    String roomType;
    @Column(name = "room_count")
    int roomCount;

    @Column(name = "no_of_people")
    int noOfPeople;

    @Column(name = "room_price")
    int roomPrice;

    @Column(name = "aminities")
    String aminities;

    @Column(name = "images")
    String image;

    @OneToMany
    Hotel hoteldata;

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }



    public Hotel getHoteldata() {
        return hoteldata;
    }

    public void setHoteldata(Hotel hoteldata) {
        this.hoteldata = hoteldata;
    }


    public int getRoomid() {
        return roomid;
    }

    public void setRoomid(int roomid) {
        this.roomid = roomid;
    }

    public String getRoomType() {
        return roomType;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    public int getRoomCount() {
        return roomCount;
    }

    public void setRoomCount(int roomCount) {
        this.roomCount = roomCount;
    }

    public int getNoOfPeople() {
        return noOfPeople;
    }

    public void setNoOfPeople(int noOfPeople) {
        this.noOfPeople = noOfPeople;
    }

    public int getRoomPrice() {
        return roomPrice;
    }

    public void setRoomPrice(int roomPrice) {
        this.roomPrice = roomPrice;
    }

    public String getAminities() {
        return aminities;
    }

    public void setAminities(String aminities) {
        this.aminities = aminities;
    }
}
