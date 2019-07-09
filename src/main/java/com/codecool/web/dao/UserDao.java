package com.codecool.web.dao;

import com.codecool.web.model.User;

import java.sql.SQLException;

public interface UserDao {
    User findByUsername(String username) throws SQLException;

    void registerCustomer(String firstname,String lastname, String phonenumber, String email, String username, String password) throws SQLException;
}
