package com.codecool.web.model;

public class Shipping extends AbstractModel {
    private String city;
    private String address;
    private String postalCode;


    public Shipping(String city, String address, String postalCode) {
        this.city = city;
        this.address = address;
        this.postalCode = postalCode;
    }


    public String getPostalCode() {
        return postalCode;
    }

    public String getAddress() {
        return address;
    }

    public String getCity() {
        return city;
    }
}
