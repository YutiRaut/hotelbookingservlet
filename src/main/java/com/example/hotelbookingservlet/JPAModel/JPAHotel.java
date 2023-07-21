package com.example.hotelbookingservlet.JPAModel;

import com.example.hotelbookingservlet.Model.Address;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class JPAHotel {
    @Id
    private int hotelId;

    @Column(name = "hotel_name")
    private String hotelName;

    @Column(name = "licence_no")
    private String licenceNo;
    @Column(name = "star_rating")
    private int starRating;

    @Column(name = "GST_No")
    private String gstNo;

    @Column(name="permits")
    private String permits;


    private Address addressline;

    private String existingHotelName;

    @Column(name = "image")
    private String image;

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Address getAddressline() {
        return addressline;
    }

    public void setAddressline(Address addressline) {
        this.addressline = addressline;
    }

    public int getHotelId() {
        return hotelId;
    }

    public void setHotelId(int hotelId) {
        this.hotelId = hotelId;
    }

    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    public String getLicenceNo() {
        return licenceNo;
    }

    public void setLicenceNo(String licenceNo) {
        this.licenceNo = licenceNo;
    }

    public int getStarRating() {
        return starRating;
    }

    public void setStarRating(int starRating) {
        this.starRating = starRating;
    }

    public String getGstNo() {
        return gstNo;
    }

    public void setGstNo(String gstNo) {
        this.gstNo = gstNo;
    }

    public String getPermits() {
        return permits;
    }

    public void setPermits(String permits) {
        this.permits = permits;
    }

    public String getExistingHotelName() {
        return existingHotelName;
    }

    public void setExistingHotelName(String existingHotelName) {
        this.existingHotelName = existingHotelName;
    }

}
