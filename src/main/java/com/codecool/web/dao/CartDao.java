package com.codecool.web.dao;

import com.codecool.web.model.Cart;
import com.codecool.web.model.Food;
import com.codecool.web.model.Order;

import java.sql.SQLException;
import java.util.List;

public interface CartDao {

    List<Cart> findAllByUserId(int userId) throws SQLException;

    void increaseQuantity(int userId, int foodId) throws SQLException;

    Cart findFoodInCart(int userId, int foodId) throws SQLException;

    boolean isFoodInCart(int userId, Food food) throws SQLException;

    void addFood(int userId, Food food) throws SQLException;

    void incrementFoodQuantity(int userId, int foodId) throws SQLException;

    void sendOrders(int userId, String order, int total) throws SQLException;

    List<Order> getOrders()throws SQLException;

    void changeOrderStatus(int orderId) throws SQLException;

    boolean checkCarts(int userId) throws SQLException;

    void createCart(int userId) throws SQLException;


}
