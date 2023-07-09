/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package dal;

import java.sql.ResultSet;
import java.util.List;
import model.Rate;
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
public class RateDAOTest {
    
    public RateDAOTest() {
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
     * Test of rateResultSet method, of class RateDAO.
     */
    @Test
    public void testRateResultSet() throws Exception {
        System.out.println("rateResultSet");
        ResultSet rs = null;
        RateDAO instance = new RateDAO();
        Rate expResult = null;
        Rate result = instance.rateResultSet(rs);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        
    }

    /**
     * Test of insertRatings method, of class RateDAO.
     */
    @Test
    public void testInsertRatings() {
        System.out.println("insertRatings");
        int cId = 0;
        int uId = 0;
        int rateNo = 0;
        String review = "";
        RateDAO instance = new RateDAO();
        instance.insertRatings(cId, uId, rateNo, review);
        // TODO review the generated test code and remove the default call to fail.
        
    }

    /**
     * Test of getQuantityReviewCorse method, of class RateDAO.
     */
    @Test
    public void testGetQuantityReviewCorse() {
        System.out.println("getQuantityReviewCorse");
        int cid = -1;
        RateDAO instance = new RateDAO();
        int expResult = 0;
        int result = instance.getQuantityReviewCorse(cid);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        
    }

    /**
     * Test of getUserRateNo method, of class RateDAO.
     */
    @Test
    public void testGetUserRateNo() {
        System.out.println("getUserRateNo");
        int cId = 0;
        int uId = 0;
        RateDAO instance = new RateDAO();
        int expResult = 0;
        int result = instance.getUserRateNo(cId, uId);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        
    }

    /**
     * Test of getSumRateNo method, of class RateDAO.
     */
    @Test
    public void testGetSumRateNo() {
        System.out.println("getSumRateNo");
        int cId = 0;
        RateDAO instance = new RateDAO();
        int expResult = 0;
        int result = instance.getSumRateNo(cId);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        
    }

    /**
     * Test of getQuantityRateNo method, of class RateDAO.
     */
    @Test
    public void testGetQuantityRateNo() {
        System.out.println("getQuantityRateNo");
        int cId = 0;
        RateDAO instance = new RateDAO();
        int expResult = 0;
        int result = instance.getQuantityRateNo(cId);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        
    }

    /**
     * Test of rateAvg method, of class RateDAO.
     */
    @Test
    public void testRateAvg() {
        System.out.println("rateAvg");
        int count = 1;
        int sum = 3;
        RateDAO instance = new RateDAO();
        int expResult = 3;
        int result = instance.rateAvg(count, sum);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        
    }

    /**
     * Test of rate method, of class RateDAO.
     */
    @Test
    public void testRate() {
        System.out.println("rate");
        int count = 0;
        int sum = 0;
        RateDAO instance = new RateDAO();
        double expResult = 0.0;
        double result = instance.rate(count, sum);
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        
    }

    /**
     * Test of updateRating method, of class RateDAO.
     */
    @Test
    public void testUpdateRating() {
        System.out.println("updateRating");
        int cId = 0;
        int uId = 0;
        int rateNo = 0;
        RateDAO instance = new RateDAO();
        instance.updateRating(cId, uId, rateNo);
        // TODO review the generated test code and remove the default call to fail.
        
    }

    /**
     * Test of updateReview method, of class RateDAO.
     */
    @Test
    public void testUpdateReview() {
        System.out.println("updateReview");
        int cId = 0;
        int uId = 0;
        String review = "";
        RateDAO instance = new RateDAO();
        instance.updateReview(cId, uId, review);
        // TODO review the generated test code and remove the default call to fail.
        
    }

    /**
     * Test of updateTime method, of class RateDAO.
     */
    @Test
    public void testUpdateTime() {
        System.out.println("updateTime");
        int cId = 0;
        int uId = 0;
        RateDAO instance = new RateDAO();
        instance.updateTime(cId, uId);
        // TODO review the generated test code and remove the default call to fail.
        
    }

    /**
     * Test of getTitleCourse method, of class RateDAO.
     */
    @Test
    public void testGetTitleCourse() {
        System.out.println("getTitleCourse");
        int cId = 0;
        RateDAO instance = new RateDAO();
        String expResult = null;
        String result = instance.getTitleCourse(cId);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        
    }

    /**
     * Test of getReviewRate method, of class RateDAO.
     */
    @Test
    public void testGetReviewRate() {
        System.out.println("getReviewRate");
        int cid = 35;
        RateDAO instance = new RateDAO();
        List<Rate> expResult = null;
        List<Rate> result = instance.getReviewRate(cid);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        
    }

    /**
     * Test of getCourseId method, of class RateDAO.
     */
    @Test
    public void testGetCourseId() {
        System.out.println("getCourseId");
        int uId = 0;
        int cId = 0;
        RateDAO instance = new RateDAO();
        List<Integer> expResult = null;
        List<Integer> result = instance.getCourseId(uId, cId);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        
    }
    
}
