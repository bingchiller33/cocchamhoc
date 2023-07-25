/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package dal;

import java.util.List;
import model.Category;
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
public class CategoryDAOTest {
    
    public CategoryDAOTest() {
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
     * Test of getAllCategories method, of class CategoryDAO.
     */
    @Test
    public void testGetAllCategories() throws Exception {
        System.out.println("getAllCategories");
        CategoryDAO instance = new CategoryDAO();
        List<Category> expResult = null;
        List<Category> result = instance.getAllCategories();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of createCategory method, of class CategoryDAO.
     */
    @Test
    public void testCreateCategory() throws Exception {
        System.out.println("createCategory");
        String description = "";
        CategoryDAO instance = new CategoryDAO();
        instance.createCategory(description);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getDefaultCategory method, of class CategoryDAO.
     */
    @Test
    public void testGetDefaultCategory() throws Exception {
        System.out.println("getDefaultCategory");
        CategoryDAO instance = new CategoryDAO();
        Category expResult = null;
        Category result = instance.getDefaultCategory();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of fromDescription method, of class CategoryDAO.
     */
    @Test
    public void testFromDescription() throws Exception {
        System.out.println("fromDescription");
        String desc = "";
        CategoryDAO instance = new CategoryDAO();
        Category expResult = null;
        Category result = instance.fromDescription(desc);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
