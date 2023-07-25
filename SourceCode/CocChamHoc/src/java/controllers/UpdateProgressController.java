/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controllers;

import dal.ProgressDAO;
import dal.UserEnrollDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.User;
import utils.ParseUtils;

/**
 *
 * @author PC
 */
@WebServlet(name = "UpdateProgressController", urlPatterns = {"/update-progress"})
public class UpdateProgressController extends HttpServlet {

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
            response.setContentType("text/html;charset=UTF-8");
            int courseId = ParseUtils.parseIntWithDefault(request.getParameter("courseId"), -1);
            int chapterId = ParseUtils.parseIntWithDefault(request.getParameter("chapterId"), -1);
            int lessonNumber = ParseUtils.parseIntWithDefault(request.getParameter("lessonNumber"), -1);
            ProgressDAO progressDAO = new ProgressDAO();
            UserEnrollDAO ued = new UserEnrollDAO();
            User u = (User) request.getSession().getAttribute("user");
            
            progressDAO.setLessonProgress(chapterId, lessonNumber, u.getUserID(), true);
            ued.trackProgress(u.getUserID(), courseId);
            String redirect = "/learn/video?courseId=" + courseId + "&chapterId=" + chapterId + "&lessonNumber=" + lessonNumber;
            
            response.sendRedirect(redirect);
        } catch (SQLException ex) {
            Logger.getLogger(UpdateProgressController.class.getName()).log(Level.SEVERE, null, ex);
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
        processRequest(request, response);
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

}