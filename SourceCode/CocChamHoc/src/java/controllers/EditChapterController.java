/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controllers;

import dal.ChapterDAO;
import dal.CourseDAO;
import dal.LessonDAO;
import java.io.IOException;
import java.io.PrintWriter;
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
 * @author Phuoc
 */
@WebServlet(name = "EditChapterController", urlPatterns = {"/admin/edit-chapter"})
public class EditChapterController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
//    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
//    throws ServletException, IOException {
//        try {
//            String courseId = request.getParameter("courseId");
//            String chapterNumber = request.getParameter("chapterNumber");
//            int courseIdi = Integer.parseInt(courseId);
//            int chapterNumberi = Integer.parseInt(chapterNumber);
//            ChapterDAO chapterDAO = new ChapterDAO();
//            List<Chapter> chapters = chapterDAO.getCourseChapters(courseIdi);
//            Chapter chapter = chapterDAO.getChapterByID(courseId, chapterNumber);
//            request.setAttribute("chapter",chapter);
//            request.getRequestDispatcher("/courseEditor/editChapter.jsp").forward(request, response);
//        } catch (SQLException e) {
//            Logger.getLogger(EditChapterController.class.getName()).log(Level.SEVERE, null, e);
//        }
//    }
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            int courseId = ParseUtils.parseIntWithDefault(request.getParameter("courseId"), -1);
            int chapterId = ParseUtils.parseIntWithDefault(request.getParameter("chapterId"), -1);
            int chapterNumber = ParseUtils.parseIntWithDefault(request.getParameter("chapterNumber"), -1);
            CourseDAO courseDAO = new CourseDAO();
            ChapterDAO chapterDAO = new ChapterDAO();
            LessonDAO lessonDAO = new LessonDAO();
            List<Chapter> chapters = chapterDAO.getCourseChapters(courseId);
            if (chapters.isEmpty()) {
                response.sendRedirect("/admin/courses");
                return;
            }
            Map<Chapter, List<Lesson>> lessonMap = chapterDAO.getGroupedLesson(chapters);

            List<Lesson> lessons = lessonDAO.findLessons(lessonMap, chapterId);

            Chapter chapter = chapterDAO.getChapterByID(courseId, chapterNumber);
            request.setAttribute("course", courseDAO.getCourseById(courseId));
            request.setAttribute("chapters", chapters);
            request.setAttribute("chapter", chapter);
            request.setAttribute("lessonMap", lessonMap);
            request.setAttribute("lessons", lessons);
            request.getRequestDispatcher("/courseEditor/editChapter.jsp").include(request, response);
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
     * @throws IOException if an I/O error0 occurs
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
            int chapterNumber = ParseUtils.parseIntWithDefault(request.getParameter("chapterNumber"), -1);
            String name = request.getParameter("chapterName");
            ChapterDAO chapterDAO = new ChapterDAO();
            chapterDAO.updateChapter(name, chapterNumber, courseId);
            processRequest(request, response);
        } catch (SQLException e) {
            Logger.getLogger(EditChapterController.class.getName()).log(Level.SEVERE, null, e);
        }

    }

    private void processDelete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            int courseId = ParseUtils.parseIntWithDefault(request.getParameter("courseId"), -1);
            int chapterNumber = ParseUtils.parseIntWithDefault(request.getParameter("chapterNumber"), -1);
            ChapterDAO chapterDAO = new ChapterDAO();
            chapterDAO.deleteChapter(chapterNumber, courseId);
            processRequest(request, response);
        } catch (SQLException e) {
             Logger.getLogger(EditChapterController.class.getName()).log(Level.SEVERE, null, e);
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
