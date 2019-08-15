package com.codecool.web.model;

public class Cart {

    private int foodId;
    private String foodName;
    private int quantity;
    private int price;

    public Cart(int foodId, String foodName, int quantity, int price) {
        this.foodId = foodId;
        this.foodName = foodName;
        this.quantity = quantity;
        this.price = price;
    }

    public String getFoodName() {
        return foodName;
    }

    public int getQuantity() {
        return quantity;
    }

    public int getPrice() {
        return price;
    }

    public int getFoodId() {
        return foodId;
    }
}
