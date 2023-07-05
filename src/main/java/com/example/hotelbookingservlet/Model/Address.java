package com.example.hotelbookingservlet.Model;

public class Address {
    private int addressId;
    private int state;
    private int stateId;
    private String StateList;
    private int cityId;
    private String viewCity;
    private String address;
    private int pincode;

    public Address() {
    }

    public Address(String addressId, int pincode, String cityname, String statename) {
    }


    public int getAddressId() {
        return addressId;
    }

    public void setAddressId(int addressId) {
        this.addressId = addressId;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public int getStateId() {
        return stateId;
    }

    public void setStateId(int stateId) {
        this.stateId = stateId;
    }


    public String getStateList() {
        return StateList;
    }

    public void setStateList(String stateList) {
        this.StateList = stateList;
    }

    public int getCityId() {
        return cityId;
    }

    public void setCityId(int cityId) {
        this.cityId = cityId;
    }

    public String getViewCity() {
        return viewCity;
    }

    public void setViewCity(String viewCity) {
        this.viewCity = viewCity;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getPincode() {
        return pincode;
    }

    public void setPincode(int pincode) {
        this.pincode = pincode;
    }
}
