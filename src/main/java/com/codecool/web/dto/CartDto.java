package com.codecool.web.dto;

import com.codecool.web.model.Cart;

import java.util.List;

public class CartDto {
    private List<Cart> cartItems;
    private int price;

    public CartDto(List<Cart> cartItems, int price) {
        this.cartItems = cartItems;
        this.price = price;
    }

    public List<Cart> getCartItems() {
        return cartItems;
    }

    public int getPrice() {
        return price;
    }
}
