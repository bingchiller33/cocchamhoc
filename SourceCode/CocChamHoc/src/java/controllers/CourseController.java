/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controllers;

import dal.CourseDAO;
import dal.LessonDAO;
import dal.RateDAO;
import dal.UserDAO;
import dal.UserEnrollDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.List;
import model.Rate;
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
        String f = (String) request.getSession().getAttribute("filterRate");
        String p = (String) request.getSession().getAttribute("pagination");
        int filterRate = -1;
        int pagination = 1;
        int pageSize = 5;
        if (!"".equals(f) && f != null) {
            filterRate = Integer.parseInt(f);
        }
        if (!"".equals(p) && p != null) {
            pagination = Integer.parseInt(p);
        }
        RateDAO rateDAO = new RateDAO();
        CourseDAO cd = new CourseDAO();
        UserEnrollDAO ued = new UserEnrollDAO();
        User user = (User) request.getSession().getAttribute("user");
        LessonDAO lesson = new LessonDAO();
        UserDAO userDAO = new UserDAO();
        List<Rate> list = rateDAO.getReviewRate(courseID, filterRate, pagination - 1, pageSize);
        int listCount = rateDAO.getSizeFilter(courseID, filterRate);
        int pageCount = (int) Math.ceil(listCount / (float) pageSize);
        System.out.println(pagination);
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
            request.setAttribute("userId", user.getUserID());
        }
        request.setAttribute("review", list);
        request.setAttribute("five", rateDAO.getQuantity5(courseID));
        request.setAttribute("four", rateDAO.getQuantity4(courseID));
        request.setAttribute("three", rateDAO.getQuantity3(courseID));
        request.setAttribute("two", rateDAO.getQuantity2(courseID));
        request.setAttribute("one", rateDAO.getQuantity1(courseID));
        request.setAttribute("all", rateDAO.getQuantityAll(courseID));
        request.setAttribute("pageCount", pageCount);
        request.setAttribute("pagination", pagination);
        request.setAttribute("rateNo",
                rateDAO.rateAvg(
                        rateDAO.getQuantityRateNo(courseID),
                        rateDAO.getSumRateNo(courseID)));
        request.setAttribute("rateAvg",
                rateDAO.rate(
                        rateDAO.getQuantityRateNo(courseID),
                        rateDAO.getSumRateNo(courseID)));
        request.setAttribute("users", userDAO.getUsers());
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
        int courseID = ParseUtils.parseIntWithDefault(request.getParameter("id"), -1);
        int rateNo = ParseUtils.parseIntWithDefault(request.getParameter("rating_1"), -1);
        String review = request.getParameter("review");
        String status = request.getParameter("status");
        String reviewUpdate = "";
        String filterRate = request.getParameter("filterRate");
        String pagination = request.getParameter("pagination");
        request.getSession().setAttribute("filterRate", filterRate);
        request.getSession().setAttribute("pagination", pagination);
        String[] values = request.getParameterValues("reviewUpdate");
        if (values != null) {
            for (String value : values) {
                if (!value.isEmpty()) {
                    reviewUpdate = value;
                }
            }
        }
        int rId = ParseUtils.parseIntWithDefault(request.getParameter("rId"), -1);
        RateDAO rateDAO = new RateDAO();
        User user = (User) request.getSession().getAttribute("user");
        int userRateNo = 0;
        if (user != null && courseID > 0) {
            userRateNo = rateDAO.getUserRateNo(courseID, user.getUserID());
        }
        if (rateNo <= 0 && user == null || rateNo > 0 && user != null || userRateNo != 0) {
            if (user != null && courseID > 0) {
                if (rateDAO.getCourseId(user.getUserID(), courseID).isEmpty()) {
                    rateDAO.insertRatings(courseID, user.getUserID(), rateNo, review);
                }
                if (status != null && status.equals("Report")) {
                    rateDAO.updateReport(courseID, rId);
                }
                if (status != null && status.equals("Delete")) {
                    rateDAO.deleteRate(courseID, user.getUserID());
                }
                rateDAO.updateRating(courseID, user.getUserID(), rateNo);
                if (review != null) {
                    rateDAO.updateReview(courseID, user.getUserID(), review);
                }
                if (reviewUpdate != null) {
                    rateDAO.updateReview(courseID, user.getUserID(), reviewUpdate);
                }
                if (rateNo > 0 && courseID > 0) {
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
