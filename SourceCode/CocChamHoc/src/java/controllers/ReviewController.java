/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controllers;

import dal.CertificateDAO;
import dal.ExamDAO;
import dal.ExamPapersDAO;
import dal.QuestionDAO;
import dal.UserAnswerDAO;
import dal.UserEnrollDAO;
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
import model.Exam;
import model.ExamPapers;
import model.Question;
import model.User;
import utils.ParseUtils;

/**
 *
 * @author Viet
 */
@WebServlet(name = "ReviewController", urlPatterns = {"/learn/review"})
public class ReviewController extends HttpServlet {

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
            //Database Access Object
            ExamDAO ed = new ExamDAO();
            ExamPapersDAO epd = new ExamPapersDAO();
            UserAnswerDAO uad = new UserAnswerDAO();
            QuestionDAO qd = new QuestionDAO();
            CertificateDAO cd = new CertificateDAO();
            UserEnrollDAO ued = new UserEnrollDAO();
            // Load questions and user's answers
            int attemptId = ParseUtils.parseIntWithDefault(request.getParameter("attemptId"), -1);          
            ExamPapers exampaper = epd.getExamPaperByID(attemptId);
            int examId = exampaper.getExamID();
            Exam exam = ed.getExamByID(examId);
            List<Question> questionList = qd.getQuestions(examId);
            Map<Question, Integer> userAnswers = uad.getUserAnswers(questionList, attemptId);
            
            // Check if the user can get certficate
            User user = (User)request.getSession().getAttribute("user");
            int userID = user.getUserID();
            int courseID = exam.getCourseID();
            if(!cd.hasCertificate(userID, courseID)){
                if(cd.isEligibleForCertificate(ed.getPassAttempt(userID, courseID), ed.getExamCount(courseID))){
                    ued.completeCourse(userID, courseID);
                    cd.issueCertificate(user.getUserID(), courseID);
                }
            }
            request.setAttribute("backUrl", "/learn/exam?examId="+examId);
            request.setAttribute("state", 2);
            request.setAttribute("exam", exam);
            request.setAttribute("paper", exampaper);
            request.setAttribute("questions", questionList);
            request.setAttribute("questionCount", questionList.size());
            request.setAttribute("userAnswers", userAnswers);
            request.getRequestDispatcher("/exam/review.jsp").forward(request, response);
        } catch (SQLException ex) {
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
