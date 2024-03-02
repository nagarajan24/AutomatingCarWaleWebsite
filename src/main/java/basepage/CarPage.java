package basepage;

import java.util.List;


import org.openqa.selenium.WebElement;

import utilities.DataUtil;

//CarPages has common locators. Hence this Class CarPage is created for executing all the functionality of all car pages
//with common locators

public class CarPage {
	
	
	public void getAllCarTitles()
	{
		String fileName = "UsedCarTitles.txt";
		List<WebElement> list = SeleniumDriver.findElements("carTitles_XPATH");
		DataUtil.fileWriterForHeader(fileName, "TITLE - ALL USED CAR TITLES");
		for(WebElement l:list)
		{
			DataUtil.fileWriter(fileName, l.getText());
		}
	}
	
	public String getCarPageTitle()
	{
		return SeleniumDriver.getElementText("carPageTitle_CSS");
	}

}
