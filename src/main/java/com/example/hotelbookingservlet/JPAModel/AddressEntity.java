package com.example.hotelbookingservlet.JPAModel;

import javax.persistence.*;

@Entity
@Table(name = "address")
public class AddressEntity {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int addressId;

    @Column(name="address_line1")
    private String address;

    @Column(name = "pincode")
    private int pincode;

//    @OneToOne(mappedBy = "addressEntity")
//    private HotelEntity hotelEntity;


    @ManyToOne
    @JoinColumn(name = "city_id")
    private CityEntity cityEntity;

    public int getAddressId() {
        return addressId;
    }

    public void setAddressId(int addressId) {
        this.addressId = addressId;
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

    public CityEntity getCityEntity() {
        return cityEntity;
    }

    public void setCityEntity(CityEntity cityEntity) {
        this.cityEntity = cityEntity;
    }

//    public HotelEntity getHotelEntity() {
//        return hotelEntity;
//    }
//
//    public void setHotelEntity(HotelEntity hotelEntity) {
//        this.hotelEntity = hotelEntity;
//    }
}
