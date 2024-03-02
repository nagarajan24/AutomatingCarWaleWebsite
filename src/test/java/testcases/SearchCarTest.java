package testcases;

import java.util.Hashtable;

import org.testng.Assert;
import org.testng.annotations.Test;

import basepage.SeleniumDriver;
import basetest.BaseTest;
import pages.SearchCarPage;
import utilities.DataUtil;

public class SearchCarTest extends BaseTest{
	
	//Search car test case
	
	@Test(dataProviderClass = DataUtil.class, dataProvider = "dp")
	public void searchCarTest(Hashtable<String,String> data)
	{
		//Initial browser set up is done along with checking the test case level runmode
		testSetUp("searchCarTest");
		
		//To check the test data runmode
		getTestDataRunMode(data.get("runmode"));
		
		//Steps to be performed in HomePage
		SeleniumDriver.getUrl();
		SeleniumDriver.topMenu.clickOnLanguageIcon();
		SearchCarPage scp = SeleniumDriver.topMenu.seachForCars(data.get("Car Name"));
		
		
		//Check if we have navigated to the correct car page which we have searched
		Assert.assertTrue(scp.getCarTitle().contains(data.get("Car Name")),"Have not navigated to the right car page");
		
		//To get the price of a car for particular variant and location and to compare it with our test data
		scp.selectCarVariant(data.get("Car Variant"));
		scp.selectCity(data.get("City"));
		Assert.assertTrue(scp.getSelectedCarVariantPrice().equalsIgnoreCase(data.get("Price")),"Mismatch in car price");
		
	}

}
