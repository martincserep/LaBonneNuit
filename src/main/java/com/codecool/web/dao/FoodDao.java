package com.codecool.web.dao;

import com.codecool.web.model.Food;

import java.sql.SQLException;
import java.util.List;

public interface FoodDao {

    List<Food> findByCategory(String category) throws SQLException;
    Food findById(Integer id) throws SQLException;
    List<Food> findAllFood() throws SQLException;


}
