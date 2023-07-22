/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controllers;

import dal.CategoryDAO;
import dal.ChapterDAO;
import dal.CourseDAO;
import dal.ExamCRUDDAO;
import dal.LessonDAO;
import dal.LevelDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
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
import model.ExamCRUD;
import model.Lesson;
import model.LessonLocation;
import model.User;
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
            ExamCRUDDAO examDAO = new ExamCRUDDAO();
            List<Chapter> chapters = chapterDAO.getCourseChapters(courseId);
            Map<Chapter, List<Lesson>> lessonMap = chapterDAO.getGroupedLesson(chapters);

            Course course = courseDAO.getCourseById(courseId);
            if (course == null) {
                response.sendRedirect("/admin");
                return;
            }

            List<Category> categories = categoryDAO.getAllCategories();
            List<model.Level> levels = levelDAO.getAllLevels();
            List<ExamCRUD> exams = examDAO.getExam(courseId);

            HttpSession session = request.getSession();
            User user = (User) session.getAttribute("user");

            request.setAttribute("backUrl", "/admin");
            request.setAttribute("nextCourses", courseDAO.getCourse());
            request.setAttribute("chapters", chapters);
            request.setAttribute("lessonMap", lessonMap);
            request.setAttribute("course", course);
            request.setAttribute("categories", categories);
            request.setAttribute("levels", levels);
            request.setAttribute("exams", exams);
            request.setAttribute("admin", user != null && user.getRole() == 3);
            request.setAttribute("showAdd", course.getPublishDate() == null);

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
                processSave(request, response);
                break;
            case "Publish":
                processPublish(request, response);
                break;
            case "Discontinue":
                processDiscontinue(request, response);
                break;
            case "Recontinue":
                processRecontinue(request, response);
                break;
            case "Delete":
                processDelete(request, response);
                break;
        }
    }

    
    private void processRecontinue(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            int courseId = ParseUtils.parseIntWithDefault(request.getParameter("courseId"), -1);
            CourseDAO courseDAO = new CourseDAO();
            courseDAO.setCourseDiscontinue(courseId, false);
            request.setAttribute("status", "Recontinue course successfully!");
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(EditCourseController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void processDiscontinue(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            int courseId = ParseUtils.parseIntWithDefault(request.getParameter("courseId"), -1);
            CourseDAO courseDAO = new CourseDAO();
            courseDAO.setCourseDiscontinue(courseId, true);
            request.setAttribute("status", "Discontinue course successfully!");
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(EditCourseController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void processPublish(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            int courseId = ParseUtils.parseIntWithDefault(request.getParameter("courseId"), -1);
            CourseDAO courseDAO = new CourseDAO();
            LessonLocation loc = courseDAO.getFirstLesson(courseId);

            if (loc == null) {
                request.setAttribute("status", "Please add a lesson before publish the course!");
                processRequest(request, response);
                return;
            }

            ExamCRUDDAO examDAO = new ExamCRUDDAO();
            List<ExamCRUD> exams = examDAO.getExam(courseId);
            if (exams.isEmpty()) {
                request.setAttribute("status", "Please add an exam before publish the course!");
                processRequest(request, response);
                return;
            }

            request.setAttribute("status", "Publish successfully!");
            processSave(request, response);

        } catch (SQLException ex) {
            Logger.getLogger(EditCourseController.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void processSave(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String action = request.getParameter("action");

            int courseId = ParseUtils.parseIntWithDefault(request.getParameter("courseId"), -1);
            int newVersionId = ParseUtils.parseIntWithDefault(request.getParameter("newVersionId"), -1);
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

            courseDAO.updateCourse(courseId, name, categoryId, levelId, newVersionId, lecturer, courseImgUrl, courseDesc, publishDate);
            processRequest(request, response);

        } catch (SQLException ex) {
            Logger.getLogger(EditCourseController.class
                    .getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(EditCourseController.class
                    .getName()).log(Level.SEVERE, null, ex);
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
