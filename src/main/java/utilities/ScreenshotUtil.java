package utilities;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;

import basepage.SeleniumDriver;

public class ScreenshotUtil {
	
	static Date d = new Date();
	
	public static String scrFileName = d.toString().replace(":", "_").replace(" ", "_")+".jpg";
	
	//To capture screenshot of a page
	public static void capturePageScreenshot()
	{
		File file = ((TakesScreenshot)SeleniumDriver.getDriver()).getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyFile(file, new File(".//target//surefire-reports//extentreports//"+scrFileName));
			FileUtils.copyFile(file, new File(".//target//surefire-reports//html//"+scrFileName));

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//To capture screenshot of an element
	public static void captureElementScreenshot(WebElement element)
	{
		File file = element.getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyFile(file, new File(".//target//surefire-reports//"+scrFileName));
			FileUtils.copyFile(file, new File(".//target//surefire-reports//html//"+scrFileName));

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
