/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controllers;

import dal.CertificateDAO;
import java.io.IOException; 
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse; 
import model.User; 

/**
 *
 * @author LAPTOP
 */
@WebServlet(name = "CertificateController", urlPatterns = {"/certificate"})
public class CertificateController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        User user = (User) request.getSession().getAttribute("user");
        CertificateDAO certificateDAO = new CertificateDAO();
        if (user == null) {
            response.sendRedirect("/login");
        } else {
            int id = Integer.parseInt(request.getParameter("id"));
            request.setAttribute("title", certificateDAO.getTitle(user.getUserID(), id));
            request.setAttribute("issueDate", certificateDAO.getDate(user.getUserID(), id));
            request.setAttribute("userName", user.getFullName());
            request.getRequestDispatcher("./certificate/certificate.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

}
