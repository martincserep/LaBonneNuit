package com.codecool.web.service.simple;

import com.codecool.web.dao.CartDao;
import com.codecool.web.model.Cart;
import com.codecool.web.model.Food;
import com.codecool.web.service.CartService;
import com.codecool.web.service.exception.ServiceException;

import java.sql.SQLException;
import java.util.List;

public class SimpleCartService implements CartService {

    private final CartDao cartDao;

    public SimpleCartService(CartDao cartDao) {
        this.cartDao = cartDao;
    }

    @Override
    public List<Cart> findAllByUserId(int userId) throws SQLException {
        return null;
    }

    @Override
    public void updateCart(int userId, Food food) throws SQLException {

    }

    @Override
    public int getTotalPrice(int userId) throws SQLException, ServiceException {
        return 0;
    }

    @Override
    public Cart findProductInCart(int userId, int productId) throws SQLException {
        return null;
    }
}
