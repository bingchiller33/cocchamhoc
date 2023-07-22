/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package controllers;

import dal.CategoryDAO;
import dal.CourseDAO;
import dal.LevelDAO;
import java.sql.Date;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import utils.ParseUtils;

/**
 *
 * @author PC
 */
public class EditCourseControllerTest {
    
    public EditCourseControllerTest() {
        CategoryDAO categoryDAO = new CategoryDAO();
            LevelDAO levelDAO = new LevelDAO();
            CourseDAO courseDAO = new CourseDAO();
    }

    @Test
    public void testProcessRequest() throws Exception {
         String name = CategoryDAO.class.getName();
         name = "";
    }

    @Test
    public void testDoGet() throws Exception {
    }

    @Test
    public void testDoPost() throws Exception {
    }

    @Test
    public void testGetServletInfo() {
    }
    
}
