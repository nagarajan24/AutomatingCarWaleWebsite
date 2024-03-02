package rough;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Sample {

	public static void main(String[] args) {
		
		WebDriver driver = new ChromeDriver();
		driver.get("https://www.google.com/");
		//String text ="Selenium";
		JavascriptExecutor js = (JavascriptExecutor)driver;
		System.out.println(js.executeScript("return arguments[0].innerText;",driver.findElement(By.xpath("//*[@id=\"gb\"]/div/div[1]/div/div[1]/a"))));
		
	}
	
	

}
