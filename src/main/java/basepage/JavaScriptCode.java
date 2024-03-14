package basepage;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import extentlisteners.ExtentListener;

public class JavaScriptCode {
	
	
	private static WebDriver driver = SeleniumDriver.getDriver();
	private static JavascriptExecutor js = (JavascriptExecutor)driver;
	
	//Get the URL of the webpage
	public static String getURL()
	{
		String url = (String) js.executeScript("return document.URL;");
		SeleniumDriver.log.info("returning the URL of our website: "+url);
		ExtentListener.test.info("returning the URL of our website: "+url);
		return url;
	}
	
	//To get the webpage title
	public static String getTitle()
	{
		String title = (String) js.executeScript("return document.title;");
		SeleniumDriver.log.info("returning the title of our website: "+title);
		ExtentListener.test.info("returning the title of our website: "+title);
		return title;
	}
	
	//To enter text in an element
	public static void enterText(WebElement element, String text)
	{
		js.executeScript("arguments[0].value='"+text+"'", element);
		SeleniumDriver.log.info("Successfully entered text: "+text+" in element: "+element);
		ExtentListener.test.info("Successfully entered text: "+text+" in element: "+element);		
	}
	
	//To get text from an element
	public static String getText(WebElement element)
	{
		String text = (String)js.executeScript("return arguments[0].value;", element);
		SeleniumDriver.log.info("Value present in element: "+element+" is : "+text);
		ExtentListener.test.info("Value present in element: "+element+" is : "+text);
		return text;
	}
	
	//To get inner test from an element
	public static String getInnerText(WebElement element)
	{
		String text = (String)js.executeScript("return arguments[0].innerText;", element);
		SeleniumDriver.log.info("Innertext present in element: "+element+" is : "+text);
		ExtentListener.test.info("Innertext present in element: "+element+" is : "+text);
		return text;
	}
	
	//Click on an element
	public static void clickOnElement(WebElement element)
	{
		js.executeScript("arguments[0].click();", element);
		SeleniumDriver.log.info("Clicking on element "+element+" using JavaScript");
		ExtentListener.test.info("Clicking on element "+element+" using JavaScript");
	}
	
	//Scroll to bottom of the page
	public static void scrollToBottom()
	{
		js.executeScript("window.scrollTo(0,document.body.scrollHeight)");
		
		SeleniumDriver.log.info("Scrolling to bottom of page");
		ExtentListener.test.info("Scrolling to bottom of page");
	}
	
	//Scroll to top of the page
	public static void scrollToTop()
	{
		js.executeScript("window.scrollBy(0,-document.body.scrollHeight)");

		SeleniumDriver.log.info("Scrolling to top of page");
		ExtentListener.test.info("Scrolling to top of page");
	}
	
	//Scroll to middle of the page
	public static void scrollToMid()
	{
		js.executeScript("window.scrollTo(0,document.body.scrollHeight/2)");
		
		SeleniumDriver.log.info("Scrolling to middle of page");
		ExtentListener.test.info("Scrolling to middle of page");
	}
	
	//Scroll till a particular element is visible in the page
	public static void scroll(WebElement element)
	{
		js.executeScript("arguments[0].scrollIntoView(true);", element);
		SeleniumDriver.log.info("Scrolling untill element: "+element+" is visible");
		ExtentListener.test.info("Scrolling untill element: "+element+" is visible");
		
	}
	
	//Normal scroll
	public static void scroll(String x,String y)
	{
		js.executeScript("window.scrollBy("+x+","+y+")");
		SeleniumDriver.log.info("Scrolling through the page");
		ExtentListener.test.info("Scrolling through the page");
		
	}
	
	//Highlighting an element
	public static void highlightElement(WebElement element)
	{
		js.executeScript("argument[0].setAttribute('style','border: 2px solid red ; background: blue;');", element);
		SeleniumDriver.log.info("Highlighting element "+element);
		ExtentListener.test.info("Highlighting element "+element);
		
	}
	
	//Get elements inside shadow dom
	public static WebElement getShadowRoot(WebElement element)
	{
		WebElement shadowDomElemenet = (WebElement)js.executeScript("return arguments[0].shadowRoot;", element);
		SeleniumDriver.log.info("Getting shadow dom element "+shadowDomElemenet);
		ExtentListener.test.info("Getting the shadow dom element "+shadowDomElemenet);
		return shadowDomElemenet;
		
	}

}
