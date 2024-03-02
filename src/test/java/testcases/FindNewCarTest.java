package testcases;

import java.util.Hashtable;

import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.Test;

import basepage.SeleniumDriver;
import basetest.BaseTest;
import basetest.Constants;
import pages.NewCarsPage;
import utilities.DataUtil;

public class FindNewCarTest extends BaseTest{
	
	//FindNewCar test case
	NewCarsPage ncp;
	
	@Test(dataProviderClass = DataUtil.class, dataProvider = "dp")
	public void findNewCarTest(Hashtable<String,String> data)
	{
		//Initial browser set up is done along with checking the test case level runmode
		testSetUp("findNewCarTest");
		
		//To check the test data runmode
		getTestDataRunMode(data.get("runmode"));
		
		//Steps to be performed in HomePage
		SeleniumDriver.getUrl();
		SeleniumDriver.topMenu.clickOnLanguageIcon();
		SeleniumDriver.topMenu.mouseHoverNewCars();
		ncp=SeleniumDriver.topMenu.clickOnFindNewCars();
		Assert.assertTrue(SeleniumDriver.getDriver().getTitle().contains(Constants.newCarPageTitle),"New car page title doesnot match");
		
		//Steps to be preformed in New Cars Page
		selectCar(data.get("Car"));
		
		//Check if we have navigated to correct car page
		Assert.assertTrue(SeleniumDriver.car.getCarPageTitle().contains(data.get("Car")),"Navigation to "+data.get("Car")+" page is unsuccessful");
		
		//To get the title of all cars displayed in the respective car page
		SeleniumDriver.car.getAllCarTitles();
	}
	
	
	//Method to select the respective car based on the test data input
	public void selectCar(String car)
	{
		if(car.equalsIgnoreCase("Maruti Suzuki"))
		{
			ncp.clickOnMarutiSuzuki();
		}
		else if(car.equalsIgnoreCase("Tata"))
		{
			ncp.clickOnTata();
		}
		else if(car.equalsIgnoreCase("Toyota"))
		{
			ncp.clickOnToyota();
		}
		else if(car.equalsIgnoreCase("Mahindra"))
		{
			ncp.clickOnMahindra();
		}
		else if(car.equalsIgnoreCase("Hyundai"))
		{
			ncp.clickOnHyundai();
		}
		else if(car.equalsIgnoreCase("Bmw"))
		{
			ncp.clickOnBMW();
		}
		else if(car.equalsIgnoreCase("MercedesBenz"))
		{
			ncp.clickOnMercedesBenz();
		}
		else if(car.equalsIgnoreCase("Kia"))
		{
			ncp.clickOnKia();
		}
		else if(car.equalsIgnoreCase("LandRover"))
		{
			ncp.clickOnLandRover();
		}
		else if(car.equalsIgnoreCase("Audi"))
		{
			ncp.clickOnAudi();
		}
		else if(car.equalsIgnoreCase("MG"))
		{
			ncp.clickOnMG();
		}
		else if(car.equalsIgnoreCase("Porsche"))
		{
			ncp.clickOnPorsche();
		}
		else
		{
			throw new SkipException("Car input provided is not valid");
		}
		
	}

}
