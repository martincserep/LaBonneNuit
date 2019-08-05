package com.codecool.web.servlet;

import com.codecool.web.dao.CartDao;
import com.codecool.web.dao.database.DatabaseCartDao;
import com.codecool.web.dto.CartDto;
import com.codecool.web.model.Cart;
import com.codecool.web.model.User;
import com.codecool.web.service.CartService;
import com.codecool.web.service.exception.ServiceException;
import com.codecool.web.service.simple.SimpleCartService;

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
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws IOException {

    }
}
