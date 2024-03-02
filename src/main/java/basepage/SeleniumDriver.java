package basepage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.List;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.SkipException;

import extentlisteners.ExtentListener;
import utilities.ExcelReader;

//Single ton design pattern

public class SeleniumDriver {

	// Initializing all the variables needed for our code
	private static SeleniumDriver seleniumDriver;
	private static WebDriver driver;
	public static Properties or = new Properties();
	private static Properties config = new Properties();
	private FileInputStream file;
	public static WebDriverWait webDriverWait;
	private static WebElement mouseHoverElement;
	public static TopMenu topMenu;
	public static ExcelReader excel = new ExcelReader(".//src//main//resources//excel//testdata.xlsx");
	public static CarPage car;
	public static Logger log = Logger.getLogger(SeleniumDriver.class);
	public static Actions action;

	// Loading OR and Config property files. Also writing the code to select
	// appropriate browser to be launched
	private SeleniumDriver(String testName) {
		
		if(testCaseRunMode(testName))
		{
			
			PropertyConfigurator.configure(".//src//main//resources//properties//log4j.properties");

			try {
				file = new FileInputStream(".//src//main//resources//properties//OR.properties");
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			try {
				or.load(file);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			log.info("OR property file loaded successfully");

			try {
				file = new FileInputStream(".//src//main//resources//properties//config.properties");
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			try {
				config.load(file);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			log.info("Config property file loaded successfully");
			
			//Getting the browser data from jenkins
			if (System.getenv("browser").equalsIgnoreCase("chrome")) {
				ChromeOptions options = new ChromeOptions();
				options.addArguments("--disable-notifications");

				driver = new ChromeDriver(options);
				log.info("Chrome browser launched");
			} else if (System.getenv("browser").equalsIgnoreCase("firefox")) {

				driver = new FirefoxDriver();
				log.info("Firefox browser launched");
			} else if (System.getenv("browser").equalsIgnoreCase("edge")) {

				driver = new EdgeDriver();
				log.info("Edge browser launched");
			} else {
				// If none of the values matches,chrome browser will launch by default
				ChromeOptions options = new ChromeOptions();
				options.addArguments("--disble-notifications");

				driver = new ChromeDriver(options);
				log.info("Chrome browser launched");
			}
			
			// Maximizing the browser
			driver.manage().window().maximize();
			// Implementing implicit wait
			driver.manage().timeouts()
					.implicitlyWait(Duration.ofSeconds(Integer.parseInt(config.getProperty("implicit_wait"))));
			// Initializing explicit wait
			webDriverWait = new WebDriverWait(driver,
					Duration.ofSeconds(Integer.parseInt(config.getProperty("explicit_wait"))));

			// Initializing actions class
			action = new Actions(driver);

			// Encapsulating TopMenu and CarPage class
			topMenu = new TopMenu();
			car = new CarPage();
		}
		else
		{
			throw new SkipException("Skipping the test case: "+testName+" as runmode is set as No");
		}
		

	}

	// Method for getting the driver
	public static WebDriver getDriver() {
		return driver;
	}

	// Method to hit the website url
	public static void getUrl() {
		driver.get(config.getProperty("url"));
		log.info("URL : " + config.getProperty("url") + " launched");
		ExtentListener.test.info("URL : " + config.getProperty("url") + " launched");
	}

	// Method to get object ot SeleniumDriver
	public static void setUp(String testName) {
		if (seleniumDriver == null) {
			seleniumDriver = new SeleniumDriver(testName);
		}
	}

	// Method to close the driver and set Selenium driver back to null
	public static void tearDown() {
		if (driver != null) {
			driver.quit();
		}
		seleniumDriver = null;

	}
	
	
	//To check the runmode of each test case
	public static boolean testCaseRunMode(String testCaseName) {
		String sheetName = "testcases";
		int rowCount = SeleniumDriver.excel.getRowCount(sheetName);

		for (int row = 1; row < rowCount; row++) {
			String testcase = SeleniumDriver.excel.getCellData(sheetName, "testcaseName", row);
			
			if (testcase.equalsIgnoreCase(testCaseName)) {
				String runMode = SeleniumDriver.excel.getCellData(sheetName, "runmode", row);
				if (runMode.equalsIgnoreCase("N")) {
					return false;
				}
			}
		}
		return true;
	}

	
	// Keywords for various actions/operations
	public static void click(String locator) {
		if (locator.endsWith("_XPATH")) {
			webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(or.getProperty(locator))))
					.click();
		} else if (locator.endsWith("_CSS")) {
			webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(or.getProperty(locator))))
					.click();
		} else if (locator.endsWith("_LINKTEXT")) {
			webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText(or.getProperty(locator))))
					.click();
		} else if (locator.endsWith("_ID")) {
			webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id(or.getProperty(locator)))).click();
		} else if (locator.endsWith("_TAGNAME")) {
			webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.tagName(or.getProperty(locator))))
					.click();
		} else if (locator.endsWith("_CLASSNAME")) {
			webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.className(or.getProperty(locator))))
					.click();
		} else if (locator.endsWith("_PARTIALLINKTEXT")) {
			webDriverWait
					.until(ExpectedConditions.visibilityOfElementLocated(By.partialLinkText(or.getProperty(locator))))
					.click();
		} else {
			throw new SkipException("Incorrect locator used: " + locator);
		}

		log.info("Element: " + locator + " is clicked successfully");
		ExtentListener.test.info("Element: " + locator + " is clicked successfully");
	}

	public static void clear(String locator) {
		if (locator.endsWith("_XPATH")) {
			webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(or.getProperty(locator))))
					.clear();
		} else if (locator.endsWith("_CSS")) {
			webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(or.getProperty(locator))))
					.clear();
		} else if (locator.endsWith("_LINKTEXT")) {
			webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText(or.getProperty(locator))))
					.clear();
		} else if (locator.endsWith("_ID")) {
			webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id(or.getProperty(locator)))).clear();
		} else if (locator.endsWith("_TAGNAME")) {
			webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.tagName(or.getProperty(locator))))
					.clear();
		} else if (locator.endsWith("_CLASSNAME")) {
			webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.className(or.getProperty(locator))))
					.clear();
		} else if (locator.endsWith("_PARTIALLINKTEXT")) {
			webDriverWait
					.until(ExpectedConditions.visibilityOfElementLocated(By.partialLinkText(or.getProperty(locator))))
					.clear();
		} else {
			throw new SkipException("Incorrect locator used: " + locator);
		}

		log.info("Data in element: " + locator + " is cleared successfully");
		ExtentListener.test.info("Data in element: " + locator + " is cleared successfully");
	}

	public static void type(String locator, String value) {
		if (locator.endsWith("_XPATH")) {
			webDriverWait.until(ExpectedConditions.elementToBeClickable(By.xpath(or.getProperty(locator))))
					.sendKeys(value);
		} else if (locator.endsWith("_CSS")) {
			webDriverWait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(or.getProperty(locator))))
					.sendKeys(value);
		} else if (locator.endsWith("_LINKTEXT")) {
			webDriverWait.until(ExpectedConditions.elementToBeClickable(By.linkText(or.getProperty(locator))))
					.sendKeys(value);
		} else if (locator.endsWith("_ID")) {
			webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id(or.getProperty(locator))))
					.sendKeys(value);
		} else if (locator.endsWith("_TAGNAME")) {
			webDriverWait.until(ExpectedConditions.elementToBeClickable(By.tagName(or.getProperty(locator))))
					.sendKeys(value);
		} else if (locator.endsWith("_CLASSNAME")) {
			webDriverWait.until(ExpectedConditions.elementToBeClickable(By.className(or.getProperty(locator))))
					.sendKeys(value);
		} else if (locator.endsWith("_PARTIALLINKTEXT")) {
			webDriverWait.until(ExpectedConditions.elementToBeClickable(By.partialLinkText(or.getProperty(locator))))
					.sendKeys(value);
		} else {
			throw new SkipException("Incorrect locator used : " + locator);
		}

		log.info("Have typed " + value + " in element " + locator + " successfully");
		ExtentListener.test.info("Have typed " + value + " in element " + locator + " successfully");
	}

	public static void mouseHover(String locator) {
		if (locator.endsWith("_XPATH")) {
			mouseHoverElement = driver.findElement(By.xpath(or.getProperty(locator)));
		} else if (locator.endsWith("_CSS")) {
			mouseHoverElement = driver.findElement(By.cssSelector(or.getProperty(locator)));
		} else if (locator.endsWith("_LINKTEXT")) {
			mouseHoverElement = driver.findElement(By.linkText(or.getProperty(locator)));
		} else if (locator.endsWith("_ID")) {
			mouseHoverElement = driver.findElement(By.id(or.getProperty(locator)));
		} else if (locator.endsWith("_TAGNAME")) {
			mouseHoverElement = driver.findElement(By.tagName(or.getProperty(locator)));
		} else if (locator.endsWith("_CLASSNAME")) {
			mouseHoverElement = driver.findElement(By.className(or.getProperty(locator)));
		} else if (locator.endsWith("_PARTIALLINKTEXT")) {
			mouseHoverElement = driver.findElement(By.partialLinkText(or.getProperty(locator)));
		} else {
			throw new SkipException("Incorrect locator used: " + locator);
		}

		action.moveToElement(mouseHoverElement).perform();

		log.info("Have mousehovered on element " + locator + " successfully");
		ExtentListener.test.info("Have mousehovered on element " + locator + " successfully");
	}

	public static List<WebElement> findElements(String locator) {
		List<WebElement> elements = null;
		if (locator.endsWith("_XPATH")) {
			elements = webDriverWait.until(ExpectedConditions
					.refreshed(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(or.getProperty(locator)))));
		} else if (locator.endsWith("_CSS")) {
			elements = webDriverWait.until(ExpectedConditions.refreshed(
					ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector(or.getProperty(locator)))));
		} else if (locator.endsWith("_LINKTEXT")) {
			elements = webDriverWait.until(ExpectedConditions.refreshed(
					ExpectedConditions.visibilityOfAllElementsLocatedBy(By.linkText(or.getProperty(locator)))));
		} else if (locator.endsWith("_ID")) {
			elements = webDriverWait.until(ExpectedConditions
					.refreshed(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.id(or.getProperty(locator)))));
		} else if (locator.endsWith("_TAGNAME")) {
			elements = webDriverWait.until(ExpectedConditions.refreshed(
					ExpectedConditions.visibilityOfAllElementsLocatedBy(By.tagName(or.getProperty(locator)))));
		} else if (locator.endsWith("_CLASSNAME")) {
			elements = webDriverWait.until(ExpectedConditions.refreshed(
					ExpectedConditions.visibilityOfAllElementsLocatedBy(By.className(or.getProperty(locator)))));
		} else if (locator.endsWith("_PARTIALLINKTEXT")) {
			elements = webDriverWait.until(ExpectedConditions.refreshed(
					ExpectedConditions.visibilityOfAllElementsLocatedBy(By.partialLinkText(or.getProperty(locator)))));
		} else {
			throw new SkipException("Incorrect locator used: " + locator);
		}

		log.info("List of elements for " + locator + " has been returned successfully");
		ExtentListener.test.info("List of elements for " + locator + " has been returned successfully");
		return elements;

	}

	public static WebElement findElement(String locator) {
		WebElement element;
		if (locator.endsWith("_XPATH")) {
			element = driver.findElement(By.xpath(or.getProperty(locator)));
		} else if (locator.endsWith("_CSS")) {
			element = driver.findElement(By.cssSelector(or.getProperty(locator)));
		} else if (locator.endsWith("_LINKTEXT")) {
			element = driver.findElement(By.linkText(or.getProperty(locator)));
		} else if (locator.endsWith("_ID")) {
			element = driver.findElement(By.id(or.getProperty(locator)));
		} else if (locator.endsWith("_TAGNAME")) {
			element = driver.findElement(By.tagName(or.getProperty(locator)));
		} else if (locator.endsWith("_CLASSNAME")) {
			element = driver.findElement(By.className(or.getProperty(locator)));
		} else if (locator.endsWith("_PARTIALLINKTEXT")) {
			element = driver.findElement(By.partialLinkText(or.getProperty(locator)));
		} else {
			throw new SkipException("Incorrect locator used: " + locator);
		}
		log.info("Returning the element using locator " + locator);
		ExtentListener.test.info("Returning the element using locator " + locator);
		return element;
	}

	public static List<WebElement> findElementsforPresenceOfElements(String locator) {
		List<WebElement> elements = null;
		if (locator.endsWith("_XPATH")) {
			elements = webDriverWait.until(ExpectedConditions
					.refreshed(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(or.getProperty(locator)))));
		} else if (locator.endsWith("_CSS")) {
			elements = webDriverWait.until(ExpectedConditions.refreshed(
					ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector(or.getProperty(locator)))));
		} else if (locator.endsWith("_LINKTEXT")) {
			elements = webDriverWait.until(ExpectedConditions.refreshed(
					ExpectedConditions.presenceOfAllElementsLocatedBy(By.linkText(or.getProperty(locator)))));
		} else if (locator.endsWith("_ID")) {
			elements = webDriverWait.until(ExpectedConditions
					.refreshed(ExpectedConditions.presenceOfAllElementsLocatedBy(By.id(or.getProperty(locator)))));
		} else if (locator.endsWith("_TAGNAME")) {
			elements = webDriverWait.until(ExpectedConditions
					.refreshed(ExpectedConditions.presenceOfAllElementsLocatedBy(By.tagName(or.getProperty(locator)))));
		} else if (locator.endsWith("_CLASSNAME")) {
			elements = webDriverWait.until(ExpectedConditions.refreshed(
					ExpectedConditions.presenceOfAllElementsLocatedBy(By.className(or.getProperty(locator)))));
		} else if (locator.endsWith("_PARTIALLINKTEXT")) {
			elements = webDriverWait.until(ExpectedConditions.refreshed(
					ExpectedConditions.presenceOfAllElementsLocatedBy(By.partialLinkText(or.getProperty(locator)))));
		} else {
			throw new SkipException("Incorrect locator used: " + locator);
		}

		log.info("List of elements for " + locator + " has been returned successfully");
		ExtentListener.test.info("List of elements for " + locator + " has been returned successfully");
		return elements;

	}

	public static String getElementText(String locator) {
		String elementText = "";
		if (locator.endsWith("_XPATH")) {
			elementText = driver.findElement(By.xpath(or.getProperty(locator))).getText();
		} else if (locator.endsWith("_CSS")) {
			elementText = driver.findElement(By.cssSelector(or.getProperty(locator))).getText();
		} else if (locator.endsWith("_LINKTEXT")) {
			elementText = driver.findElement(By.linkText(or.getProperty(locator))).getText();
		} else if (locator.endsWith("_ID")) {
			elementText = driver.findElement(By.id(or.getProperty(locator))).getText();
		} else if (locator.endsWith("_TAGNAME")) {
			elementText = driver.findElement(By.tagName(or.getProperty(locator))).getText();
		} else if (locator.endsWith("_CLASSNAME")) {
			elementText = driver.findElement(By.className(or.getProperty(locator))).getText();
		} else if (locator.endsWith("_PARTIALLINKTEXT")) {
			elementText = driver.findElement(By.partialLinkText(or.getProperty(locator))).getText();
		} else {
			throw new SkipException("Incorrect locator used: " + locator);
		}
		log.info("Getting the element text for " + locator);
		ExtentListener.test.info("Getting the element text for " + locator);
		return elementText;
	}

	public static String getAttribute(String locator, String attributeName) {

		String attribute = "";
		if (locator.endsWith("_XPATH")) {
			attribute = webDriverWait
					.until(ExpectedConditions.refreshed(
							ExpectedConditions.visibilityOfElementLocated(By.xpath(or.getProperty(locator)))))
					.getAttribute(attributeName);
		} else if (locator.endsWith("_CSS")) {
			attribute = webDriverWait
					.until(ExpectedConditions.refreshed(
							ExpectedConditions.visibilityOfElementLocated(By.cssSelector(or.getProperty(locator)))))
					.getAttribute(attributeName);
		} else if (locator.endsWith("_LINKTEXT")) {
			attribute = webDriverWait
					.until(ExpectedConditions.refreshed(
							ExpectedConditions.visibilityOfElementLocated(By.linkText(or.getProperty(locator)))))
					.getAttribute(attributeName);
		} else if (locator.endsWith("_ID")) {
			attribute = webDriverWait
					.until(ExpectedConditions
							.refreshed(ExpectedConditions.visibilityOfElementLocated(By.id(or.getProperty(locator)))))
					.getAttribute(attributeName);
		} else if (locator.endsWith("_TAGNAME")) {
			attribute = webDriverWait
					.until(ExpectedConditions.refreshed(
							ExpectedConditions.visibilityOfElementLocated(By.tagName(or.getProperty(locator)))))
					.getAttribute(attributeName);
		} else if (locator.endsWith("_CLASSNAME")) {
			attribute = webDriverWait
					.until(ExpectedConditions.refreshed(
							ExpectedConditions.visibilityOfElementLocated(By.className(or.getProperty(locator)))))
					.getAttribute(attributeName);
		} else if (locator.endsWith("_PARTIALLINKTEXT")) {
			attribute = webDriverWait
					.until(ExpectedConditions.refreshed(
							ExpectedConditions.visibilityOfElementLocated(By.partialLinkText(or.getProperty(locator)))))
					.getAttribute(attributeName);
		}

		log.info("Attribute in Element: " + locator + " returned. Returned value is- " + attribute);
		ExtentListener.test.info("Attribute in Element: " + locator + " returned. Returned value is- " + attribute);
		return attribute;
	}

}
