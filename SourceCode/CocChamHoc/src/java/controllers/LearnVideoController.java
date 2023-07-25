/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controllers;

import dal.ChapterDAO;
import dal.CourseDAO;
import dal.ExamDAO;
import dal.LessonDAO;
import dal.ProgressDAO;
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
import model.Exam;
import model.Lesson;
import model.User;
import utils.ParseUtils;

/**
 *
 * @author Yui
 */
@WebServlet(name = "LearnVideoController", urlPatterns = {"/learn/video"})
public class LearnVideoController extends HttpServlet {

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
            int chapterId = ParseUtils.parseIntWithDefault(request.getParameter("chapterId"), -1);
            int lessonNumber = ParseUtils.parseIntWithDefault(request.getParameter("lessonNumber"), -1);

            CourseDAO courseDAO = new CourseDAO();
            ChapterDAO chapterDAO = new ChapterDAO();
            LessonDAO lessonDAO = new LessonDAO();
            ExamDAO examDAO = new ExamDAO();
            List<Exam> exams = examDAO.getExams(courseId);
            List<Chapter> chapters = chapterDAO.getCourseChapters(courseId);
            if (chapters.isEmpty()) {
                request.getRequestDispatcher("/notFound.jsp").forward(request, response);
                return;
            }

            Map<Chapter, List<Lesson>> lessonMap = chapterDAO.getGroupedLesson(chapters);
            if (lessonMap.isEmpty()) {
                request.getRequestDispatcher("/notFound.jsp").forward(request, response);
                return;
            }

            List<Lesson> lessons = lessonDAO.findLessons(lessonMap, chapterId);
            if (lessons.isEmpty()) {
                request.getRequestDispatcher("/notFound.jsp").forward(request, response);
                return;
            }

            Chapter chapter = chapterDAO.findChapterById(chapters, chapterId);
            Lesson lesson = lessonDAO.findLesson(lessons, lessonNumber);
            if (lesson == null) {
                request.getRequestDispatcher("/notFound.jsp").forward(request, response);
                return;
            }

            String nextLessonUrl = "#";
            String prevLessonUrl = "#";

            Lesson nextLesson = lessonDAO.getNextLesson(courseId, chapter.getChapterNumber(), lesson.getLessonNumber());
            Lesson prevLesson = lessonDAO.getPrevLesson(courseId, chapter.getChapterNumber(), lesson.getLessonNumber());

            if (nextLesson != null) {
                nextLessonUrl = "/learn/video?courseId=" + courseId + "&chapterId=" + nextLesson.getChapterId() + "&lessonNumber=" + nextLesson.getLessonNumber();
            }

            if (prevLesson != null) {
                prevLessonUrl = "/learn/video?courseId=" + courseId + "&chapterId=" + prevLesson.getChapterId() + "&lessonNumber=" + prevLesson.getLessonNumber();
            }

            ProgressDAO progressDAO = new ProgressDAO();
            int chapterId1 = ParseUtils.parseIntWithDefault(request.getParameter("chapterId"), -1);
            int LessonNumber = ParseUtils.parseIntWithDefault(request.getParameter("lessonNumber"), -1);
            User u = (User) request.getSession().getAttribute("user");
                
            Map<Chapter, Map<Lesson, Boolean>> allProgress = progressDAO.getAllLessonsProgress(lessonMap, u.getUserID());
            boolean progress = progressDAO.getLessonProgress(chapterId1, LessonNumber, u.getUserID());
            
            request.setAttribute("allProgress", allProgress);
            request.setAttribute("progress", progress);
            
            request.setAttribute("backUrl", "/course?id=" + courseId);
            request.setAttribute("course", courseDAO.getCourseById(courseId));
            request.setAttribute("chapters", chapters);
            request.setAttribute("chapter", chapter);
            request.setAttribute("lessonMap", lessonMap);
            request.setAttribute("lessons", lessons);
            request.setAttribute("lesson", lesson);
            request.setAttribute("nextUrl", nextLessonUrl);
            request.setAttribute("prevUrl", prevLessonUrl);
            request.setAttribute(("exams"), exams);
            request.getRequestDispatcher("/learn/learn.jsp").include(request, response);
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
