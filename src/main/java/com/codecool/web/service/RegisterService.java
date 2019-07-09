package com.codecool.web.service;

import com.codecool.web.model.User;
import com.codecool.web.service.exception.ServiceException;

import java.sql.SQLException;

public interface RegisterService {
    void registerUser(String firstname, String lastname, String phonenumber, String email, String username, String password) throws SQLException, ServiceException;
}
