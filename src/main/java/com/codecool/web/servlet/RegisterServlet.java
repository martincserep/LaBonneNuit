package com.codecool.web.servlet;

import com.codecool.web.dao.UserDao;
import com.codecool.web.dao.database.DatabaseUserDao;
import com.codecool.web.model.User;
import com.codecool.web.service.LoginService;
import com.codecool.web.service.RegisterService;
import com.codecool.web.service.exception.ServiceException;
import com.codecool.web.service.simple.SimpleLoginService;
import com.codecool.web.service.simple.SimpleRegisterService;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

@WebServlet("/register")
public final class RegisterServlet extends AbstractServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try (Connection connection = getConnection(req.getServletContext())) {
            UserDao userDao = new DatabaseUserDao(connection);
            RegisterService registerService = new SimpleRegisterService(userDao);

            String firstname = req.getParameter("firstname");
            String lastname = req.getParameter("lastname");
            String phonenumber = req.getParameter("phonenumber");
            String email = req.getParameter("email");
            String username = req.getParameter("username");
            String password = req.getParameter("password");

            User user = registerService.registerUser(firstname,lastname,phonenumber,email,username, password);
            req.getSession().setAttribute("user", user);
            sendMessage(resp, HttpServletResponse.SC_OK, user);
        } catch (SQLException ex) {
            handleSqlError(resp, ex);
        } catch (ServiceException e) {
            e.printStackTrace();
        }
    }
}
