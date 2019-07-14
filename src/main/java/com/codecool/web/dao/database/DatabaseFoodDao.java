package com.codecool.web.dao.database;

import com.codecool.web.dao.FoodDao;
import com.codecool.web.model.Food;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public final class DatabaseFoodDao extends AbstractDao implements FoodDao {

    public DatabaseFoodDao (Connection connection) {super(connection);}


    @Override
    public List<Food> findByCategory(String category) throws SQLException {
        List<Food> foods = new ArrayList<>();
        if(category == null || "".equals(category)){
            throw new IllegalArgumentException("Category can not be null or empty");
        }
        String sql = "SELECT * FROM foods WHERE category = ?";
        try (Statement statement = connection.createStatement()){
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()){
                foods.add(fetchFood(resultSet));
            }
        }
        return foods;

    }

    @Override
    public Food findById(Integer id) throws SQLException {
        String sql = "SELECT * FROM foods WHERE foodid = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1,id);
            statement.execute();
            ResultSet resultSet = statement.getResultSet();
            resultSet.next();
            return fetchFood(resultSet);
        }
    }

    @Override
    public List<Food> findAllFood() throws SQLException {
        String sql = "SELECT * FROM foods;";
        List<Food> foods = new ArrayList<>();
        try (Statement statement = connection.createStatement()){
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()){
                foods.add(fetchFood(resultSet));
            }
        }
        return foods;
    }

    private Food fetchFood(ResultSet resultSet) throws SQLException{

        int id = resultSet.getInt("foodid");
        String name = resultSet.getString("name");
        int price = resultSet.getInt("price");
        String pictureURL = resultSet.getString("pictuer");
        String category = resultSet.getString("category");

        return new Food(id,name,price,pictureURL,category);

    }

}
