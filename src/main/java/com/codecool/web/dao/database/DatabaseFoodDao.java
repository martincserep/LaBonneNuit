package com.codecool.web.dao.database;

import com.codecool.web.dao.FoodDao;
import com.codecool.web.model.Food;

import java.sql.Connection;
import java.sql.SQLException;

public final class DatabaseFoodDao extends AbstractDao implements FoodDao {

    public DatabaseFoodDao (Connection connection) {super(connection);}


    @Override
    public Food findByCategory(String category) throws SQLException {
        if(category == null || "".equals(category)){
            throw new IllegalArgumentException("Category can not be null or empty");
        }

        String sql = "SELECT name, price, description, image, category FROM foods WHERE category=?";
        return null;
    }
}
