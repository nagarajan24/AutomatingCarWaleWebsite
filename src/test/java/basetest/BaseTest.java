package basetest;

import java.lang.reflect.Method;

import org.testng.SkipException;
import org.testng.annotations.AfterTest;

import basepage.SeleniumDriver;
import extentlisteners.ExtentListener;

public class BaseTest {

	// All initialization before starting a test case is done in the test set up method
	
	public void testSetUp(String testName) {
		SeleniumDriver.setUp(testName);
	}

	// Browser is closed after completing a test case in the test tear down method
	@AfterTest
	public void testTearDown() {
		SeleniumDriver.tearDown();
	}

	// To check the runmode for each test data in a test case
	public void getTestDataRunMode(String runmode) {
		if (runmode.equalsIgnoreCase("N")) {
			ExtentListener.test.skip("Testcase skipped as runmode is set as No");
			SeleniumDriver.log.info("Testcase skipped as runmode is set as No");
			throw new SkipException("Testcase skipped as runmode is set as No");

		}
	}


}
