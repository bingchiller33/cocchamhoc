/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controllers;

import dal.UserDAO;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.User;
import utils.EncryptionUtils;

/**
 *
 * @author hoang
 */
@WebServlet(name = "RegisterController", urlPatterns = {"/register"})
public class RegisterController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/login/register.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String fullname = request.getParameter("fullname");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        request.setAttribute("email", email);
        request.setAttribute("fullname", fullname);

        String url;
        String error = "";

        EncryptionUtils encrypt = new EncryptionUtils();
        UserDAO dao = new UserDAO();
        if (dao.usernameCheck(email)) {
            error += "Email provided is already registered!";
            request.getSession().setAttribute("Email_DUP", error);
        }

        password = encrypt.toMD5(password);

        if (error.length() > 0) {
            url = "/login/register.jsp";
        } else {
            User user = new User(0, fullname, email, password, 1, null, false, null);
            dao.insertUser(user);
            url = "/login/login.jsp";
        }

        RequestDispatcher rd = getServletContext().getRequestDispatcher(url);
        rd.forward(request, response);
    }
}
