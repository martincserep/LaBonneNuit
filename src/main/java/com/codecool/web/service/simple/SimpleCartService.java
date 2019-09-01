package com.codecool.web.service.simple;

import com.codecool.web.dao.CartDao;
import com.codecool.web.model.Cart;
import com.codecool.web.model.Food;
import com.codecool.web.model.Order;
import com.codecool.web.service.CartService;
import com.codecool.web.service.exception.ServiceException;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class SimpleCartService implements CartService {

    private final CartDao cartDao;

    public SimpleCartService(CartDao cartDao) {
        this.cartDao = cartDao;
    }

    @Override
    public List<Cart> findAllByUserId(int userId) throws SQLException {
        return cartDao.findAllByUserId(userId);
    }

    @Override
    public void AddToCart(int userId, Food food) throws SQLException {
        cartDao.addFood(userId,food);
    }

    @Override
    public int getTotalPrice(int userId) throws SQLException, ServiceException {
        return 0;
    }

    @Override
    public Cart findFoodInCart(int userId, int foodId) throws SQLException {
        return cartDao.findFoodInCart(userId,foodId);
    }

    @Override
    public void sendOrders(int userId, String order, int total) throws SQLException {
        cartDao.sendOrders(userId, order, total);
    }

    @Override
    public List<Order> getOrders() throws SQLException {
        return cartDao.getOrders();
    }

    @Override
    public void changeOrderStatus(int orderId) throws SQLException {
        cartDao.changeOrderStatus(orderId);
    }

    @Override
    public boolean checkCarts(int userId) throws SQLException {
        return cartDao.checkCarts(userId);
    }

    @Override
    public void increaseQuantity(int userId, int foodId) throws SQLException {
        cartDao.incrementFoodQuantity(userId,foodId);
    }

    @Override
    public boolean isFoodInCart(int userId, Food food) throws SQLException {
        return cartDao.isFoodInCart(userId,food);
    }


    @Override
    public String getOrderedFoodFromList(List<Cart> orderList) {
        String orderString = "";
        for(int i=0;i<orderList.size();i++){
                Cart cart = orderList.get(i);
            System.out.println(cart.getFoodName() + " " + cart.getQuantity() + " " + cart.getPrice());
            String newFood = cart.getFoodName() + " " + cart.getQuantity() + " " + cart.getPrice();
            orderString = orderString + " " + newFood;
        }
        return orderString;
    }

    @Override
    public void deleteAllFoodFromCart(int userId) throws SQLException {
        cartDao.deleteAllFoodFromCart(userId);
    }

    @Override
    public Integer getCartPrice(List<Cart> orderList) {
        int total = 0;
        for(int i=0;i<orderList.size();i++){
            Cart cart = orderList.get(i);
            total = total + cart.getPrice();
        }
        return total;
    }

    @Override
    public void createCart(int userId, String orderedFood, int total) throws SQLException {
        cartDao.createCart(userId,orderedFood,total);
    }

}
