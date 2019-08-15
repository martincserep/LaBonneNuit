package com.codecool.web.service.simple;

import com.codecool.web.dao.CartDao;
import com.codecool.web.model.Cart;
import com.codecool.web.model.Food;
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
}
