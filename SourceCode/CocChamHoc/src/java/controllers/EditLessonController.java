/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controllers;

import dal.ChapterDAO;
import dal.CourseDAO;
import dal.LessonDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Chapter;
import model.Lesson;
import utils.ParseUtils;

/**
 *
 * @author Yui
 */
@WebServlet(name = "EditLessonController", urlPatterns = {"/admin/edit-lesson"})
public class EditLessonController extends HttpServlet {

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
            int courseId = ParseUtils.parseIntWithDefault(request.getParameter("courseId"), -1);
            int chapterId = ParseUtils.parseIntWithDefault(request.getParameter("chapterId"), -1);
            int lessonNumber = ParseUtils.parseIntWithDefault(request.getParameter("lessonNumber"), -1);

            CourseDAO courseDAO = new CourseDAO();
            ChapterDAO chapterDAO = new ChapterDAO();
            LessonDAO lessonDAO = new LessonDAO();
            List<Chapter> chapters = chapterDAO.getCourseChapters(courseId);
            if (chapters.isEmpty()) {
                response.sendRedirect("/admin/courses");
                return;
            }

            Map<Chapter, List<Lesson>> lessonMap = chapterDAO.getGroupedLesson(chapters);
            if (lessonMap.isEmpty()) {
                response.sendRedirect("/admin/edit-course?courseId=" + courseId);
                return;
            }

            List<Lesson> lessons = lessonDAO.findLessons(lessonMap, chapterId);
            if (lessons.isEmpty()) {
                response.sendRedirect("/admin/edit-chapter?courseId=" + courseId + "&chapterId=" + chapterId);
                return;
            }

            Lesson lesson = lessonDAO.findLesson(lessons, lessonNumber);
            if (lesson == null) {
                response.sendRedirect("/admin/edit-chapter?courseId=" + courseId + "&chapterId=" + chapterId);
                return;
            }

            Lesson prevLesson = lessonDAO.findPrevLesson(lessons, lesson);

            request.setAttribute("backUrl", "/admin");
            request.setAttribute("course", courseDAO.getCourseById(courseId));
            request.setAttribute("chapters", chapters);
            request.setAttribute("lessonMap", lessonMap);
            request.setAttribute("lessons", lessons);
            request.setAttribute("lesson", lesson);
            request.setAttribute("prev", prevLesson);

            request.getRequestDispatcher("/courseEditor/editLesson.jsp").include(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(EditLessonController.class.getName()).log(Level.SEVERE, null, ex);
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
        String action = request.getParameter("action");
        if (action == null) {
            processRequest(request, response);
            return;
        }

        switch (action) {
            case "Save":
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
            int courseId = ParseUtils.parseIntWithDefault(request.getParameter("courseId"), -1);
            int chapterId = ParseUtils.parseIntWithDefault(request.getParameter("chapterId"), -1);
            int lessonNumber = ParseUtils.parseIntWithDefault(request.getParameter("lessonNumber"), -1);

            String name = ParseUtils.defaultIfEmpty(request.getParameter("lessonName"), "");
            if (name.length() > 60) {
                request.setAttribute("status", "Name cannot be longer than 60 characters!");
                processRequest(request, response);
                return;
            }

            int lessonPrev = ParseUtils.parseIntWithDefault(request.getParameter("lessonPrev"), 0);
            if (lessonPrev == lessonNumber) {
                request.setAttribute("status", "Previous lesson cannot be the same lesson!");
                processRequest(request, response);
                return;
            }
            
            String video = ParseUtils.defaultIfEmpty(request.getParameter("lessonVid"), "");
            if (video.length() > 500) {
                request.setAttribute("status", "Video Url cannot be longer than 500 characters!");
                processRequest(request, response);
                return;
            }
            
            String desc = request.getParameter("lessonDesc");

            LessonDAO lessonDAO = new LessonDAO();
            lessonDAO.updateLesson(chapterId, lessonNumber, name, video, desc);

            if (lessonPrev + 1 != lessonNumber) {
                lessonDAO.reorderLesson(chapterId, lessonNumber, lessonPrev);
                response.sendRedirect("/admin/edit-lesson?courseId=" + courseId + "&chapterId=" + chapterId + "&lessonNumber=" + (lessonPrev + 1));
                return;
            }

            request.setAttribute("status", "Saved Successfully!");
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(EditLessonController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void processDelete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            int courseId = ParseUtils.parseIntWithDefault(request.getParameter("courseId"), -1);
            int chapterId = ParseUtils.parseIntWithDefault(request.getParameter("chapterId"), -1);
            int lessonNumber = ParseUtils.parseIntWithDefault(request.getParameter("lessonNumber"), -1);

            LessonDAO lessonDAO = new LessonDAO();
            if (lessonNumber > 0) {
                lessonDAO.deleteLession(chapterId, lessonNumber);

                if (lessonNumber == 1) {
                    response.sendRedirect("/admin/edit-course?courseId=" + courseId);
                } else {
                    response.sendRedirect("/admin/edit-lesson?courseId=" + courseId + "&chapterId=" + chapterId + "&lessonNumber=" + (lessonNumber - 1));
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(EditLessonController.class.getName()).log(Level.SEVERE, null, ex);
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
