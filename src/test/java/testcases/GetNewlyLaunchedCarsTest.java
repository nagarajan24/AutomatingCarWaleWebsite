package testcases;

import java.util.Hashtable;

import org.testng.Assert;
import org.testng.annotations.Test;

import basepage.SeleniumDriver;
import basetest.BaseTest;
import basetest.Constants;
import pages.NewCarLaunchesPage;
import utilities.DataUtil;

public class GetNewlyLaunchedCarsTest extends BaseTest {

	// get newly launched cars test case
	
	@Test
	public void getNewlyLaunchedCarsTest() {

		//Initial browser set up is done along with checking the test case level runmode
		testSetUp("getNewlyLaunchedCarsTest");
		
		// Steps to be performed in HomePage
		SeleniumDriver.getUrl();
		SeleniumDriver.topMenu.clickOnLanguageIcon();
		SeleniumDriver.topMenu.mouseHoverNewCars();
		NewCarLaunchesPage nclp = SeleniumDriver.topMenu.clickOnNewLaunches();

		// Check if we have navigated to correct car page
		Assert.assertTrue(nclp.getPageTitle().equalsIgnoreCase(Constants.newCarLaunchesPageTitle),
				"New car launches page title doesnot match");

		// Steps to be preformed in New Car Launches Page
		nclp.getNewCarsNameAndPrice();

	}

}
