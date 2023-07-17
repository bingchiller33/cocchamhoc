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
        fail("The test case is a prototype.");
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
        fail("The test case is a prototype.");
    }

    /**
     * Test of getQuantityReviewCorse method, of class RateDAO.
     */
    @Test
    public void testGetQuantityReviewCorse() {
        System.out.println("getQuantityReviewCorse");
        int cid = 2;
        RateDAO instance = new RateDAO();
        int expResult = 8;
        int result = instance.getQuantityReviewCorse(cid);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the def
    }

    /**
     * Test of getUserRateNo method, of class RateDAO.
     */
    @Test
    public void testGetUserRateNo() {
        System.out.println("getUserRateNo");
        int cId = 15;
        int uId = 3;
        RateDAO instance = new RateDAO();
        int expResult = 0;
        int result = instance.getUserRateNo(cId, uId);
        assertEquals(expResult, result);
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
        fail("The test case is a prototype.");
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
        fail("The test case is a prototype.");
    }

    /**
     * Test of rateAvg method, of class RateDAO.
     */
    @Test
    public void testRateAvg() {
        System.out.println("rateAvg");
        int count = 0;
        int sum = 0;
        RateDAO instance = new RateDAO();
        int expResult = 0;
        int result = instance.rateAvg(count, sum);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
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
        fail("The test case is a prototype.");
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
        fail("The test case is a prototype.");
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
        fail("The test case is a prototype.");
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
        fail("The test case is a prototype.");
    }

    /**
     * Test of getTitleCourse method, of class RateDAO.
     */
    @Test
    public void testGetTitleCourse() {
        System.out.println("getTitleCourse");
        int cId = 0;
        RateDAO instance = new RateDAO();
        String expResult = "";
        String result = instance.getTitleCourse(cId);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getReviewRate method, of class RateDAO.
     */
    @Test
    public void testGetReviewRate() {
        System.out.println("getReviewRate");
        int cid = 0;
        int rateNo = 0;
        int page = 0;
        int pageSize = 0;
        RateDAO instance = new RateDAO();
        List<Rate> expResult = null;
        List<Rate> result = instance.getReviewRate(cid, rateNo, page, pageSize);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
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
        fail("The test case is a prototype.");
    }

    /**
     * Test of updateReport method, of class RateDAO.
     */
    @Test
    public void testUpdateReport() {
        System.out.println("updateReport");
        int cId = 0;
        int rId = 0;
        RateDAO instance = new RateDAO();
        instance.updateReport(cId, rId);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of deleteRate method, of class RateDAO.
     */
    @Test
    public void testDeleteRate() {
        System.out.println("deleteRate");
        int cId = 0;
        int uId = 0;
        RateDAO instance = new RateDAO();
        instance.deleteRate(cId, uId);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getQuantity5 method, of class RateDAO.
     */
    @Test
    public void testGetQuantity5() {
        System.out.println("getQuantity5");
        int cId = 0;
        RateDAO instance = new RateDAO();
        int expResult = 0;
        int result = instance.getQuantity5(cId);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getQuantity4 method, of class RateDAO.
     */
    @Test
    public void testGetQuantity4() {
        System.out.println("getQuantity4");
        int cId = 0;
        RateDAO instance = new RateDAO();
        int expResult = 0;
        int result = instance.getQuantity4(cId);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getQuantity3 method, of class RateDAO.
     */
    @Test
    public void testGetQuantity3() {
        System.out.println("getQuantity3");
        int cId = 0;
        RateDAO instance = new RateDAO();
        int expResult = 0;
        int result = instance.getQuantity3(cId);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getQuantity2 method, of class RateDAO.
     */
    @Test
    public void testGetQuantity2() {
        System.out.println("getQuantity2");
        int cId = 0;
        RateDAO instance = new RateDAO();
        int expResult = 0;
        int result = instance.getQuantity2(cId);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getQuantity1 method, of class RateDAO.
     */
    @Test
    public void testGetQuantity1() {
        System.out.println("getQuantity1");
        int cId = 0;
        RateDAO instance = new RateDAO();
        int expResult = 0;
        int result = instance.getQuantity1(cId);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getQuantityAll method, of class RateDAO.
     */
    @Test
    public void testGetQuantityAll() {
        System.out.println("getQuantityAll");
        int cId = 0;
        RateDAO instance = new RateDAO();
        int expResult = 0;
        int result = instance.getQuantityAll(cId);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getSizeFilter method, of class RateDAO.
     */
    @Test
    public void testGetSizeFilter() {
        System.out.println("getSizeFilter");
        int cid = 0;
        int rateNo = 0;
        RateDAO instance = new RateDAO();
        int expResult = 0;
        int result = instance.getSizeFilter(cid, rateNo);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
