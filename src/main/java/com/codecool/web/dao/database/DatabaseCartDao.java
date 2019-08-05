package com.codecool.web.dao.database;

import com.codecool.web.dao.CartDao;
import com.codecool.web.model.Cart;
import com.codecool.web.model.Food;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class DatabaseCartDao extends AbstractDao implements CartDao {

    public DatabaseCartDao (Connection connection) {super(connection);}

    @Override
    public List<Cart> findAllByUserId(int userId) throws SQLException {
        return null;
    }

    @Override
    public Cart findFoodInCart(int userId, int foodId) throws SQLException {
        return null;
    }

    @Override
    public boolean isFoodInCart(int userId, Food food) throws SQLException {
        return false;
    }

    @Override
    public void addFood(int userId, Food food) throws SQLException {

    }

    @Override
    public void incrementFoodQuantity(int userId, Food food) throws SQLException {

    }
}
