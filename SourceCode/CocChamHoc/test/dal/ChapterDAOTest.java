/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package dal;

import java.util.List;
import java.util.Map;
import model.Chapter;
import model.Lesson;
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
public class ChapterDAOTest {
    
    public ChapterDAOTest() {
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
     * Test of getCourseChapters method, of class ChapterDAO.
     */
    @Test
    public void testGetCourseChapters() throws Exception {
        System.out.println("getCourseChapters");
        int courseId = 0;
        ChapterDAO instance = new ChapterDAO();
        List<Chapter> expResult = null;
        List<Chapter> result = instance.getCourseChapters(courseId);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of findChapterById method, of class ChapterDAO.
     */
    @Test
    public void testFindChapterById() {
        System.out.println("findChapterById");
        List<Chapter> chapters = null;
        int id = 0;
        ChapterDAO instance = new ChapterDAO();
        Chapter expResult = null;
        Chapter result = instance.findChapterById(chapters, id);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getGroupedLesson method, of class ChapterDAO.
     */
    @Test
    public void testGetGroupedLesson() throws Exception {
        System.out.println("getGroupedLesson");
        List<Chapter> chapters = null;
        ChapterDAO instance = new ChapterDAO();
        Map<Chapter, List<Lesson>> expResult = null;
        Map<Chapter, List<Lesson>> result = instance.getGroupedLesson(chapters);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getNextChapterNumber method, of class ChapterDAO.
     */
    @Test
    public void testGetNextChapterNumber() throws Exception {
        System.out.println("getNextChapterNumber");
        int courseId = 0;
        ChapterDAO instance = new ChapterDAO();
        int expResult = 0;
        int result = instance.getNextChapterNumber(courseId);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of createDefaulChapter method, of class ChapterDAO.
     */
    @Test
    public void testCreateDefaulChapter() throws Exception {
        System.out.println("createDefaulChapter");
        int courseId = 0;
        ChapterDAO instance = new ChapterDAO();
        int expResult = 0;
        int result = instance.createDefaulChapter(courseId);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getChapterByID method, of class ChapterDAO.
     */
    @Test
    public void testGetChapterByID() {
        System.out.println("getChapterByID");
        int courseId = 0;
        int chapterNumber = 0;
        ChapterDAO instance = new ChapterDAO();
        Chapter expResult = null;
        Chapter result = instance.getChapterByID(courseId, chapterNumber);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of updateChapter method, of class ChapterDAO.
     */
    @Test
    public void testUpdateChapter() throws Exception {
        System.out.println("updateChapter");
        String chapterName = "Module 1 - Java Basics-Update";
        int chapterNumber = 5;
        int courseId = 1;
        ChapterDAO instance = new ChapterDAO();
        instance.updateChapter(chapterName, chapterNumber, courseId);
        // TODO review the generated test code and remove the default call to fail.
       
    }

    /**
     * Test of deleteChapter method, of class ChapterDAO.
     */
    @Test
    public void testDeleteChapter() throws Exception {
        System.out.println("deleteChapter");
        int chapterNumber = 0;
        int courseId = 0;
        ChapterDAO instance = new ChapterDAO();
        instance.deleteChapter(chapterNumber, courseId);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
