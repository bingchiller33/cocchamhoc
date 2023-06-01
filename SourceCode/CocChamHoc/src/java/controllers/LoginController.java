/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controllers;

import model.Users;
import dal.UserDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import utils.EncryptionUtils;

/**
 *
 * @author LAPTOP
 */
@WebServlet(name = "LoginController", urlPatterns = {"/login"})
public class LoginController extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/login/login.jsp").forward(req, resp);
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String remember = request.getParameter("remember-account");
        String validate = "Email or password is incorrect.";
        UserDAO userDAO = new UserDAO();
        EncryptionUtils eu = new EncryptionUtils();
        List<Users> isUser = userDAO.checkUser(email, eu.toMD5(password));
        if (!isUser.isEmpty()) {
            if (remember.equals("on")) {
                Cookie cEmail = new Cookie("email", email);
                Cookie cPassword = new Cookie("password", password);
                cEmail.setMaxAge(60 * 60 * 24);
                cPassword.setMaxAge(60 * 60 * 24);
                response.addCookie(cEmail);
                response.addCookie(cPassword);
            }
            if (userDAO.checkAdmin(email, eu.toMD5(password))){
                response.sendRedirect("/admin");
                return;
            }
            request.getSession().setAttribute("validate", "");
            response.sendRedirect("/");
        }else {
            request.getSession().setAttribute("validate", validate);
            response.sendRedirect("/login");
        }
    }
}
