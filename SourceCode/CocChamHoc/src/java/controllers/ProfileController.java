/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controllers;

import dal.CertificateDAO;
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
        CertificateDAO certificateDAO = new CertificateDAO();
        if (loggedUser == null) {
            response.sendRedirect("/login");
        } else {
            request.setAttribute("username", loggedUser.getFullName());
            request.setAttribute("phoneNumber", loggedUser.getPhoneNumber());
            request.setAttribute("gender", loggedUser.isGender());
            request.setAttribute("dob", loggedUser.getDob());
            request.setAttribute("email", loggedUser.getEmail());
            request.setAttribute("listCourse", certificateDAO.getCourseCer(loggedUser.getUserID()));
            if ("success".equals(request.getSession().getAttribute("success"))) {
                request.getSession().removeAttribute("emailError");
                request.getSession().removeAttribute("wrongPassword");
                request.getSession().removeAttribute("success");
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
        UserDAO userDao = new UserDAO();
        EncryptionUtils encrypt = new EncryptionUtils();
        User loggedUser = (User) request.getSession().getAttribute("user");
        String url = "";
        String error = "";
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
                    String password = encrypt.toMD5(request.getParameter("password"));

                    if (!userDao.getPassword(loggedUser.getEmail()).equals(password)) {
                        error += "Wrong password";
                        request.getSession().setAttribute("emailError", error);
                    } else if (userDao.usernameCheck(email)) {
                        error += "Email provided is already registered!";
                        request.getSession().setAttribute("emailError", error);
                    }

                    if (error.length() > 0) {
                        error = "";
                        url = "/profile";
                    } else {
                        dao.updateEmail(email, loggedUser.getUserID());
                        loggedUser.setEmail(email);
                        url = "/profile";
                        request.getSession().setAttribute("success", "success");
                    }

                    break;
                case "changePasswordForm":
                    String currentPassword = encrypt.toMD5(request.getParameter("currentPassword"));
                    String newPassword = encrypt.toMD5(request.getParameter("newPassword"));

                    if (!userDao.getPassword(loggedUser.getEmail()).equals(currentPassword)) {
                        error += "Wrong current password";
                        request.getSession().setAttribute("wrongPassword", error);
                    }
                    
                     if (error.length() > 0) {
                        error = "";
                        url = "/profile";
                    } else {
                        dao.updatePassword(newPassword, loggedUser.getUserID());
                        url = "/profile";
                        request.getSession().setAttribute("success", "success");
                    }

                    break;
                default:
                    throw new AssertionError();
            }
        }

        response.sendRedirect(url);
    }
}
