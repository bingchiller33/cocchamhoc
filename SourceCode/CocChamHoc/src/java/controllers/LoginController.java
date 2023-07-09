/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controllers;

import model.User;
import dal.UserDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.List;
import utils.EncryptionUtils;

/**
 *
 * @author LAPTOP
 */
@WebServlet(name = "LoginController", urlPatterns = {"/login"})
public class LoginController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getSession().getAttribute("user") != null) {
            User user = (User) request.getSession().getAttribute("user");
            request.setAttribute("email", user.getEmail());
            request.setAttribute("password", user.getPassword()); 
        }
        request.getRequestDispatcher("/login/login.jsp").forward(request, response);
        boolean inValid = "".equals(request.getSession().getAttribute("validate"));
        if (!inValid) {
            request.getSession().setAttribute("validate", "");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        boolean remember = "".equals(request.getParameter("remember-account"));
        String validate = "Email or password is incorrect.";
        UserDAO userDAO = new UserDAO();
        EncryptionUtils eu = new EncryptionUtils();
        List<User> isUser = userDAO.checkUser(email, eu.toMD5(password)); 
        List<User> isUser2 = userDAO.checkUser(email, password);
        if (!isUser.isEmpty() || !isUser2.isEmpty()) {
            try {
                request.getSession().setAttribute("user", userDAO.getUser(email, eu.toMD5(password)));
            } catch (SQLException e) {
                e.printStackTrace();
            }
            if (!remember) {
                Cookie cEmail = new Cookie("email", email);
                Cookie cPassword = new Cookie("password", password);
                cEmail.setMaxAge(60 * 60 * 24);
                cPassword.setMaxAge(60 * 60 * 24);
                response.addCookie(cEmail);
                response.addCookie(cPassword);
            }
            if (userDAO.checkAdmin(email, eu.toMD5(password))) {
                response.sendRedirect("/admin");
                return;
            }
            request.getSession().setAttribute("validate", "");
            response.sendRedirect("/");
        } else {
            request.getSession().setAttribute("validate", validate);
            response.sendRedirect("/login");
        }
    }
}
