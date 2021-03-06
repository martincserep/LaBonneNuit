package com.codecool.web.dao;

import com.codecool.web.model.Shipping;
import com.codecool.web.model.User;

import java.sql.SQLException;
import java.util.List;

public interface UserDao {
    User findByUsername(String username) throws SQLException;

    void registerCustomer(String firstname,String lastname, String phonenumber, String email, String username, String password) throws SQLException;

    void deleteUser(Integer userId) throws SQLException;


    void setShippingAddress(Integer userId, String city, String address, String postalCode) throws SQLException;
    void updateShippingAddress(Integer userId, String city, String address, String postalCode) throws SQLException;

    List<User> getUsers() throws SQLException;
    void updateRole(Integer userId, String role) throws SQLException;

}
