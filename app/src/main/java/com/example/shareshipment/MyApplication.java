package com.example.shareshipment;

import android.app.Application;

public class MyApplication extends Application {

    private String fullName;
    private String phoneNumber;
    private String city;
    private  String productImage;
    private Shipment shipment;
    private String shipments;
    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setProductImage(String productImage) {
        this.productImage = productImage;
    }

    public String getProductImage() {
        return productImage;
    }

    public Shipment getShipment() {
        return shipment;
    }

    public void setShipment(Shipment shipment) {
        this.shipment = shipment;
    }

    public String getShipments() {
        return shipments;
    }

    public void setShipments(String shipments) {
        this.shipments = shipments;
    }
}