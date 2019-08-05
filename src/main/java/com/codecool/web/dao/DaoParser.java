package com.codecool.web.dao;

import com.codecool.web.model.Role;

import java.sql.SQLException;

public class DaoParser {

    public static Role roleParser(String role) throws SQLException {
        Role parsedRole;
        switch (role){
            case "CUSTOMER":
                parsedRole = Role.CUSTOMER;
                break;
            case "MANAGER" :
                parsedRole = Role.MANAGER;
                break;
            case "EMPLOYEE" :
                parsedRole = Role.EMPLOYEE;
                break;
            default:
                throw new SQLException("Role can't be parsed from Result set!");
        }
        return parsedRole;
    }

}
