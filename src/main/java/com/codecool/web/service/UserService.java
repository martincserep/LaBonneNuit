package com.codecool.web.service;

import com.codecool.web.dao.UserDao;
import com.codecool.web.model.Shipping;

import java.sql.SQLException;


public interface UserService {

    void deleteUser(Integer userId) throws SQLException;
    Boolean hasShippingAddress(Integer userId) throws SQLException;
    void setShippingAddress(Integer userId, String city, String address, String postalCode) throws SQLException;
    void updateShippingAddress(Integer userId, String city, String address, String postalCode) throws SQLException;

}
