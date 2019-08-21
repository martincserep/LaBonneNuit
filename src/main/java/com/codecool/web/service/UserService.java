package com.codecool.web.service;

import com.codecool.web.dao.UserDao;
import com.codecool.web.model.Shipping;
import com.codecool.web.model.User;

import java.sql.SQLException;
import java.util.List;


public interface UserService {

    void deleteUser(Integer userId) throws SQLException;
    void setShippingAddress(Integer userId, String city, String address, String postalCode) throws SQLException;
    void updateShippingAddress(Integer userId, String city, String address, String postalCode) throws SQLException;
    List<User> getUsers() throws SQLException;
    void updateRole(Integer userId, String role) throws SQLException;

}
