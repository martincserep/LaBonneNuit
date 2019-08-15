package com.codecool.web.servlet;

import com.codecool.web.dao.CartDao;
import com.codecool.web.dao.FoodDao;
import com.codecool.web.dao.database.DatabaseCartDao;
import com.codecool.web.dao.database.DatabaseFoodDao;
import com.codecool.web.dto.CartDto;
import com.codecool.web.model.Cart;
import com.codecool.web.model.Food;
import com.codecool.web.model.User;
import com.codecool.web.service.CartService;
import com.codecool.web.service.FoodService;
import com.codecool.web.service.exception.ServiceException;
import com.codecool.web.service.simple.SimpleCartService;
import com.codecool.web.service.simple.SimpleFoodService;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/protected/cart")
public class CartServlet extends AbstractServlet{

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try (Connection connection = getConnection(request.getServletContext())) {

            CartDao cartDao = new DatabaseCartDao(connection);
            CartService cartService = new SimpleCartService(cartDao);

            User loggedInUser = (User) request.getSession().getAttribute("user");
            int userId = loggedInUser.getId();

            List<Cart> cartItems = cartService.findAllByUserId(userId);
            int totalPrice = cartService.getTotalPrice(userId);

            sendMessage(response, HttpServletResponse.SC_OK, new CartDto(cartItems, totalPrice));
        } catch (SQLException | ServiceException se) {
            handleError(response, se, true);
        }
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try (Connection connection = getConnection(request.getServletContext())) {

            CartDao cartDao = new DatabaseCartDao(connection);
            CartService cartService = new SimpleCartService(cartDao);

            FoodDao foodDao = new DatabaseFoodDao(connection);
            FoodService foodService = new SimpleFoodService(foodDao);


            User loggedInUser = (User) request.getSession().getAttribute("user");
            int userId = loggedInUser.getId();

            int foodId = Integer.parseInt(request.getParameter("foodId"));
            Food food = foodService.findById(foodId);

            cartService.AddToCart(userId,food);

            Cart updatedCartItem = cartService.findFoodInCart(userId,foodId);

            sendMessage(response,HttpServletResponse.SC_OK,updatedCartItem);

        } catch (SQLException se) {
            handleError(response,se,false);
        }
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws IOException {

    }
}
