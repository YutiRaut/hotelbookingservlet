package com.example.hotelbookingservlet.Model;

public class Hotel {
    private int hotelId;
    private String hotelName;
    private String licenceNo;
    private int starRating;
    private String gstNo;
    private String permits;
    private Address addressline;
    private String existingHotelName;

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
