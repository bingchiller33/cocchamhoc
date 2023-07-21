/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package dal;

import java.util.List;
import model.Exam;
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
public class ExamDAOTest {
    
    public ExamDAOTest() {
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
     * Test of getExams method, of class ExamDAO.
     */
    @Test
    public void testGetExams() throws Exception {
        System.out.println("getExams");
        int courseID = 2;
        ExamDAO instance = new ExamDAO();
        List<Exam> expResult = null;
        List<Exam> result = instance.getExams(courseID);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of getExamByID method, of class ExamDAO.
     */
    @Test
    public void testGetExamByID() throws Exception {
        System.out.println("getExamByID");
        int examID = 0;
        ExamDAO instance = new ExamDAO();
        Exam expResult = null;
        Exam result = instance.getExamByID(examID);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getPassAttempt method, of class ExamDAO.
     */
    @Test
    public void testGetPassAttempt() throws Exception {
        System.out.println("getPassAttempt");
        int userId = 0;
        int courseId = 0;
        ExamDAO instance = new ExamDAO();
        int expResult = 0;
        int result = instance.getPassAttempt(userId, courseId);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getExamCount method, of class ExamDAO.
     */
    @Test
    public void testGetExamCount() throws Exception {
        System.out.println("getExamCount");
        int courseID = 0;
        ExamDAO instance = new ExamDAO();
        int expResult = 0;
        int result = instance.getExamCount(courseID);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
