/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controllers;

import dal.ChapterDAO;
import dal.MyCourseDAO;
import dal.ProgressDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Chapter;
import model.Course;
import model.Lesson;
import model.User;
import utils.ParseUtils;


/**
 *
 * @author PC
 */
@WebServlet(name = "InProgressController", urlPatterns = {"/inprogress"})
public class InProgressController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ProgressDAO progressDAO = new ProgressDAO();
        
        if (request.getSession().getAttribute("user") != null) {
            User u = (User) request.getSession().getAttribute("user");
            if (u != null) {
                
                List<Course> listProgressCourse = progressDAO.listProgressCourse(u.getUserID());
              
                request.setAttribute("listProgressCourse", listProgressCourse);
         //     request.setAttribute("listProgressChapter", listChapter);
                if (progressDAO.quantityCourse(u.getUserID()) != 0) {
                    request.setAttribute("noCourse", "no have any course");
                }
            }
        }
     try {
         
            int courseId = ParseUtils.parseIntWithDefault(request.getParameter("courseId"), -1);
            List <Lesson> lesson = progressDAO.getLessonData(courseId);
            request.setAttribute("lessons", lesson);
    } catch (SQLException ex) {
            Logger.getLogger(EditLessonController.class.getName()).log(Level.SEVERE, null, ex);
        }
        request.getRequestDispatcher("/inProgress/inProgress.jsp").forward(request, response);
    }
}

