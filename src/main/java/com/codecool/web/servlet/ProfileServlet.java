package com.codecool.web.servlet;

import com.codecool.web.dao.UserDao;
import com.codecool.web.dao.database.DatabaseUserDao;
import com.codecool.web.model.User;
import com.codecool.web.service.UserService;
import com.codecool.web.service.simple.SimpleUserService;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

@WebServlet("/protected/profile")
public class ProfileServlet extends AbstractServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try (Connection connection = getConnection(request.getServletContext())){
            UserDao userDao = new DatabaseUserDao(connection);
            UserService userService = new SimpleUserService(userDao);
            System.out.println(request.getParameter("userId"));
            Integer userId = Integer.valueOf(request.getParameter("userId"));
            System.out.println(userId);
            userService.hasShippingAddress(userId);


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //Set Address
        try (Connection connection = getConnection(request.getServletContext())){
            UserDao userDao = new DatabaseUserDao(connection);
            UserService userService = new SimpleUserService(userDao);

            Integer userId = Integer.valueOf(request.getParameter("userId"));
            String city = request.getParameter("city");
            String address = request.getParameter("address");
            String postalCode = request.getParameter("postalCode");

            userService.hasShippingAddress(userId);


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //Update Address
        try (Connection connection = getConnection(request.getServletContext())){
            UserDao userDao = new DatabaseUserDao(connection);
            UserService userService = new SimpleUserService(userDao);

            Integer userId = Integer.valueOf(request.getParameter("userId"));

            boolean hasShippingAddress = userService.hasShippingAddress(userId);

            sendMessage(response, HttpServletResponse.SC_OK, hasShippingAddress);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try (Connection connection = getConnection(request.getServletContext())) {
            UserDao userDao = new DatabaseUserDao(connection);
            UserService userService = new SimpleUserService(userDao);

            Integer userId = Integer.valueOf(request.getParameter("userId"));

            userService.deleteUser(userId);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

}

