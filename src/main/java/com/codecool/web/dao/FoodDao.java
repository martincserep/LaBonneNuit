package com.codecool.web.dao;

import com.codecool.web.model.Food;

import java.sql.SQLException;

public interface FoodDao {

    Food findByCategory(String name) throws SQLException;

}
