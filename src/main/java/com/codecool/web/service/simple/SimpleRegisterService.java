package com.codecool.web.service.simple;

import com.codecool.web.dao.UserDao;
import com.codecool.web.model.User;
import com.codecool.web.service.RegisterService;
import com.codecool.web.service.exception.ServiceException;

import java.sql.SQLException;

public class SimpleRegisterService implements RegisterService {

    private final UserDao userDao;

    public SimpleRegisterService(UserDao userDao) {this.userDao = userDao;}

    @Override
    public User registerUser(String firstname, String lastname, String phonenumber, String email, String username, String password) throws SQLException, ServiceException {
        try {
            User user = userDao.findByUsername(username);
            if (user != null) {
                throw new ServiceException("User already exists");
            }
            return user;
        } catch (IllegalArgumentException ex) {
            throw new ServiceException(ex.getMessage());
        }
    }
}
