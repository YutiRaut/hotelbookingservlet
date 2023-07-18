package com.example.hotelbookingservlet.Model;

public class Room {
    int roomid;
    String roomType;
    int roomCount;
    int noOfPeople;
    int roomPrice;
    String aminities;
    String image;

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    Hotel hoteldata;

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
