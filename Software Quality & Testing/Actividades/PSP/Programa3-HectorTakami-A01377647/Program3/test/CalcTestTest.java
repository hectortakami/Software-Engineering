/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Hector
 */
public class CalcTestTest {
    Program3 testCalc;
    
    public CalcTestTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() throws Exception{
		testCalc = new Program3();
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void testSomeMethod() {
        fail("The test case is a prototype.");
    }
    
    public void test(){
		try {
			setUp();
		} catch (Exception e) {
			e.printStackTrace();
		}
              
		assertTrue(Math.abs(testCalc.beta0 + 22.55) < 0.01);
		assertTrue(Math.abs(testCalc.beta1 - 1.7279) < 0.01);
		assertTrue(Math.abs(testCalc.r - 0.9545) < 0.01);
		assertTrue(Math.abs(testCalc.y - 644.429) < 0.01);
	}
    
}
