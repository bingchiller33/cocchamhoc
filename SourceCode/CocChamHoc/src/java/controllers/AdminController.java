/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controllers;

import dal.CategoryDAO;
import dal.CourseDAO;
import dal.LevelDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Course;
import model.User;
import utils.ParseUtils;

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
        int pageSize = 5;
        int page = ParseUtils.parseIntWithDefault(request.getParameter("page"), 1) - 1;
        int category = ParseUtils.parseIntWithDefault(request.getParameter("category"), -1);
        int level = ParseUtils.parseIntWithDefault(request.getParameter("level"), -1);
        String duration = ParseUtils.defaultIfEmpty(request.getParameter("duration"), "0-0");
        
        String[] parts = duration.split("-");
        int low = ParseUtils.parseIntWithDefault(parts[0], 0);
        int high = 0;
        if (parts.length >= 2) {
            high = ParseUtils.parseIntWithDefault(parts[1], 0);
        }

        String search = request.getParameter("search");
        if (search == null) {
            search = "";
        }
        String sortName = request.getParameter("sortName");
        String sortDuration = request.getParameter("sortDuration");
        String sortPublishDate = request.getParameter("sortPublishDate");
        
        CourseDAO cd = new CourseDAO();
        CategoryDAO catDao = new CategoryDAO();
        LevelDAO levelDao = new LevelDAO();
        try {
            // Designer courses data
            User loggedUser = (User) request.getSession().getAttribute("user");
            List<Course> courseByIdData = cd.getAssignedCoursesById(loggedUser.getUserID());
            String filterValue = request.getParameter("filterValue");
            
            // Admin courses data
            List<Course> list = cd.searchCourses(search, category, level, low, high, true, sortName, sortDuration, sortPublishDate, page, pageSize);
            int listCount = cd.searchCoursesCount(search, category, level, low, high, false);
            int pageCount = (int) Math.ceil(listCount / (float) pageSize);

            if (filterValue == null || filterValue.isEmpty()) {
                request.setAttribute("courseData", list);
            } else if (filterValue.equals("assigned")) {
                request.setAttribute("designerCourses", courseByIdData);
            } else {
                request.setAttribute("courseData", list);
            }
            request.setAttribute("pageCount", pageCount);
            request.setAttribute("listCount", listCount);
            request.setAttribute("categories", catDao.getAllCategories());
            request.setAttribute("levels", levelDao.getAllLevels());
            request.setAttribute("filterValue", filterValue);
            request.getRequestDispatcher("admin/admin.jsp").forward(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //Unused Method
    }

}
