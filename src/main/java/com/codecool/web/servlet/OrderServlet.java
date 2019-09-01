package com.codecool.web.servlet;

import com.codecool.web.dao.CartDao;
import com.codecool.web.dao.FoodDao;
import com.codecool.web.dao.database.DatabaseCartDao;
import com.codecool.web.dao.database.DatabaseFoodDao;
import com.codecool.web.dto.CartDto;
import com.codecool.web.model.Cart;
import com.codecool.web.model.Food;
import com.codecool.web.model.Order;
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

@WebServlet("/protected/orders")
public class OrderServlet extends AbstractServlet{

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try (Connection connection = getConnection(request.getServletContext())) {

            CartDao cartDao = new DatabaseCartDao(connection);
            CartService cartService = new SimpleCartService(cartDao);
             List<Order> orders = cartService.getOrders();
            sendMessage(response,HttpServletResponse.SC_OK,orders);

        } catch (SQLException se) {
            handleError(response, se, true);
        }
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try (Connection connection = getConnection(request.getServletContext())) {

            CartDao cartDao = new DatabaseCartDao(connection);
            CartService cartService = new SimpleCartService(cartDao);

            Integer orderId = Integer.valueOf(request.getParameter("orderId"));

            cartService.changeOrderStatus(orderId);

            sendMessage(response,HttpServletResponse.SC_OK,orderId);

        } catch (SQLException se) {
            handleError(response,se,false);
        }
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try (Connection connection = getConnection(request.getServletContext())) {
                CartDao cartDao = new DatabaseCartDao(connection);
                CartService cartService = new SimpleCartService(cartDao);
                Integer userId = Integer.valueOf(request.getParameter("userId"));
                if(cartService.checkCarts(userId)){
                    System.out.println("Már van ilyen tesó");
                } else {
                    cartService.createCart(userId);
                }



        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
