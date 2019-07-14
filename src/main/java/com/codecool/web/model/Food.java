package com.codecool.web.model;

public class Food {

    private int foodId;
    private String name;
    private int price;
    private String pictureURL;
    private String category;

    public Food(int foodId, String name, int price, String pictureURL, String category) {
        this.foodId = foodId;
        this.name = name;
        this.price = price;
        this.pictureURL = pictureURL;
        this.category = category;
    }


    public int getFoodId() {
        return foodId;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public String getPictureURL() {
        return pictureURL;
    }

    public String getCategory() {
        return category;
    }
}
