package com.codecool.web.service.simple;

import com.codecool.web.dao.UserDao;
import com.codecool.web.model.Shipping;
import com.codecool.web.service.UserService;

import java.sql.SQLException;

public class SimpleUserService implements UserService {

    private final UserDao userDao;

    public SimpleUserService(UserDao userDao) {
        this.userDao = userDao;
    }


    @Override
    public void deleteUser(Integer userId) throws SQLException {
        userDao.deleteUser(userId);
    }

    @Override
    public Boolean hasShippingAddress(Integer userId) throws SQLException {
        return userDao.hasShippingAddress(userId);
    }

    @Override
    public void setShippingAddress(Integer userId, String city, String address, String postalCode) throws SQLException {
        userDao.setShippingAddress(userId,city,address,postalCode);
    }

    @Override
    public void updateShippingAddress(Integer userId, String city, String address, String postalCode) throws SQLException {
        userDao.updateShippingAddress(userId,city,address,postalCode);
    }
}
