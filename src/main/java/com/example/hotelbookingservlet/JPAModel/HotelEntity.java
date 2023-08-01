package com.example.hotelbookingservlet.JPAModel;

import javax.persistence.*;

@Entity
@Table(name = "hotel")
public class HotelEntity {

    @Id
    @Column(name = "id")
        private int hotelId;


    @Column(name="hotel_name")
        private String hotelName;
    @Column(name = "licence_no")
        private String licenceNo;

    @Column(name = "star_rating")
        private int starRating;

    @Column(name = "Gst_No")
        private String gstNo;

    @Column(name = "permites")
        private String permits;


    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity userEntity;

    @OneToOne
    @JoinColumn(name ="address_id",referencedColumnName = "id")
        private AddressEntity addressEntity;

        @Column(name = "images")
        private String image;

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

    public AddressEntity getAddressEntity() {
        return addressEntity;
    }

    public void setAddressEntity(AddressEntity addressEntity) {
        this.addressEntity = addressEntity;
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

    public UserEntity getUserEntity() {
        return userEntity;
    }

    public void setUserEntity(UserEntity userEntity) {
        this.userEntity = userEntity;
    }
}


