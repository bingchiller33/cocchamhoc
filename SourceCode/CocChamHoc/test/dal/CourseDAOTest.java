/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package dal;

import java.sql.Date;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import model.Course;
import model.LessonLocation;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author LAPTOP
 */
public class CourseDAOTest {
    
    public CourseDAOTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of searchCourses method, of class CourseDAO.
     */
    @Test
    public void testSearchCourses() throws Exception {
        System.out.println("searchCourses");
        String searchQuery = "";
        int categoryId = 0;
        int levelId = 0;
        int durationLow = 0;
        int durationHigh = 0;
        String sortName = "";
        String sortDuration = "";
        String sortPublishDate = "";
        int page = 0;
        int pageSize = 0;
        CourseDAO instance = new CourseDAO();
        List<Course> expResult = null;
        List<Course> result = instance.searchCourses(searchQuery, categoryId, levelId, durationLow, durationHigh, sortName, sortDuration, sortPublishDate, page, pageSize);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        
    }

    /**
     * Test of getCourse method, of class CourseDAO.
     */
    @Test
    public void testGetCourse() {
        System.out.println("getCourse");
        CourseDAO instance = new CourseDAO();
        ArrayList<Course> expResult = null;
        ArrayList<Course> result = instance.getCourse();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        
    }

    /**
     * Test of searchCoursesCount method, of class CourseDAO.
     */
    @Test
    public void testSearchCoursesCount() throws Exception {
        System.out.println("searchCoursesCount");
        String searchQuery = "";
        int categoryId = 0;
        int levelId = 0;
        int durationLow = 0;
        int durationHigh = 0;
        CourseDAO instance = new CourseDAO();
        int expResult = 0;
        int result = instance.searchCoursesCount(searchQuery, categoryId, levelId, durationLow, durationHigh);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        
    }

    /**
     * Test of getCourseById method, of class CourseDAO.
     */
    @Test
    public void testGetCourseById() throws Exception {
        System.out.println("getCourseById");
        int id = 0;
        CourseDAO instance = new CourseDAO();
        Course expResult = null;
        Course result = instance.getCourseById(id);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        
    }

    /**
     * Test of createDefaultCourse method, of class CourseDAO.
     */
    @Test
    public void testCreateDefaultCourse() throws Exception {
        System.out.println("createDefaultCourse");
        CourseDAO instance = new CourseDAO();
        int expResult = 0;
        int result = instance.createDefaultCourse();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        
    }

    /**
     * Test of updateCourse method, of class CourseDAO.
     */
    @Test
    public void testUpdateCourse() throws Exception {
        System.out.println("updateCourse");
        int courseId = 0;
        String name = "";
        int categoryId = 0;
        int levelId = 0;
        String lecturer = "";
        String imgUrl = "";
        String description = "";
        Date publishDate = null;
        CourseDAO instance = new CourseDAO();
        instance.updateCourse(courseId, name, categoryId, levelId, lecturer, imgUrl, description, publishDate);
        // TODO review the generated test code and remove the default call to fail.
        
    }

    /**
     * Test of deleteCourse method, of class CourseDAO.
     */
    @Test
    public void testDeleteCourse() throws Exception {
        System.out.println("deleteCourse");
        int courseId = 0;
        CourseDAO instance = new CourseDAO();
        instance.deleteCourse(courseId);
        // TODO review the generated test code and remove the default call to fail.
        
    }

    /**
     * Test of getFirstLesson method, of class CourseDAO.
     */
    @Test
    public void testGetFirstLesson() throws Exception {
        System.out.println("getFirstLesson");
        int courseId = 0;
        CourseDAO instance = new CourseDAO();
        LessonLocation expResult = null;
        LessonLocation result = instance.getFirstLesson(courseId);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        
    }

    /**
     * Test of fromResultSet method, of class CourseDAO.
     */
    @Test
    public void testFromResultSet() throws Exception {
        System.out.println("fromResultSet");
        ResultSet rs = null;
        CourseDAO instance = new CourseDAO();
        Course expResult = null;
        Course result = instance.fromResultSet(rs);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        
    }

    /**
     * Test of getNewestCoursesInfo method, of class CourseDAO.
     */
    @Test
    public void testGetNewestCoursesInfo() {
        System.out.println("getNewestCoursesInfo");
        int limit = 0;
        CourseDAO instance = new CourseDAO();
        ArrayList<Course> expResult = null;
        ArrayList<Course> result = instance.getNewestCoursesInfo(limit);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        
    }
    
}
