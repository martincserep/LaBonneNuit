package com.codecool.web.dao.database;

import com.codecool.web.dao.CartDao;
import com.codecool.web.model.Cart;
import com.codecool.web.model.Food;
import com.codecool.web.model.Order;

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
        String sql = "SELECT foods.foodid, foods.name,cartitems.quantity AS quantity,cartitems.price AS price FROM cartitems JOIN foods ON cartitems.foodid=foods.foodid Where cartitems.userid = ?";

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
    public void increaseQuantity(int userId, int foodId) throws SQLException {
        String sql = "UPDATE cartitems SET quantity = quantity + 1 FROM cartitems Where userid = ? AND foodid = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setInt(1,userId);
            statement.setInt(2,foodId);
            executeInsert(statement);
        }
    }


    @Override
    public Cart findFoodInCart(int userId, int foodId) throws SQLException {
        String sql = "SELECT foods.foodid, foods.name,cartitems.quantity,cartitems.price FROM cartitems JOIN foods ON cartitems.foodid=foods.foodid Where cartitems.userid = ? AND cartitems.foodid = ?;";
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
        String sql = "SELECT * FROM cartitems WHERE userid = ? AND foodid = ? ";
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
        String sql = "INSERT INTO cartitems(userid, foodid, price) VALUES(?,?,?);";
            try (PreparedStatement statement = connection.prepareStatement(sql)){
                statement.setInt(1,userId);
                statement.setInt(2,food.getFoodId());
                statement.setInt(3,food.getPrice());
                statement.executeUpdate();
            }
    }

    @Override
    public void incrementFoodQuantity(int userId, int foodId) throws SQLException {
        String sql = "UPDATE cartitems SET quantity = quantity + 1,price = price * (quantity + 1) WHERE userid = ? AND foodid = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, userId);
            statement.setInt(2, foodId);
            executeInsert(statement);
        }
    }

    @Override
    public void sendOrders(int userId, String order, int total) throws SQLException {

    }

    @Override
    public List<Order> getOrders() throws SQLException {
        String sql = "select cartid, concat(users.firstname, ' ', users.lastname) as name ,concat(users.postalcode, ' ', users.city, ' ', users.address) as address, carts.orderedfoods as orderedfoods, carts.price as price, isfinished from carts inner join users on carts.userid = users.userid where isfinished = false";

        try (PreparedStatement statement = connection.prepareStatement(sql)){
            statement.execute();
            ResultSet resultSet = statement.getResultSet();
            List<Order> orders = new ArrayList<>();
            while (resultSet.next()){
                orders.add(fetchOrders(resultSet));
            }
            return orders;
        }
    }

    @Override
    public void changeOrderStatus(int orderId) throws SQLException {
        String sql = " UPDATE carts SET isfinished = true WHERE cartid = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, orderId);
            statement.executeUpdate();
        }
    }

    @Override
    public boolean checkCarts(int userId) throws SQLException {
        String sql = "SELECT * FROM carts WHERE userid=? and isfinished=false";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, userId);
            statement.execute();
            ResultSet resultSet = statement.getResultSet();
            if (resultSet.next()){
                return true;
            } else {
                return false;
            }

        }
    }

    @Override
    public void createCart(int userId, String orderedFood, int total) throws SQLException {
        String sql = "INSERT INTO carts(userid,orderedfoods,price) VALUES(?,?,?);";
        try (PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setInt(1,userId);
            statement.setString(2,orderedFood);
            statement.setInt(3,total);
            statement.executeUpdate();
        }
    }

    @Override
    public void deleteAllFoodFromCart(int userId) throws SQLException {
        String sql = "DELETE FROM cartitems WHERE userid = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setInt(1,userId);
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


    private Order fetchOrders(ResultSet resultSet) throws SQLException {
        Integer orderid = resultSet.getInt("cartid");
        String name = resultSet.getString("name");
        String address = resultSet.getString("address");
        String orderedFood = resultSet.getString("orderedfoods");
        Integer price = resultSet.getInt("price");
        boolean isfinished = resultSet.getBoolean("isfinished");
        if (isfinished){
            return new Order(orderid,name,address,orderedFood,price, "Finished");

        } else {
            return new Order(orderid,name,address,orderedFood,price, "Waiting for finish...");
        }

    }

}

//select carts.cartid, concat(users.firstname, ' ', users.lastname) as name ,concat(users.postalcode, ' ', users.city, ' ', users.address) as address, isfinished, foods.name, cartitems.quantity, cartitems.price from carts inner join users on carts.userid = users.userid full join cartitems on carts.cartid = cartitems.cartid inner join foods on cartitems.foodid=foods.foodid
