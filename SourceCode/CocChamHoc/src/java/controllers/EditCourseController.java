/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controllers;

import dal.CategoryDAO;
import dal.ChapterDAO;
import dal.CourseDAO;
import dal.LessonDAO;
import dal.LevelDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Category;
import model.Chapter;
import model.Course;
import model.Lesson;
import model.Users;
import utils.ParseUtils;

/**
 *
 * @author Yui
 */
@WebServlet(name = "EditCourseController", urlPatterns = {"/admin/edit-course"})
public class EditCourseController extends HttpServlet {

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
        response.setContentType("text/html;charset=UTF-8");
        try {
            int courseId = ParseUtils.parseIntWithDefault(request.getParameter("courseId"), -1);
            CourseDAO courseDAO = new CourseDAO();
            CategoryDAO categoryDAO = new CategoryDAO();
            LevelDAO levelDAO = new LevelDAO();
            ChapterDAO chapterDAO = new ChapterDAO();
            List<Chapter> chapters = chapterDAO.getCourseChapters(courseId);
            Map<Chapter, List<Lesson>> lessonMap = chapterDAO.getGroupedLesson(chapters);

            Course course = courseDAO.getCourseById(courseId);
            if (course == null) {
                response.sendRedirect("/admin/courses");
                return;
            }

            List<Category> categories = categoryDAO.getAllCategories();
            List<model.Level> levels = levelDAO.getAllLevels();

            request.setAttribute("backUrl", "/admin");
            request.setAttribute("chapters", chapters);
            request.setAttribute("lessonMap", lessonMap);
            request.setAttribute("course", course);
            request.setAttribute("categories", categories);
            request.setAttribute("levels", levels);

            request.getRequestDispatcher("/courseEditor/editCourse.jsp").include(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(EditCourseController.class.getName()).log(Level.SEVERE, null, ex);
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
        String action = request.getParameter("action");
        if (action == null) {
            processRequest(request, response);
            return;
        }

        switch (action) {
            case "Save":
            case "Publish":
            case "Unpublish":
                processSave(request, response);
                break;
            case "Delete":
                processDelete(request, response);
                break;
        }
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
        String action = request.getParameter("action");
        if (action == null) {
            processRequest(request, response);
            return;
        }

        switch (action) {
            case "Save":
            case "Publish":
            case "Unpublish":
                processSave(request, response);
                break;
            case "Delete":
                processDelete(request, response);
                break;
        }
    }

    private void processSave(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String action = request.getParameter("action");

            int courseId = ParseUtils.parseIntWithDefault(request.getParameter("courseId"), -1);
            if (courseId == -1) {
                response.sendRedirect("/admin/courses");
                return;
            }

            CategoryDAO categoryDAO = new CategoryDAO();
            LevelDAO levelDAO = new LevelDAO();
            CourseDAO courseDAO = new CourseDAO();

            String name = request.getParameter("courseName");
            String category = request.getParameter("courseCategory").trim();
            int categoryId = categoryDAO.fromDescription(category).getId();
            String level = request.getParameter("courseLevel").trim();
            int levelId = levelDAO.fromDescription(level).getId();
            String lecturer = request.getParameter("courseLecturer");
            String courseImgUrl = request.getParameter("courseImgUrl");
            String courseDesc = request.getParameter("courseDesc");
            Date publishDate = ParseUtils.parseDateWithDefault(request.getParameter("coursePublishDate"), null);
            if (action.equals("Publish")) {
                publishDate = Date.valueOf(LocalDate.now());
            } else if (action.equals("Unpublish")) {
                publishDate = null;
            }

            courseDAO.updateCourse(courseId, name, categoryId, levelId, lecturer, courseImgUrl, courseDesc, publishDate);
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(EditCourseController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void processDelete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            int courseId = ParseUtils.parseIntWithDefault(request.getParameter("courseId"), -1);
            if (courseId != -1) {
                CourseDAO courseDAO = new CourseDAO();
                courseDAO.deleteCourse(courseId);
            }

            response.sendRedirect("/admin");
        } catch (SQLException ex) {
            Logger.getLogger(EditCourseController.class.getName()).log(Level.SEVERE, null, ex);
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

}
