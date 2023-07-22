/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package dal;
import model.Course;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author PC
 */
public class CourseDAOTest {
    
    public CourseDAOTest() {
    }

    @Test
    public void testSearchCourses() throws Exception {
        
        
    }

    @Test
    public void testGetCourse() {
      
    }

    @Test
    public void testSearchCoursesCount() throws Exception {
        
    }

    @Test
    public void testGetCourseById() throws Exception {
      
    }

    @Test
    public void testCreateDefaultCourse() throws Exception {
        
    }

    @Test
    public void testUpdateCourse() throws Exception {
         CourseDAO in = new CourseDAO();
        int id = 0;
        String result =String.format("haha", null);
        Course rs = in.getCourseById(id);
        assertEquals(result, rs);
        
    }

    @Test
    public void testDeleteCourse() throws Exception {
         CourseDAO in = new CourseDAO();
        int id = 0;
        System.out.println("getCourseById");
        Course i =null;
        Course rs = in.getCourseById(id);
        String name ="ds";
        assertEquals(i, rs);
    }

    @Test
    public void testGetFirstLesson() throws Exception {
       
    }

    @Test
    public void testFromResultSet() throws Exception {
       
    }

    @Test
    public void testGetNewestCoursesInfo() {
         
    }
    
}
