/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controllers;

import dal.CertificateDAO;
import dal.ChapterDAO;
import dal.CourseDAO;
import dal.ExamDAO;
import dal.ExamPapersDAO;
import dal.LessonDAO;
import dal.QuestionDAO;
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
import model.Exam;
import model.ExamPapers;
import model.Lesson;
import model.Question;
import model.User;
import utils.ParseUtils;

/**
 *
 * @author Viet
 */
@WebServlet(name = "ExamController", urlPatterns = {"/learn/exam"})
public class ExamController extends HttpServlet {

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
            // Database Access Object
            ExamDAO ed = new ExamDAO();
            CourseDAO courseDAO = new CourseDAO();
            QuestionDAO qd = new QuestionDAO();
            ChapterDAO chapterDAO = new ChapterDAO();
            ExamPapersDAO epd = new ExamPapersDAO();
            CertificateDAO cd = new CertificateDAO();
            // URL param
            int examId = ParseUtils.parseIntWithDefault(request.getParameter("examId"), -1);
            Exam exam = ed.getExamByID(examId);
            int courseId = exam.getCourseID();
            // Load Navbar
            List<Exam> exams = ed.getExams(courseId);
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
            // Load Exam INF and Prev Exam attempt
            if (exam == null) {
                request.getRequestDispatcher("/notFound.jsp").forward(request, response);
                return;
            }
            User user = (User)request.getSession().getAttribute("user");
            List<ExamPapers> examPapers = epd.getExamPapers(examId, user.getUserID());
            ExamPapers bestAttempt = epd.getBestAttempt(user.getUserID(), examId);            
            // Set Navbar data
            request.setAttribute("backUrl", "/course?id=" + courseId);
            request.setAttribute("course", courseDAO.getCourseById(courseId));
            request.setAttribute("chapters", chapters);
            request.setAttribute("lessonMap", lessonMap);
            request.setAttribute("exams", exams);            
            // Set Exam INF and Prev Exam attempt data
            if(cd.hasCertificate(user.getUserID(), courseId))
                request.setAttribute("toCert", "/certificate?id="+courseId);
            request.setAttribute("questionCount", qd.getQuestionCount(examId));
            if(bestAttempt!=null){
                request.setAttribute("bestAttempt", bestAttempt);
            }
            request.setAttribute("exam", exam);
            if(!examPapers.isEmpty())
                request.setAttribute("examPapers", examPapers);
            request.getRequestDispatcher("/exam/exam.jsp").forward(request, response);
        } catch (Exception ex) {
            Logger.getLogger(ExamController.class.getName()).log(Level.SEVERE, null, ex);
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
