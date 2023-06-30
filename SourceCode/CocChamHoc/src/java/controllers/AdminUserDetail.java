/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controllers;

import dal.MyCourseDAO;
import dal.UserDAO;
import dal.UserEnrollDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Course;
import model.User;
import utils.ParseUtils;

/**
 *
 * @author Yui
 */
@WebServlet(name = "AdminUserDetail", urlPatterns = {"/admin/userDetail"})
public class AdminUserDetail extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            int id = ParseUtils.parseIntWithDefault(request.getParameter("id"), -1);

            UserDAO userDAO = new UserDAO();
            MyCourseDAO myCourseDAO = new MyCourseDAO();
            UserEnrollDAO userEnrollDAO = new UserEnrollDAO();

            User u = userDAO.getUserById(id);
            List<Course> courses = myCourseDAO.listMyCourse(id);
            Map<Integer, String> statMap = userEnrollDAO.getEnrollmentStatus(id);

            request.setAttribute("user", u);
            request.setAttribute("courses", courses);
            request.setAttribute("statusMap", statMap);

            request.getRequestDispatcher("/admin/editUser.jsp").include(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(AdminUserDetail.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = ParseUtils.defaultIfEmpty(request.getParameter("action"), "");
        switch (action) {
            case "Restrict":
                processRestrict(request, response);
                break;
            case "Appeal":
                processAppeal(request, response);
                break;
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    private void processRestrict(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = ParseUtils.parseIntWithDefault(request.getParameter("id"), -1);
        Date restrictUntil = ParseUtils.parseDateWithDefault(request.getParameter("banUntil"), Date.valueOf("2000-01-01"));
        String reason = ParseUtils.defaultIfEmpty(request.getParameter("banReason"), "");

        UserDAO userDAO = new UserDAO();
        userDAO.updateUserRestriction(id, restrictUntil, reason);

        request.setAttribute("restrictStatus", "Restricting user successfully!");
        processRequest(request, response);

    }

    private void processAppeal(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = ParseUtils.parseIntWithDefault(request.getParameter("id"), -1);

        UserDAO userDAO = new UserDAO();
        userDAO.updateUserRestriction(id, Date.valueOf("2000-01-01"), "");

        request.setAttribute("restrictStatus", "Appeal user successfully!");
        processRequest(request, response);

    }

}
