/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controllers;

import dal.CourseDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import utils.ParseUtils;

/**
 *
 * @author hoang
 */
@WebServlet(name = "UnassignCourseController", urlPatterns = {"/admin/unassign-course"})
public class UnassignCourseController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet UnassignCourseController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet UnassignCourseController at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
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
                courseDAO.unassignDesignerFromCourse(designerId, courseId);
            }
        }

        response.sendRedirect(request.getContextPath() + "/admin/assign-course?courseId=" + courseId);
    }

}
