package com.example.hotelbookingservlet.Model;

public class Address {
    private int address_id;
    private int state;
    private int state_id;
    private String viewState;
    private int city;
    private String viewCity;
    private String address;
    private int pincode;

    public int getAddress_id() {
        return address_id;
    }

    public void setAddress_id(int address_id) {
        this.address_id = address_id;
    }

    public int getState() {
        return state;
    }
    
    public void setState(int state) {
        this.state = state;
    }

    public int getState_id() {
        return state_id;
    }

    public void setState_id(int state_id) {
        this.state_id = state_id;
    }



    public String getViewState() {
        return viewState;
    }

    public void setViewState(String viewState) {
        this.viewState = viewState;
    }

    public int getCity() {
        return city;
    }

    public void setCity(int city) {
        this.city = city;
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
