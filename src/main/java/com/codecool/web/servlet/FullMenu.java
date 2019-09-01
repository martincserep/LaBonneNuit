package com.codecool.web.servlet;

import com.codecool.web.dao.FoodDao;
import com.codecool.web.dao.database.DatabaseFoodDao;
import com.codecool.web.model.Food;
import com.codecool.web.service.FoodService;
import com.codecool.web.service.simple.SimpleFoodService;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/protected/allfoods")
public class FullMenu extends AbstractServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException{
        try (Connection connection = getConnection(request.getServletContext())) {
            FoodDao foodDao = new DatabaseFoodDao(connection);
            FoodService foodService = new SimpleFoodService(foodDao);
            List<Food> foods = foodService.findAllFoods();
            sendMessage(response, HttpServletResponse.SC_OK, foods);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException{
        try (Connection connection = getConnection(request.getServletContext())) {
            FoodDao foodDao = new DatabaseFoodDao(connection);
            FoodService foodService = new SimpleFoodService(foodDao);
            String name = request.getParameter("name");
            Integer price = Integer.valueOf(request.getParameter("price"));
            String imageURL = request.getParameter("image");
            String category = request.getParameter("category");
            foodService.inserFoodToMenu(name,price,imageURL,category);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
