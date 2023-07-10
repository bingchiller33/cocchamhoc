/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controllers;

import dal.ExamDAO;
import dal.ExamPapersDAO;
import dal.QuestionDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Exam;
import model.ExamPapers;
import model.Question;
import model.User;
import utils.GradeUtils;
import utils.ParseUtils;

/**
 *
 * @author Viet
 */
@WebServlet(name = "AttemptController", urlPatterns = {"/learn/attempt"})
public class AttemptController extends HttpServlet {
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
            //Database Access Object
            ExamDAO ed = new ExamDAO();
            QuestionDAO qd = new QuestionDAO();
            ExamPapersDAO epd = new ExamPapersDAO();

            //get URL param
            int examId = ParseUtils.parseIntWithDefault(request.getParameter("examId"), -1);
            //get exam
            Exam exam = ed.getExamByID(examId);
            if (exam == null) {
                request.getRequestDispatcher("/notFound.jsp").forward(request, response);
                return;
            }
            //get current user
            User user = (User) request.getSession().getAttribute("user");
            //insert new attempt or retrieve current attempt
            int attemptID = epd.attempt(user.getUserID(), examId);
            ExamPapers exampaper = epd.getExamPaperByID(attemptID);
            List<Question> questionList = qd.getQuestions(examId);
            
            Timestamp startTime = exampaper.getTimeStart();
            Timestamp currentTimestamp = new Timestamp(System.currentTimeMillis());
            startTime.setTime(startTime.getTime()+(exam.getDuration().toLocalTime().toSecondOfDay()*1000));
            long differenceInMillis = startTime.getTime() - currentTimestamp.getTime();
            long differenceInSeconds = TimeUnit.MILLISECONDS.toSeconds(differenceInMillis);
            //set attribute
            request.setAttribute("state", 1);
            request.setAttribute("questionCount", questionList.size());
            request.setAttribute("attemptId", attemptID);
            request.setAttribute("remainingTime", differenceInSeconds);
            request.setAttribute("questions", questionList);
            request.setAttribute("exam", exam);
            request.getRequestDispatcher("/exam/attempt.jsp").forward(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(ExamController.class.getName()).log(Level.SEVERE, null, ex);
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
        try {
            QuestionDAO qd = new QuestionDAO();
            ExamPapersDAO epd = new ExamPapersDAO();
            int attemptId = ParseUtils.parseIntWithDefault(request.getParameter("attemptId"), -1);
            ExamPapers exampaper = epd.getExamPaperByID(attemptId);
            int examId = exampaper.getExamID();

            List<Question> questionList = qd.getQuestions(examId);
            Map<Question, Integer> useranswer = new HashMap<>();
            for (Question q : questionList) {
                String userAnswer = request.getParameter(Integer.toString(q.getQuestionID()));
                if (userAnswer == null) {
                    useranswer.put(q, -1);
                } else {
                    useranswer.put(q, Integer.parseInt(userAnswer));
                }
            }
            epd.completeAttempt(attemptId, useranswer, GradeUtils.grade(questionList, useranswer));
            response.sendRedirect("/learn/review?attemptId=" + attemptId);
        } catch (SQLException ex) {
            Logger.getLogger(ExamController.class.getName()).log(Level.SEVERE, null, ex);
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
