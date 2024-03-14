package pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import basepage.JavaScriptCode;
import basepage.SeleniumDriver;

public class UsedCarPage{

	//Params passed - budget, manufacturer, startCarAge, endCarAge, carTransmissionType
	public void filteringCarOptions(String... params)
	{
		//Close the location window
		SeleniumDriver.click("closeOPtionCityDropdown_XPATH");
		
		//Budget Filter
		List<WebElement> carBudgets = SeleniumDriver.findElements("budgetOptions_XPATH");
		for(WebElement carBudget: carBudgets)
		{
			if(carBudget.getText().equalsIgnoreCase(params[0]))
			{
				carBudget.click();
			}
		}
		//Select Manufactures
		SeleniumDriver.type("manufacturer_XPATH", params[1]);
		JavaScriptCode.clickOnElement(SeleniumDriver.findElement("manufacturerCheckBox_XPATH"));
		
		//Select Car Age
		//Start Car Age
//		SeleniumDriver.click("carAgeDropDown_XPATH");
//		SeleniumDriver.click("carStartAge_XPATH");
//		SeleniumDriver.action.sendKeys(Keys.BACK_SPACE).perform();
//		SeleniumDriver.type("carStartAge_XPATH",params[2]);
//		
//		//End Car Age
//		SeleniumDriver.click("carEndAge_XPATH");
//		SeleniumDriver.action.sendKeys(Keys.BACK_SPACE).perform();
//		SeleniumDriver.type("carEndAge_XPATH", params[3]);
//		SeleniumDriver.click("carStartAge_XPATH");
//		SeleniumDriver.click("carEndAge_XPATH");
//		SeleniumDriver.click("carStartAge_XPATH");
//		SeleniumDriver.click("carEndAge_XPATH");
//		
		
		//Select Transmission
		SeleniumDriver.click("transmission_XPATH");
	
		
		//SeleniumDriver.click("transmission_XPATH");
		int count=0;
		for(WebElement tranmissionType: SeleniumDriver.findElementsforPresenceOfElements("transmissionTypes_XPATH"))
		{
			
			if(tranmissionType.getText().equalsIgnoreCase(params[3]))
			{
				
				SeleniumDriver.click("transmission_XPATH");
				JavaScriptCode.scrollToMid();
				JavaScriptCode.clickOnElement(SeleniumDriver.findElementsforPresenceOfElements("transmissionTypesCheckBoxes_XPATH").get(count));
				//SeleniumDriver.findElementsforPresenceOfElements("transmissionTypesCheckBoxes_XPATH").get(count).click();				

				break;
			}
			count+=1;
		}
			
	}
	
	public String getNoOfFilteredCars()
	{
		return SeleniumDriver.getElementText("filteredCarHeader_XPATH");
	}
	
}
