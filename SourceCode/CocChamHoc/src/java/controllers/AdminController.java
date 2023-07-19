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
import java.util.List;
import model.Course;
import model.User;

/**
 *
 * @author Viet
 */
@WebServlet(name = "AdminController", urlPatterns = {"/admin"})
public class AdminController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet AdminController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AdminController at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        CourseDAO cd = new CourseDAO();

        // Designer courses data
        User loggedUser = (User) request.getSession().getAttribute("user");
        List<Course> courseByIdData = cd.getGetCourseById(loggedUser.getUserID());
        String filterValue = request.getParameter("filterValue");

        // Admin courses data
        List<Course> courseData = cd.getCourse();

        if (filterValue == null || filterValue.isEmpty()) {
            request.setAttribute("courseData", courseData);
        } else if (filterValue.equals("assigned")) {
            request.setAttribute("designerCourses", courseByIdData);
        } else {
            request.setAttribute("courseData", courseData);
        }
        request.setAttribute("filterValue", filterValue);
        request.getRequestDispatcher("admin/admin.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //Unused Method
    }

}
