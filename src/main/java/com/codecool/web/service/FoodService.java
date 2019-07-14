package com.codecool.web.service;

import com.codecool.web.model.Food;

import java.sql.SQLException;
import java.util.List;

public interface FoodService {

    Food findById(Integer id) throws SQLException;
    List<Food> findByCategory(String category) throws SQLException;
    List<Food> findAllFoods() throws SQLException;
}
