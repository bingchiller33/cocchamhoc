/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controllers;

import dal.CourseDAO;
import dal.UserDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Course;
import utils.ParseUtils;

/**
 *
 * @author hoang
 */
public class AssignCourseController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try {
            int courseId = ParseUtils.parseIntWithDefault(request.getParameter("courseId"), -1);
            int assignedPage = ParseUtils.parseIntWithDefault(request.getParameter("assignedPage"), -1);
            int unassignedPage = ParseUtils.parseIntWithDefault(request.getParameter("unassignedPage"), -1);

            CourseDAO courseDAO = new CourseDAO();
            List<Course> courseData = courseDAO.getCourse();
            Course course = courseDAO.getCourseById(courseId);
            if (course == null) {
                response.sendRedirect("/admin");
                return;
            }

            displayAssignedDesignersList(request, assignedPage, courseId);
            displayUnassignedDesignersList(request, unassignedPage, courseId);

            request.setAttribute("backName", "admin");
            request.setAttribute("backUrl", "/admin");
            request.setAttribute("course", course);
            request.setAttribute("courseData", courseData);
            request.getRequestDispatcher("/admin/assignCourse.jsp").include(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(EditCourseController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String[] designerIdsStr = request.getParameterValues("designerIds");
        int courseId = ParseUtils.parseIntWithDefault(request.getParameter("courseId"), -1);
        CourseDAO courseDAO = new CourseDAO();

        if (designerIdsStr != null) {
            for (String designerIdStr : designerIdsStr) {
                int designerId = Integer.parseInt(designerIdStr);
                courseDAO.assignCourseToDesigner(courseId, designerId);
            }
        }

        response.sendRedirect(request.getContextPath() + "/admin/assign-course?courseId=" + courseId);
    }

    private void displayAssignedDesignersList(HttpServletRequest request, int page, int courseId) {
        int size = 5;
        String searchAssigned = ParseUtils.defaultIfEmpty(request.getParameter("searchAssigned"), "");

        UserDAO userDAO = new UserDAO();

        List assignedList = userDAO.getAssignedDesigners(page, size, courseId, searchAssigned);

        int totalCount = userDAO.getAssignedDesignersCount(courseId);
        int pageCount = (int) Math.ceil((double) totalCount / size);

        request.setAttribute("assignedPageCount", pageCount);
        request.setAttribute("assignedListCount", size);
        request.setAttribute("assignedList", assignedList);
    }

    private void displayUnassignedDesignersList(HttpServletRequest request, int page, int courseId) {
        int size = 5;
        String searchUnassigned = ParseUtils.defaultIfEmpty(request.getParameter("searchUnassigned"), "");

        UserDAO userDAO = new UserDAO();

        List unassignedList = userDAO.getUnassignedDesigners(page, size, courseId, searchUnassigned);

        int totalCount = userDAO.getUnassignedDesignersCount(courseId);
        int pageCount = (int) Math.ceil((double) totalCount / size);

        request.setAttribute("unassignedPageCount", pageCount);
        request.setAttribute("unassignedListCount", size);
        request.setAttribute("unassignedList", unassignedList);
    }

}
