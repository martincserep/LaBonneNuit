package com.codecool.web.service.simple;

import com.codecool.web.dao.UserDao;
import com.codecool.web.model.User;
import com.codecool.web.service.LoginService;
import com.codecool.web.service.exception.ServiceException;

import javax.sql.rowset.serial.SerialException;
import java.sql.SQLException;

public final class SimpleLoginService implements LoginService {

    private final UserDao userDao;

    public SimpleLoginService(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public User loginUser(String username, String password) throws SQLException, ServiceException {
        try {
            User user = userDao.findByUsername(username);
            if (user == null) {
                throw new ServiceException("There is no user under the name " + username + ".");
            } else if (!user.getPassword().equals(password)) {
                throw new ServiceException("Wrong password");
            }
            return user;
        } catch (IllegalArgumentException ex) {
            throw new ServiceException(ex.getMessage());
        }
    }
}
