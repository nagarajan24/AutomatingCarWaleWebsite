package pages;

import java.lang.reflect.Method;
import java.util.List;

import org.openqa.selenium.WebElement;

import basepage.SeleniumDriver;
import utilities.DataUtil;

public class NewCarLaunchesPage {
	//Class for new car launches page business functionalities

	public String getPageTitle()
	{
		return SeleniumDriver.getElementText("newCarLaunchesTitle_CSS");
	}
	
	//Handle Pagination
	public void getNewCarsNameAndPrice()
	{
		String fileName = "NewCarNamesAndPrice.txt";
		List<WebElement> carNames = SeleniumDriver.findElements("newCarNames_CSS");
		List<WebElement> carPrices = SeleniumDriver.findElements("newCarPrice_CSS");
		DataUtil.fileWriterForHeader(fileName, "TITLE - NEW CAR NAMES AND PRICE DETAILS");
		for(int i=0;i<carNames.size();i++)
		{
			DataUtil.fileWriter(fileName, carNames.get(i).getText()+"---"+carPrices.get(i).getText());
			//System.out.println(carNames.get(i).getText()+"---"+carNames.get(i).getText());
		}
		do {
			
			SeleniumDriver.click("newCarLaunchNextBtn_XPATH");
			
			carNames = SeleniumDriver.findElements("newCarNames_CSS");
			carPrices = SeleniumDriver.findElements("newCarPrice_CSS");
			for(int i=0;i<carNames.size();i++)
			{
				DataUtil.fileWriter(fileName, carNames.get(i).getText()+"---"+carNames.get(i).getText());
				//System.out.println(carNames.get(i).getText()+"---"+carNames.get(i).getText());
			}
		}while(!SeleniumDriver.getAttribute("newCarLaunchNextBtn_XPATH", "data-skin").contains("inactive"));
	}
	
	
	
}
