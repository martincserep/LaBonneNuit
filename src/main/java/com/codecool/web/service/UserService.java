package com.codecool.web.service;

import com.codecool.web.dao.UserDao;

import java.sql.SQLException;


public interface UserService {

    void deleteUser(Integer userId) throws SQLException;


}
