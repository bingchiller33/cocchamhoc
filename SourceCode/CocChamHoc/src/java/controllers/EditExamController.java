/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controllers;

import dal.ChapterDAO;
import dal.ChoiceDAO;
import dal.CourseDAO;
import dal.ExamCRUDDAO;
import dal.LessonDAO;
import dal.QuestionCRUDDAO;
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
import model.ChoiceCRUD;
import model.Course;
import model.ExamCRUD;
import model.Lesson;
import model.QuestionCRUD;
import utils.ParseUtils;

/**
 *
 * @author Phuoc
 */
@WebServlet(name = "EditExamController", urlPatterns = {"/admin/edit-exam"})
public class EditExamController extends HttpServlet {

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
            int chapterNumber = ParseUtils.parseIntWithDefault(request.getParameter("chapterNumber"), -1);
            int examId = ParseUtils.parseIntWithDefault(request.getParameter("examId"), -1);
            CourseDAO courseDAO = new CourseDAO();
            ChapterDAO chapterDAO = new ChapterDAO();
            LessonDAO lessonDAO = new LessonDAO();
            ExamCRUDDAO examDAO = new ExamCRUDDAO();
            QuestionCRUDDAO questionDAO = new QuestionCRUDDAO();
            List<Chapter> chapters = chapterDAO.getCourseChapters(courseId);
            List<QuestionCRUD> questions = questionDAO.getQuestion(examId);
            if (chapters.isEmpty()) {
                response.sendRedirect("/admin/courses");
                return;
            }
            Map<Chapter, List<Lesson>> lessonMap = chapterDAO.getGroupedLesson(chapters);

            List<Lesson> lessons = lessonDAO.findLessons(lessonMap, chapterId);

            List<ExamCRUD> exams = examDAO.getExam(courseId);

            ExamCRUD exam = examDAO.getExamById(examId, courseId);

            Map<QuestionCRUD, List<ChoiceCRUD>> choiceMap = questionDAO.getGroupChoice(questions);
            Course course = courseDAO.getCourseById(courseId);

            Chapter chapter = chapterDAO.getChapterByID(courseId, chapterNumber);
            request.setAttribute("backUrl", "/admin");
            request.setAttribute("course", course);
            request.setAttribute("chapters", chapters);
            request.setAttribute("chapter", chapter);
            request.setAttribute("lessonMap", lessonMap);
            request.setAttribute("lessons", lessons);
            request.setAttribute("exams", exams);
            request.setAttribute("exam", exam);
            request.setAttribute("questions", questions);
            request.setAttribute("choiceMap", choiceMap);
            request.setAttribute("showAdd", course.getPublishDate() == null);
            request.getRequestDispatcher("/courseEditor/editExam.jsp").include(request, response);
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
            String examName = request.getParameter("ExamName");
            String duration = request.getParameter("ExamDuration");
            int examId = ParseUtils.parseIntWithDefault(request.getParameter("examId"), -1);
            ExamCRUDDAO examDAO = new ExamCRUDDAO();
            QuestionCRUDDAO questionDAO = new QuestionCRUDDAO();
            ChoiceDAO choiceDAO = new ChoiceDAO();

            examDAO.updateExam(examName, duration, courseId, examId);
            // Update QuestionCRUD
//            int i = 0;
//            while (request.getParameter("ExamQuestionDetail" + i) != null) {
//                String questionDetail = request.getParameter("ExamQuestionDetail" + i);
//                int questionId = Integer.parseInt(request.getParameter("QuestionId" + i));
//                questionDAO.updateQuestion(questionId, examId, questionDetail);
//                i++;
//            }
            // Update ChoiceCRUD
            int i = 0;
            while (request.getParameter("ExamQuestionDetail" + i) != null) {
                String questionDetail = request.getParameter("ExamQuestionDetail" + i);
                int questionId = Integer.parseInt(request.getParameter("QuestionId" + i));
                questionDAO.updateQuestion(questionId, examId, questionDetail);

                // Update ChoiceCRUD for each question
                int j = 0;
                while (request.getParameter("QuestionChoiceDetail" + i + "-" + j) != null) {
                    String choiceDetail = request.getParameter("QuestionChoiceDetail" + i + "-" + j);
                    String isTrueAnswer = request.getParameter("IsTrueAnswer" + i + "-" + j);
                    int choiceId = Integer.parseInt(request.getParameter("ChoiceId"  + i + "-" + j));
                    choiceDAO.updateChoice(choiceDetail, Boolean.parseBoolean(isTrueAnswer), questionId, choiceId);
                    j++;
                }

                i++;
            }
            processRequest(request, response);
        } catch (SQLException e) {
            Logger.getLogger(EditChapterController.class.getName()).log(Level.SEVERE, null, e);
        }

    }

    private void processDelete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            int courseId = ParseUtils.parseIntWithDefault(request.getParameter("courseId"), -1);
            int examId = ParseUtils.parseIntWithDefault(request.getParameter("examId"), -1);
            ExamCRUDDAO examDAO = new ExamCRUDDAO();
            examDAO.deleteExam(courseId, examId);
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
