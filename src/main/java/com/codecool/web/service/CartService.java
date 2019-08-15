package com.codecool.web.service;

import com.codecool.web.model.Cart;
import com.codecool.web.model.Food;
import com.codecool.web.service.exception.ServiceException;

import java.sql.SQLException;
import java.util.List;

public interface CartService {

    List<Cart> findAllByUserId(int userId) throws SQLException;

    void AddToCart(int userId, Food food) throws SQLException;

    int getTotalPrice(int userId) throws SQLException, ServiceException;

    Cart findFoodInCart(int userId, int productId) throws SQLException;



}
