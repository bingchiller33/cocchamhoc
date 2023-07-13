/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controllers;


import dal.ProgressDAO;
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
@WebServlet(name = "ProgressController", urlPatterns = {"/progress"})
public class ProgressController extends HttpServlet {

 
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
        try {

           
            ProgressDAO progressDAO = new ProgressDAO();
            int chapterId = ParseUtils.parseIntWithDefault(request.getParameter("ChapterID"), -1);
            int LessonNumber = ParseUtils.parseIntWithDefault(request.getParameter("LessonNumber"), -1);
            User u = (User) request.getSession().getAttribute("user");
            // boolean PogressDAO=  progressDAO.setLessonProgress(chapterId, UserID, UserID, true);
            boolean progressd = progressDAO.getLessonProgress( u.getUserID(),LessonNumber,chapterId); 
                request.setAttribute("UnSuccess", 0); 
               request.setAttribute("Success", 1); 
           request.setAttribute("progressd", progressd); 
           
            request.setAttribute("u", u); 
            //    progress = true;
            

           progressDAO.getLessonProgress(u.getUserID(),LessonNumber,chapterId);


        } catch (SQLException ex) {
            Logger.getLogger(EditLessonController.class.getName()).log(Level.SEVERE, null, ex);
        }
        request.getRequestDispatcher("/learn/learn.jsp").forward(request, response);
       
    }

    
}
