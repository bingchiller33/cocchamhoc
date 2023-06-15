/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controllers;

import dal.MyCourseDAO;
import java.io.IOException;
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
 * @author LAPTOP
 */
@WebServlet(name = "MyCourseController", urlPatterns = {"/mycourse"})
public class MyCourseController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        MyCourseDAO myCourseDAO = new MyCourseDAO();
        if (request.getSession().getAttribute("user") != null) {
            User u = (User) request.getSession().getAttribute("user");
            if (u != null) {
                List<Course> listMyCourse = myCourseDAO.listMyCourse(u.getUserID());
                request.setAttribute("listMyCourse", listMyCourse);
                if (myCourseDAO.quantityCourse(u.getUserID()) != 0) {
                    request.setAttribute("noCourse", "no have any course");
                }
            }
        }
        request.getRequestDispatcher("/myCourse/myCourse.jsp").forward(request, response);
    }
}
