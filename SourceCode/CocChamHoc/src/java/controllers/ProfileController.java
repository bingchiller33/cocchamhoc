/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controllers;

import dal.ProfileDAO;
import dal.UserDAO;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import model.User;
import utils.EncryptionUtils;

/**
 *
 * @author hoang
 */
public class ProfileController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        User loggedUser = (User) request.getSession().getAttribute("user");
        if (loggedUser == null) {
            response.sendRedirect("/login");
        } else {
            request.setAttribute("username", loggedUser.getFullName());
            request.setAttribute("phoneNumber", loggedUser.getPhoneNumber());
            request.setAttribute("gender", loggedUser.isGender());
            request.setAttribute("dob", loggedUser.getDob());
            request.setAttribute("email", loggedUser.getEmail());
            if ("success".equals(request.getSession().getAttribute("success"))) {
                request.getSession().removeAttribute("Email_DUP");
            }
            String url = "/profileDetail/profileDetail.jsp";
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String formId = request.getParameter("formId");
        ProfileDAO dao = new ProfileDAO();
        User loggedUser = (User) request.getSession().getAttribute("user");
        String url = "";
        if (formId != null) {
            switch (formId) {
                case "profileForm":
                    String username = request.getParameter("username");
                    String phoneNumber = request.getParameter("phoneNumber");

                    // gender
                    String genderStr = request.getParameter("gender");
                    boolean gender = false; // Default value for gender is null

                    if (genderStr != null && !genderStr.isEmpty()) {
                        if (genderStr.equalsIgnoreCase("male")) {
                            gender = true;
                        }
                    }

                    // dob
                    String dobStr = request.getParameter("dob");
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                    java.util.Date utilDate = null;
                    java.sql.Date dob = null;
                    try {
                        utilDate = dateFormat.parse(dobStr);
                        dob = new java.sql.Date(utilDate.getTime());
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }

                    dao.updateProfile(username, phoneNumber, gender, dob, loggedUser.getUserID());

                    loggedUser.setFullName(username);
                    loggedUser.setPhoneNumber(phoneNumber);
                    loggedUser.setGender(gender);
                    loggedUser.setDob(dob);
                    break;
                case "changeEmailForm":
                    String email = request.getParameter("email");
                    String password = request.getParameter("password");
                    EncryptionUtils encrypt = new EncryptionUtils();
                    String password2 = encrypt.toMD5(password);

                    String error = "";
                    UserDAO userDao = new UserDAO();

                    if (!userDao.getPassword(loggedUser.getEmail()).equals(password2)) {
                        error += "Wrong password";
                        request.getSession().setAttribute("Email_DUP", error);
                    } else if (userDao.usernameCheck(email)) {
                        error += "Email provided is already registered!";
                        request.getSession().setAttribute("Email_DUP", error);
                    }

                    if (error.length() > 0) {
                        url = "/profile";
                    } else {
                        dao.updateEmail(email, loggedUser.getUserID());
                        loggedUser.setEmail(email);
                        url = "/profile";
                        request.getSession().setAttribute("success", "success");
                    }

                    break;
                case "changePasswordForm":

                    break;
                default:
                    throw new AssertionError();
            }
        }

        response.sendRedirect(url);
    }
}
