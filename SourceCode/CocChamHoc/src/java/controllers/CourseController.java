/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controllers;

import dal.CourseDAO;
import dal.LessonDAO;
import dal.RateDAO;
import dal.UserEnrollDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import model.User;
import utils.ParseUtils;

/**
 *
 * @author Viet
 */
@WebServlet(name = "CourseController", urlPatterns = {"/course"})
public class CourseController extends HttpServlet {

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
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet CourseController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet CourseController at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
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
        int courseID = Integer.parseInt(request.getParameter("id"));
        RateDAO rateDAO = new RateDAO();
        CourseDAO cd = new CourseDAO();
        UserEnrollDAO ued = new UserEnrollDAO();
        User user = (User) request.getSession().getAttribute("user");
        LessonDAO lesson = new LessonDAO();

        try {
            request.setAttribute("lessonData", lesson.getLessonData(courseID));
            request.setAttribute("courseData", cd.getCourseById(courseID));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (user == null) {
            request.setAttribute("isEnroll", false);
        } else {
            request.setAttribute("isEnroll", ued.isEnroll(user.getUserID(), courseID));
            request.setAttribute("userRateNo", rateDAO.getUserRateNo(courseID, user.getUserID()));
        }
        request.setAttribute("review", rateDAO.getReviewRate(courseID));
        request.setAttribute("rateNo",
                rateDAO.rateAvg(
                        rateDAO.getQuantityRateNo(courseID),
                        rateDAO.getSumRateNo(courseID)));
        request.setAttribute("rateAvg",
                rateDAO.rate(
                        rateDAO.getQuantityRateNo(courseID),
                        rateDAO.getSumRateNo(courseID)));
        request.setAttribute("titleName", rateDAO.getTitleCourse(courseID));
        request.setAttribute("countRating", rateDAO.getQuantityRateNo(courseID));
        request.setAttribute("reviewNo", rateDAO.getQuantityReviewCorse(courseID));
        request.setAttribute("courseID", courseID);
       
        request.getRequestDispatcher("/courseDetail/courseDetail.jsp").forward(request, response);
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
        int courseID = Integer.parseInt(request.getParameter("id"));
        int rateNo = ParseUtils.parseIntWithDefault(request.getParameter("rating_1"), -1);
        String review = request.getParameter("review");
        RateDAO rateDAO = new RateDAO();
        User user = (User) request.getSession().getAttribute("user");
        int userRateNo = 0;
        if (user != null) {
            userRateNo = rateDAO.getUserRateNo(courseID, user.getUserID());
        }
        if (rateNo <= 0 && user == null || rateNo > 0 && user != null || userRateNo != 0) {
            if (user != null) {
                if (rateDAO.getCourseId(user.getUserID(), courseID).isEmpty()) {
                    rateDAO.insertRatings(courseID, user.getUserID(), rateNo, review);
                }
                rateDAO.updateRating(courseID, user.getUserID(), rateNo);
                if (!review.trim().isEmpty()) {
                    rateDAO.updateReview(courseID, user.getUserID(), review);
                }
                if (rateNo > 0) {
                    rateDAO.updateTime(courseID, user.getUserID());
                }
            }
            response.sendRedirect("/course?id=" + courseID);
        } else if (user == null && rateNo > 0) {
            response.sendRedirect("/login");
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
