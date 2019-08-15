package com.codecool.web.dao.database;

import com.codecool.web.dao.CartDao;
import com.codecool.web.model.Cart;
import com.codecool.web.model.Food;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DatabaseCartDao extends AbstractDao implements CartDao {

    public DatabaseCartDao (Connection connection) {super(connection);}

    @Override
    public List<Cart> findAllByUserId(int userId) throws SQLException {
        String sql = "SELECT foods.foodid, foods.name,cart.quantity,cart.price FROM cart JOIN foods ON cart.foodid=foods.foodid Where cart.userid = ?;";

        try (PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setInt(1,userId);
            statement.execute();
            ResultSet resultSet = statement.getResultSet();
            List<Cart> cartItem = new ArrayList<>();
            while (resultSet.next()){
                cartItem.add(fetchCartItem(resultSet));
            }
            return cartItem;
        }
    }


    @Override
    public Cart findFoodInCart(int userId, int foodId) throws SQLException {
        String sql = "SELECT foods.foodid, foods.name,cart.quantity,cart.price FROM cart JOIN foods ON cart.foodid=foods.foodid Where cart.userid = ? AND cart.foodid = ?;";
        try (PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setInt(1,userId);
            statement.setInt(2,foodId);
            statement.execute();
            ResultSet resultSet = statement.getResultSet();
            resultSet.next();
            return fetchCartItem(resultSet);
        }
    }

    @Override
    public boolean isFoodInCart(int userId, Food food) throws SQLException {
        String sql = "SELECT * FROM cart WHERE userid = ? AND foodid = ? ";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, userId);
            statement.setInt(2, food.getFoodId());
            statement.execute();
            ResultSet resultSet = statement.getResultSet();
            return resultSet.next();
        }
    }

    @Override
    public void addFood(int userId, Food food) throws SQLException {
        String sql = "INSERT INTO cart(userid, foodid, price) VALUES(?,?,?);";
            try (PreparedStatement statement = connection.prepareStatement(sql)){
                statement.setInt(1,userId);
                statement.setInt(2,food.getFoodId());
                statement.setInt(3,food.getPrice());
                statement.executeUpdate();
            }
    }

    @Override
    public void incrementFoodQuantity(int userId, Food food) throws SQLException {
        String sql = "UPDATE cart SET price = price + ?, quantity = quantity + 1 " +
            "WHERE userid = ? AND foodid = ?;";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, food.getPrice());
            statement.setInt(2, userId);
            statement.setInt(3, food.getFoodId());
            statement.executeUpdate();
        }
    }

    private Cart fetchCartItem(ResultSet resultSet) throws SQLException {
    Integer foodId = resultSet.getInt("foodid");
    String name = resultSet.getString("name");
    Integer quantity = resultSet.getInt("quantity");
    Integer price = resultSet.getInt("price");


    return new Cart(foodId, name,quantity,price);
    }


}
