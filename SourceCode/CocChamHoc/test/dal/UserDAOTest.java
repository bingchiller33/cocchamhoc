/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package dal;

import java.sql.ResultSet;
import java.util.List;
import model.User;
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
public class UserDAOTest {
    
    public UserDAOTest() {
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
     * Test of insertUser method, of class UserDAO.
     */
    @Test
    public void testInsertUser() {
        System.out.println("insertUser");
        User t = null;
        UserDAO instance = new UserDAO();
        int expResult = 0;
        int result = instance.insertUser(t);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        
    }

    /**
     * Test of usernameCheck method, of class UserDAO.
     */
    @Test
    public void testUsernameCheck() {
        System.out.println("usernameCheck");
        String email = "a";
        UserDAO instance = new UserDAO();
        boolean expResult = true;
        boolean result = instance.usernameCheck(email);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        
    }

    /**
     * Test of checkUser method, of class UserDAO.
     */
    @Test
    public void testCheckUser() {
        System.out.println("checkUser");
        String email = "";
        String passWord = "";
        UserDAO instance = new UserDAO();
        List<User> expResult = null;
        List<User> result = instance.checkUser(email, passWord);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        
    }

    /**
     * Test of checkAdmin method, of class UserDAO.
     */
    @Test
    public void testCheckAdmin() {
        System.out.println("checkAdmin");
        String email = "";
        String passWord = "";
        UserDAO instance = new UserDAO();
        boolean expResult = false;
        boolean result = instance.checkAdmin(email, passWord);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        
    }

    /**
     * Test of fromResultSet method, of class UserDAO.
     */
    @Test
    public void testFromResultSet() throws Exception {
        System.out.println("fromResultSet");
        ResultSet rs = null;
        UserDAO instance = new UserDAO();
        User expResult = null;
        User result = instance.fromResultSet(rs);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        
    }

    /**
     * Test of getUser method, of class UserDAO.
     */
    @Test
    public void testGetUser() throws Exception {
        System.out.println("getUser");
        String email = "";
        String pass = "";
        UserDAO instance = new UserDAO();
        User expResult = null;
        User result = instance.getUser(email, pass);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        
    }
    
}
