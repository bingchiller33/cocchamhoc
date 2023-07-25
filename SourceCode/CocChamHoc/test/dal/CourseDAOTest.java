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
 * @author Phuoc
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
        String searchQuery = " ";
        int categoryId = -1;
        int levelId = -1;
        int durationLow = 3600;
        int durationHigh = 22000;
        boolean showHiddenCourses = false;
        String sortName = "";
        String sortDuration = "";
        String sortPublishDate = "";
        int page = 1;
        int pageSize = 5;
        CourseDAO instance = new CourseDAO();
        List<Course> expResult = null;
        List<Course> result = instance.searchCourses(searchQuery, categoryId, levelId, durationLow, durationHigh, showHiddenCourses, sortName, sortDuration, sortPublishDate, page, pageSize);
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
        String searchQuery = "java";
        int categoryId = -1;
        int levelId = -1;
        int durationLow = -1;
        int durationHigh = -1;
        boolean showHiddenCourses = false;
        CourseDAO instance = new CourseDAO();
        int expResult = 1;
        int result = instance.searchCoursesCount(searchQuery, categoryId, levelId, durationLow, durationHigh, showHiddenCourses);
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
        int courseId = 1;
        String name = "SQL";
        int categoryId = 1;
        int levelId = 1;
        int newVersionId = 0;
        String lecturer = "";
        String imgUrl = "";
        String description = "";
        Date publishDate = null;
        CourseDAO instance = new CourseDAO();
        instance.updateCourse(courseId, name, categoryId, levelId, newVersionId, lecturer, imgUrl, description, publishDate);
        // TODO review the generated test code and remove the default call to fail.
        
    }

    /**
     * Test of setCourseDiscontinue method, of class CourseDAO.
     */
    @Test
    public void testSetCourseDiscontinue() throws Exception {
        System.out.println("setCourseDiscontinue");
        int courseId = 0;
        boolean isDiscontinued = false;
        CourseDAO instance = new CourseDAO();
        instance.setCourseDiscontinue(courseId, isDiscontinued);
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
