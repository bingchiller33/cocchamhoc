/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controllers;

import dal.CategoryDAO;
import dal.LevelDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import dal.*;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Course;
import utils.ParseUtils;

/**
 *
 * @author Yui
 */
@WebServlet(name = "HomeController", urlPatterns = {"/"})
public class HomeController extends HttpServlet {

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
            out.println("<title>Servlet HomeController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet HomeController at " + request.getContextPath() + "</h1>");
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
        int pageSize = 12;
        int page = ParseUtils.parseIntWithDefault(request.getParameter("page"), 1) - 1;
        int category = ParseUtils.parseIntWithDefault(request.getParameter("category"), -1);
        int level = ParseUtils.parseIntWithDefault(request.getParameter("level"), -1);
        String duration = ParseUtils.defaultIfEmpty(request.getParameter("duration"), "0-0");
        String[] parts = duration.split("-");
        int low = ParseUtils.parseIntWithDefault(parts[0], 0);
        int high = 0;
        if(parts.length >= 2) {
            high = ParseUtils.parseIntWithDefault(parts[1], 0);
        }

        String search = request.getParameter("search");
        if (search == null) {
            search = "";
        }

        CourseDAO courseDao = new CourseDAO();
        CategoryDAO catDao = new CategoryDAO();
        LevelDAO levelDao = new LevelDAO();
        try {
            List<Course> list = courseDao.searchCourses(search, category, level, low, high, page, pageSize);
            int listCount = courseDao.searchCoursesCount(search, category, level, low, high);
            int pageCount = (int) Math.ceil(listCount / (float) pageSize);

            request.setAttribute("list", list);
            request.setAttribute("pageCount", pageCount);
            request.setAttribute("listCount", listCount);

            request.setAttribute("categories", catDao.getAllCategories());
            request.setAttribute("levels", levelDao.getAllLevels());
            request.getRequestDispatcher("home/home.jsp").forward(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
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
        //Unused method
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
