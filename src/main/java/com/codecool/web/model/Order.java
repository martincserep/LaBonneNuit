package com.codecool.web.model;

public class Order {

    private int orderId;
    private String name;
    private String address;
    private String orderedFood;
    private int price;
    private String isFinished;


    public Order(int orderId, String name, String address, String orderedFood, int price, String isFinished) {
        this.orderId = orderId;
        this.name = name;
        this.address = address;
        this.orderedFood = orderedFood;
        this.price = price;
        this.isFinished = isFinished;
    }

    public int getOrderId() {
        return orderId;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getOrderedFood() {
        return orderedFood;
    }

    public int getPrice() {
        return price;
    }

    public String isFinished() {
        return isFinished;
    }
}
