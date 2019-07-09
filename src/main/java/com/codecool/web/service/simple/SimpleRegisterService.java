package com.codecool.web.service.simple;

import com.codecool.web.dao.UserDao;
import com.codecool.web.dao.database.DatabaseUserDao;
import com.codecool.web.model.User;
import com.codecool.web.service.RegisterService;
import com.codecool.web.service.exception.ServiceException;

import java.sql.SQLException;

public class SimpleRegisterService implements RegisterService {

    private final UserDao userDao;

    public SimpleRegisterService(UserDao userDao) {this.userDao = userDao;}

    @Override
    public void registerUser(String firstname, String lastname, String phonenumber, String email, String username, String password) throws SQLException, ServiceException {
        try {
            User user = userDao.findByUsername(username);
            if (user != null) {
                throw new ServiceException("User already exists");
            }
            userDao.registerCustomer(firstname,lastname,phonenumber,email,username,password);
        } catch (IllegalArgumentException ex) {
            throw new ServiceException(ex.getMessage());
        }
    }
}
