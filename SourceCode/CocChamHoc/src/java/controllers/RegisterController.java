/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controllers;

import dal.UserDAO;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.User;

/**
 *
 * @author hoang
 */
public class RegisterController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String fullname = request.getParameter("fullname");
        String password = request.getParameter("password");
        String repassword = request.getParameter("repassword");
        String email = request.getParameter("email");
        request.setAttribute("email", email);
        request.setAttribute("fullname", fullname);

        String url = "";
        String error = "";
        UserDAO dao = new UserDAO();
        if (dao.usernameCheck(email)) {
            error += "Email existed!";
        }

        if (password.equals(repassword)) {
            error += "Mật khẩu không khớp <br/>.";
        }

        if (error.length() > 0) {
            url = "/login/register.jsp";
        } else {
            User user = new User(0, fullname, email, password, null, true, "0", true);
            dao.insertUser(user);
            url = "/success.jsp";
        }
        RequestDispatcher rd = getServletContext().getRequestDispatcher(url);
        rd.forward(request, response);
    }

//            String email = request.getParameter("email");
//            UserDAO dao = new UserDAO();
//            boolean exist = dao.userCheck(email);
//            if (exist) {
//                request.setAttribute("MSG_ERROR", "The account already exists in the system! Please use this email to login.");
//                request.setAttribute("email", email);
//                request.getRequestDispatcher("../login/register.jsp").forward(request, response);
//            } else {
//                String username = request.getParameter("username");
//                String password = request.getParameter("password");
//                String re_password = request.getParameter("password-confirm");
//
//                if (!password.equals(re_password)) {
//                    request.setAttribute("MSG_ERROR", "Password not match!");
//                    request.getRequestDispatcher("../login/register.jsp").forward(request, response);
//
//                } else {
//                    User user = new User(0, username, email, password, null, false, null, false);
//                    boolean check = dao.insertUser(user);
//                    if (check) {
//                        request.setAttribute("MSG_SUCCESS", "You have successfully registered!");
//                        request.getRequestDispatcher("../login/login.jsp").forward(request, response);
//
//                    }
//                }
//            }
}
