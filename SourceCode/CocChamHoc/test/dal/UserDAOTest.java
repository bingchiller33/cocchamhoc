/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package dal;

import java.sql.Date;
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
 * @author Phuoc
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
     * Test of searchUsers method, of class UserDAO.
     */
    @Test
    public void testSearchUsers() throws Exception {
        System.out.println("searchUsers");
        String name = "";
        int role = 0;
        int page = 0;
        int pageSize = 0;
        UserDAO instance = new UserDAO();
        List<User> expResult = null;
        List<User> result = instance.searchUsers(name, role, page, pageSize);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of searchUserCount method, of class UserDAO.
     */
    @Test
    public void testSearchUserCount() throws Exception {
        System.out.println("searchUserCount");
        String name = "";
        int role = 0;
        UserDAO instance = new UserDAO();
        int expResult = 0;
        int result = instance.searchUserCount(name, role);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getUserById method, of class UserDAO.
     */
    @Test
    public void testGetUserById() throws Exception {
        System.out.println("getUserById");
        int id = 0;
        UserDAO instance = new UserDAO();
        User expResult = null;
        User result = instance.getUserById(id);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
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
        fail("The test case is a prototype.");
    }

    /**
     * Test of usernameCheck method, of class UserDAO.
     */
    @Test
    public void testUsernameCheck() {
        System.out.println("usernameCheck");
        String email = "";
        UserDAO instance = new UserDAO();
        boolean expResult = false;
        boolean result = instance.usernameCheck(email);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getPassword method, of class UserDAO.
     */
    @Test
    public void testGetPassword() {
        System.out.println("getPassword");
        String email = "";
        UserDAO instance = new UserDAO();
        String expResult = "";
        String result = instance.getPassword(email);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of checkUser method, of class UserDAO.
     */
    @Test
    public void testCheckUser() {
        System.out.println("checkUser");
        String email = "admin@gmail.com";
        String passWord = "6a5aeb1ea832832a9969a562357994ba";
        UserDAO instance = new UserDAO();
        List<User> expResult = null;
        List<User> result = instance.checkUser(email, passWord);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
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
        fail("The test case is a prototype.");
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
        fail("The test case is a prototype.");
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
        fail("The test case is a prototype.");
    }

    /**
     * Test of updateUserRestriction method, of class UserDAO.
     */
    @Test
    public void testUpdateUserRestriction() throws Exception {
        System.out.println("updateUserRestriction");
        int id = 0;
        Date until = null;
        String reason = "";
        UserDAO instance = new UserDAO();
        instance.updateUserRestriction(id, until, reason);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of updateUserRole method, of class UserDAO.
     */
    @Test
    public void testUpdateUserRole() throws Exception {
        System.out.println("updateUserRole");
        int id = 0;
        int newRole = 0;
        UserDAO instance = new UserDAO();
        instance.updateUserRole(id, newRole);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of updateUserProfile method, of class UserDAO.
     */
    @Test
    public void testUpdateUserProfile() throws Exception {
        System.out.println("updateUserProfile");
        int id = 0;
        String fullName = "";
        String email = "";
        Date dob = null;
        int gender = 0;
        String phoneNumber = "";
        UserDAO instance = new UserDAO();
        instance.updateUserProfile(id, fullName, email, dob, gender, phoneNumber);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
