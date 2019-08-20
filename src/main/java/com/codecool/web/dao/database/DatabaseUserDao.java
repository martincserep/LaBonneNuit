package com.codecool.web.dao.database;

import com.codecool.web.dao.DaoParser;
import com.codecool.web.dao.UserDao;
import com.codecool.web.model.Role;
import com.codecool.web.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public final class DatabaseUserDao extends AbstractDao implements UserDao {

    public DatabaseUserDao(Connection connection) {
        super(connection);
    }

    public List<User> findAll() throws SQLException {
        String sql = "SELECT id, username, password FROM users";
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            List<User> users = new ArrayList<>();
            while (resultSet.next()) {
                users.add(fetchUser(resultSet));
            }
            return users;
        }
    }

    @Override
    public User findByUsername(String username) throws SQLException {
        if (username == null || "".equals(username)) {
            throw new IllegalArgumentException("Username cannot be null or empty");
        }
        String sql = "SELECT userid, firstname, lastname, phonenumber, email, username, password, userrole FROM users WHERE username = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, username);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return fetchUser(resultSet);
                }
            }
        }
        return null;
    }

    @Override
    public void registerCustomer(String getfirstname,String getlastname, String getphonenumber, String getemail, String getusername, String getpassword) throws SQLException{
        String sql = "INSERT INTO users (firstname,lastname,phonenumber,email,username,password,userrole) VALUES (?,?,?,?, ?,?,?)";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1,getfirstname);
        statement.setString(2,getlastname);
        statement.setString(3,getphonenumber);
        statement.setString(4,getemail);
        statement.setString(5,getusername);
        statement.setString(6,getpassword);
        statement.setString(7,"CUSTOMER");
        executeInsert(statement);
    }

    @Override
    public void deleteUser(Integer userId) throws SQLException {
        String sql = "DELETE FROM users WHERE userid = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1,userId);
        statement.execute();
    }

    @Override
    public Boolean hasShippingAddress(Integer userId) throws SQLException {
        String sql = "SELECT city, address, postalcode FROM users WHERE userid = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1,userId);
        ResultSet resultSet = statement.executeQuery();

        if (resultSet.next()){
            return false;
        } else {
            return true;
        }

    }

    @Override
    public void setShippingAddress(Integer userId, String city, String address, String postalCode) throws SQLException {
        String sql = "INSERT INTO users (city,address,postalcode) VALUES (?,?,?) WHERE userid = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1,city);
        statement.setString(2,address);
        statement.setString(3,postalCode);
        statement.setInt(4,userId);
        executeInsert(statement);
    }

    @Override
    public void updateShippingAddress(Integer userId, String city, String address, String postalCode) throws SQLException {
        String sql = "UPDATE  users SET city = ?, address = ?, postalcode = ? WHERE userid = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1,city);
        statement.setString(2,address);
        statement.setString(3,postalCode);
        statement.setInt(4,userId);
        executeInsert(statement);
    }

    private User fetchUser(ResultSet resultSet) throws SQLException {
        Integer userId = resultSet.getInt("userid");
        String firstname = resultSet.getString("firstname");
        String lastname = resultSet.getString("lastname");
        String phonenumber = resultSet.getString("phonenumber");
        String email = resultSet.getString("email");
        String username = resultSet.getString("username");
        String password = resultSet.getString("password");
        Role role = DaoParser.roleParser(resultSet.getString("userrole"));
        System.out.println(role);
        return new User(userId, firstname, lastname, phonenumber, email, username, password, role);
    }
}
