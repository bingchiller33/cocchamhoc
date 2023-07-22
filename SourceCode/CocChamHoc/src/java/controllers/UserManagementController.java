/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controllers;

import dal.MyCourseDAO;
import dal.UserDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.User;
import utils.ParseUtils;

/**
 *
 * @author Yui
 */
@WebServlet(name="UserManagementController", urlPatterns={"/admin/users"})
public class UserManagementController extends HttpServlet {
   
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        try {
            int size = 5;
            int page = ParseUtils.parseIntWithDefault(request.getParameter("page"), 1) - 1;
            int role = ParseUtils.parseIntWithDefault(request.getParameter("role"), -1);
            String userSearch = ParseUtils.defaultIfEmpty(request.getParameter("search"), "");
            
            UserDAO userDAO = new UserDAO();
            
            List<User> users = userDAO.searchUsers(userSearch, role, page, size);
            Map<Integer, Integer> map = getCourseEnrolled(users);
            
            request.setAttribute("pageCount", Math.ceil(userDAO.searchUserCount(userSearch, role) / (float) size));
            request.setAttribute("listCount", size);
            
            request.setAttribute("users", users);
            request.setAttribute("enrollMap", map);
            request.getRequestDispatcher("/admin/userManagement.jsp").include(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(UserManagementController.class.getName()).log(Level.SEVERE, null, ex);
        }
    } 
    
    private Map<Integer, Integer> getCourseEnrolled(List<User> users) {
        MyCourseDAO myCourseDAO = new MyCourseDAO();
        Map<Integer, Integer> map = new HashMap<>();
        
        for (User u : users) {
            map.put(u.getUserID(), myCourseDAO.quantityCourse(u.getUserID()));
        }
        
        return map;
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
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
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        processRequest(request, response);
    }

    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
