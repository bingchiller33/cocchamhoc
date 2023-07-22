/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controllers;

import dal.CourseDAO;
import dal.MyCourseDAO;
import dal.UserEnrollDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
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
        try{
            MyCourseDAO myCourseDAO = new MyCourseDAO();
        UserEnrollDAO ued = new UserEnrollDAO();
        CourseDAO courseDAO = new CourseDAO();
        List<Course> courses = courseDAO.getNewestCoursesInfo(5);
        request.setAttribute("sliderList", courses);
        if (request.getSession().getAttribute("user") != null) {
            User u = (User) request.getSession().getAttribute("user");
            if (u != null) {
                List<Course> listMyCourse = myCourseDAO.listMyCourse(u.getUserID());
                Map<Course, Integer> progress = ued.getAllProgress(listMyCourse, u.getUserID());
                Map<Course, Integer> counts = courseDAO.getChaptersCount(listMyCourse);
                request.setAttribute("listMyCourse", listMyCourse);
                request.setAttribute("progress", progress);
                request.setAttribute("counts", counts);
                if (myCourseDAO.quantityCourse(u.getUserID()) != 0) {
                    request.setAttribute("noCourse", "no have any course");
                }
            }
        }
        request.getRequestDispatcher("/myCourse/myCourse.jsp").forward(request, response);
        }
        catch(SQLException ex){
            
        }
        
    }
}
