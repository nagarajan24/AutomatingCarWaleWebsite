package testcases;

import java.lang.reflect.Method;
import java.util.Hashtable;

import org.testng.Assert;
import org.testng.annotations.Test;

import basepage.SeleniumDriver;
import basetest.BaseTest;
import basetest.Constants;
import pages.NewCarsPage;
import pages.UsedCarPage;
import utilities.DataUtil;

public class BuyUsedCarsTest extends BaseTest{

	// Buy used cars test case
	
	@Test(dataProviderClass = DataUtil.class, dataProvider = "dp")
		public void buyUsedCarsTest(Hashtable<String,String> data)
		{
			//Initial browser set up is done along with checking the test case level runmode
			testSetUp("buyUsedCarsTest");
			
			//To check the test data runmode
			getTestDataRunMode(data.get("runmode"));
			
			//Steps to be performed in HomePage
			SeleniumDriver.getUrl();
			SeleniumDriver.topMenu.clickOnLanguageIcon();
			SeleniumDriver.topMenu.mouseHoverUsedCars();
			UsedCarPage ucp = SeleniumDriver.topMenu.clickOnBuyUsedCars();
			
			//Check if we have navigated to correct car page
			Assert.assertTrue(SeleniumDriver.getDriver().getTitle().contains(Constants.usedCarPageTitle),"Used car page title doesnot match");
			
			//Steps to be performed in used car page
			ucp.filteringCarOptions(data.get("Budget"), data.get("Manufacturer"),data.get("carTransmissionType"));
			//Assert.assertTrue(ucp.getNoOfFilteredCars().substring(0, 3).equalsIgnoreCase(data.get("No of cars")));

		}
	
}
