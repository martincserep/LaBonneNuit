package com.codecool.web.servlet;

import com.codecool.web.dao.UserDao;
import com.codecool.web.model.User;
import com.codecool.web.dao.database.DatabaseUserDao;
import com.codecool.web.service.UserService;
import com.codecool.web.service.simple.SimpleUserService;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/protected/profile")
public class ProfileServlet extends AbstractServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try (Connection connection = getConnection(request.getServletContext())) {
            UserDao userDao = new DatabaseUserDao(connection);
            UserService userService = new SimpleUserService(userDao);

            List<User> users = userService.getUsers();
            sendMessage(response,HttpServletResponse.SC_OK,users);

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

        try (Connection connection = getConnection(request.getServletContext())){
            UserDao userDao = new DatabaseUserDao(connection);
            UserService userService = new SimpleUserService(userDao);

            Integer userId = Integer.valueOf(request.getParameter("userId"));
            String city = request.getParameter("city");
            String address = request.getParameter("address");
            String postalCode = request.getParameter("postalCode");

            System.out.println(city);
            System.out.println(address);
            System.out.println(postalCode);

            userService.updateShippingAddress(userId,city,address,postalCode);



        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws IOException {

        try (Connection connection = getConnection(request.getServletContext())){
            UserDao userDao = new DatabaseUserDao(connection);
            UserService userService = new SimpleUserService(userDao);

            Integer userId = Integer.valueOf(request.getParameter("userId"));
            boolean role = Boolean.parseBoolean(request.getParameter("role"));
            String userRole = "CUSTOMER";
            if (role){
                userRole = "EMPLOYEE";
            }

            userService.updateRole(userId,userRole);

            List<String> currentUser = new ArrayList<>();
            currentUser.add(String.valueOf(userId));
            currentUser.add(userRole);


            sendMessage(response,HttpServletResponse.SC_OK,currentUser);

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

