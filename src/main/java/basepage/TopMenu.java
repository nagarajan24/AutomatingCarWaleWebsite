package basepage;

import org.openqa.selenium.Keys;

import pages.NewCarLaunchesPage;
import pages.NewCarsPage;
import pages.SearchCarPage;
import pages.SellYourCarPage;
import pages.UsedCarPage;


public class TopMenu {
	
	//Top menu options remains the same across all pages. Hence we are defining the business logics
	//for these menu options here
	
	//New Cars Test Case
	
	//Find new car test
	public TopMenu mouseHoverNewCars()
	{
		SeleniumDriver.mouseHover("newCars_XPATH");
		return this;
	}
	
	public NewCarsPage clickOnFindNewCars()
	{
		SeleniumDriver.click("findNewCars_XPATH");
		return new NewCarsPage();
	}
	
	public TopMenu clickOnLanguageIcon()
	{
		SeleniumDriver.click("languageIcon_CSS");
		return this;
	}
	
	public SearchCarPage seachForCars(String carName)
	{
		
		SeleniumDriver.type("searchForCars_XPATH", carName);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		SeleniumDriver.action.sendKeys(Keys.ENTER).perform();
		
		return new SearchCarPage();
	
	}
	
	//New Launches Test Case
	
	public NewCarLaunchesPage clickOnNewLaunches()
	{
		SeleniumDriver.click("newLaunches_XPATH");
		return new NewCarLaunchesPage();
	}
	
	//Used Cars Test Case
	public TopMenu mouseHoverUsedCars()
	{
		SeleniumDriver.mouseHover("usedCars_CSS");
		return this;
	}
	
	public UsedCarPage clickOnBuyUsedCars()
	{
		SeleniumDriver.click("buyUsedCars_CSS");
		return new UsedCarPage();
	}
	
	//Sell Your Car Test Case
	public SellYourCarPage getSellYourCarOption()
	{
		SeleniumDriver.click("sellYourCars_XPATH");
		return new SellYourCarPage();
	}
	
}
