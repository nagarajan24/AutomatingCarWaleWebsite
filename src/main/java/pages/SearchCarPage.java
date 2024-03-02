package pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import basepage.JavaScriptCode;
import basepage.SeleniumDriver;

//Class for searching and getting car details
public class SearchCarPage {

	public String getCarTitle() {
		return SeleniumDriver.getElementText("searchedCarTitle_CSS");
	}

	public void selectCarVariant(String variant) {
		SeleniumDriver.click("variantOption_XPATH");
		List<WebElement> carVariants = SeleniumDriver.findElementsforPresenceOfElements("carVariants_XPATH");
		for (WebElement carVariant : carVariants) {
			if (carVariant.getText().equalsIgnoreCase(variant)) {
				carVariant.click();
				break;
			}
		}
	}

	public void selectCity(String city) {
		SeleniumDriver.click("cityselectBox_XPATH");
		
		SeleniumDriver.click("cityInputBox_XPATH");
		SeleniumDriver.action.sendKeys(Keys.BACK_SPACE).perform();
		SeleniumDriver.type("cityInputBox_XPATH", city);
		for(WebElement cityOption: SeleniumDriver.findElementsforPresenceOfElements("cityOptions_XPATH"))
		{
			//JavaScriptCode.clickOnElement(cityOption);
			cityOption.click();
			break;
		}
		
		SeleniumDriver.click("closeOPtionCityDropdown_XPATH");
	}
	
	public String getSelectedCarVariantPrice()
	{
		return SeleniumDriver.getElementText("selectedCarPrice_XPATH");
	}
}
