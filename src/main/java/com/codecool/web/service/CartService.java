package com.codecool.web.service;

import com.codecool.web.model.Cart;
import com.codecool.web.model.Food;
import com.codecool.web.model.Order;
import com.codecool.web.service.exception.ServiceException;

import java.sql.SQLException;
import java.util.List;

public interface CartService {

    List<Cart> findAllByUserId(int userId) throws SQLException;

    void AddToCart(int userId, Food food) throws SQLException;

    int getTotalPrice(int userId) throws SQLException, ServiceException;

    Cart findFoodInCart(int userId, int foodId) throws SQLException;

    void sendOrders(int userId, String order, int total) throws SQLException;

    List<Order> getOrders() throws SQLException;

    void changeOrderStatus(int orderId) throws SQLException;

    boolean checkCarts(int userId) throws SQLException;

    void increaseQuantity(int userId, int foodId) throws SQLException;

    boolean isFoodInCart(int userId, Food food) throws SQLException;

    Integer getCartPrice(List<Cart> orderList);

    void createCart(int userId, String orderedFood, int total) throws SQLException;

    String getOrderedFoodFromList(List<Cart> orderList);

    void deleteAllFoodFromCart(int userId) throws SQLException;

}
