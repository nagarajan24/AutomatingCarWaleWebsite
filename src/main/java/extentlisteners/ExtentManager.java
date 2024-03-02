package extentlisteners;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentManager {
	
	private static ExtentReports extent;
	
	//Configuring Extent Report
	public static ExtentReports extentReportConfig(String filePath)
	{
		ExtentSparkReporter htmlReporter = new ExtentSparkReporter(filePath);
		htmlReporter.config().setEncoding("utf-8");
		htmlReporter.config().setDocumentTitle("Automating CarWale Website - Extent Report Document");
		htmlReporter.config().setReportName("Extent Report");
		htmlReporter.config().setTheme(Theme.STANDARD);
		
		extent = new ExtentReports();
		extent.attachReporter(htmlReporter);
		extent.setSystemInfo("Tester", "Nagarajan Prabhakar");
		extent.setSystemInfo("OS", "Windows 11");
		
		return extent;
		
	}

}
