package com.codecool.web.service.simple;

import com.codecool.web.dao.FoodDao;
import com.codecool.web.model.Food;
import com.codecool.web.service.FoodService;

import java.sql.SQLException;
import java.util.List;

public class SimpleFoodService implements FoodService {

    private final FoodDao foodDao;

    public SimpleFoodService(FoodDao foodDao) {
        this.foodDao = foodDao;
    }

    @Override
    public Food findById(Integer id) throws SQLException {
        return foodDao.findById(id);
    }

    @Override
    public List<Food> findByCategory(String category) throws SQLException {
        return foodDao.findByCategory(category);
    }

    @Override
    public List<Food> findAllFoods() throws SQLException {
        return foodDao.findAllFood();
    }

}
