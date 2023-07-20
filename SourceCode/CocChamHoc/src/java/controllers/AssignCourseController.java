/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controllers;

import dal.CourseDAO;
import dal.UserDAO;
import java.io.IOException;
import java.io.PrintWriter;
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

            UserDAO userDAO = new UserDAO();
            CourseDAO courseDAO = new CourseDAO();
            List<Course> courseData = courseDAO.getCourse();
            List unassignedList = userDAO.getUnassignedDesigners(courseId);
            List assignedList = userDAO.getAssignedDesigners(courseId);
            Course course = courseDAO.getCourseById(courseId);
            if (course == null) {
                response.sendRedirect("/admin");
                return;
            }
            
            request.setAttribute("assignedList", assignedList);
            request.setAttribute("backUrl", "/admin");
            request.setAttribute("course", course);
            request.setAttribute("courseData", courseData);
            request.setAttribute("unassignedList", unassignedList);
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

}
