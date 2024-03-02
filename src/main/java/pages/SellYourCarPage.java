package pages;

import org.openqa.selenium.WebElement;


import basepage.SeleniumDriver;

public class SellYourCarPage {
	
	public String getPageHeader()
	{
		return SeleniumDriver.getElementText("sellYourCarPageHeader_CSS");
	}
	
	//Params used in the below order
	// city,  pincode,  carMakeYear,  carMakeMonth,  carMake,  carModel,  carFuelType,  carVersion,
	// carColor, alternativeFuel,  owner,  kms,  expectedSellingPrice
	public void postYourCarAdd(String... params)
	{
		//City
		SeleniumDriver.clear("citytxtBox_CSS");
		SeleniumDriver.type("citytxtBox_CSS", params[0]);
		for(WebElement ourCity:SeleniumDriver.findElements("cityList_CSS")){
			if(ourCity.getAttribute("cityname").contains(params[0]))
			{
				SeleniumDriver.action.click(ourCity).perform();
			}
		}
		//Pincode
		SeleniumDriver.click("pinCode_CSS");
		SeleniumDriver.type("pinCodetxtBox_XPATH", params[1]);
		
		//next button
		SeleniumDriver.click("nextBtn_CSS");
		
		//Car Make Year
		clickOnElement("carMakeYearList_CSS",params[2]);

		//Car Make Month
		clickOnElement("carMakeMonthList_CSS",params[3]);
		
		//Car Make
		clickOnElement("carMakeList_CSS", params[4]);
		
		//Car Model
		clickOnElement("carModelList_CSS", params[5]);
		
		//Car Fuel
		clickOnElement("carFuelList_CSS", params[6]);
		
		//Car Version
		clickOnElement("carVersionList_CSS", params[7]);
		
		//Car Color
		clickOnElement("carColorList_CSS", params[8]);
		
		//Alternate Fuel
		clickOnElement("alternateFuelList_CSS", params[9]);
		
		//Owner
		clickOnElement("ownerList_CSS", params[10]);
		
		//Kms Driver
		SeleniumDriver.type("kmsDriver_CSS", params[11]);
		//Expected Selling Price
		SeleniumDriver.type("expectedSellingPrice_CSS", params[12]);
		
	}
	
	//Click on the respective element from the list of elements extracted from website
	public void clickOnElement(String locator, String value)
	{
		for(WebElement element: SeleniumDriver.findElements(locator))
		{
			if(element.getText().equalsIgnoreCase(value))
			{
				element.click();
			}
		}
	}

}
