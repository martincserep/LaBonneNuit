package com.codecool.web.service.simple;

import com.codecool.web.dao.UserDao;
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
}
