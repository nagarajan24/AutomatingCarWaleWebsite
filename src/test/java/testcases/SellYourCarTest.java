package testcases;

import java.util.Hashtable;

import org.testng.Assert;
import org.testng.annotations.Test;

import basepage.SeleniumDriver;
import basetest.BaseTest;
import basetest.Constants;
import pages.SellYourCarPage;
import utilities.DataUtil;

public class SellYourCarTest extends BaseTest{
	
	//Sell your car test case
	
	@Test(dataProviderClass = DataUtil.class, dataProvider = "dp")
	public void sellYourCarTest(Hashtable<String,String> data)
	{
		//Initial browser set up is done along with checking the test case level runmode
		testSetUp("sellYourCarTest");
		
		//To check the test data runmode
		getTestDataRunMode(data.get("runmode"));
		
		//Steps to be performed in HomePage
		SeleniumDriver.getUrl();
		SeleniumDriver.topMenu.clickOnLanguageIcon();
		SeleniumDriver.topMenu.mouseHoverUsedCars();
		SellYourCarPage sycp = SeleniumDriver.topMenu.getSellYourCarOption();
		
		//Check if we have navigated to correct car page
		Assert.assertTrue(sycp.getPageHeader().contains(Constants.sellYourCarPageTitle));
		
		//Steps to be performed in Sell your car page
		sycp.postYourCarAdd(data.get("city"), data.get("pincode"), data.get("carMakeYear"), data.get("carMakeMonth"), data.get("carMake"), data.get("carModel"), data.get("carFuelType"), data.get("carVersion"), data.get("carColor"), data.get("alternativeFuel"), data.get("owner"), data.get("kms"), data.get("expectedSellingPrice"));
	
	}

}
