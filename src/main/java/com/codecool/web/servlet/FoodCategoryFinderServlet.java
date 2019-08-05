package com.codecool.web.servlet;

import com.codecool.web.dao.FoodDao;
import com.codecool.web.dao.database.DatabaseFoodDao;
import com.codecool.web.model.Food;
import com.codecool.web.service.FoodService;
import com.codecool.web.service.simple.SimpleFoodService;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/protected/foodbycategory")
public class FoodCategoryFinderServlet extends AbstractServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException{
        try (Connection connection = getConnection(request.getServletContext())) {
            FoodDao foodDao = new DatabaseFoodDao(connection);
            FoodService foodService = new SimpleFoodService(foodDao);
            String category = request.getParameter("category");
            List<Food> foods = foodService.findByCategory(category);
            sendMessage(response, HttpServletResponse.SC_OK, foods);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
