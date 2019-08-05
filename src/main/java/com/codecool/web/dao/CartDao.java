package com.codecool.web.dao;

import com.codecool.web.model.Cart;
import com.codecool.web.model.Food;

import java.sql.SQLException;
import java.util.List;

public interface CartDao {

    List<Cart> findAllByUserId(int userId) throws SQLException;

    Cart findFoodInCart(int userId, int foodId) throws SQLException;

    boolean isFoodInCart(int userId, Food food) throws SQLException;

    void addFood(int userId, Food food) throws SQLException;

    void incrementFoodQuantity(int userId, Food food) throws SQLException;

}
