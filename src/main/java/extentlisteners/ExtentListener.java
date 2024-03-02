package extentlisteners;

import java.util.Arrays;
import java.util.Date;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.SkipException;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;

import basepage.SeleniumDriver;
import utilities.DataUtil;
import utilities.ScreenshotUtil;

//Listener for generating Extent report and also for checking runmode, attaching screenshot to reports
public class ExtentListener implements ITestListener {

	Date d = new Date();
	String extentReport = "Extent_" + d.toString().replace(":", "_").replace(" ", "_") + ".html";
	private ExtentReports extent = ExtentManager
			.extentReportConfig(".//target//surefire-reports//extentreports//" + extentReport);
	public static ExtentTest test;

	public void onTestStart(ITestResult result) {
		// Creating test case section for extent report
		String mtdName = result.getMethod().getMethodName();
		Object[] param = result.getParameters();
		if (param.length != 0) {
			test = extent.createTest(
					result.getTestClass().getClass() + "--@TestCase--" + mtdName + "--@Parameters--" + param[0]);
		} else {
			test = extent.createTest(result.getTestClass().getClass() + "--@TestCase--" + mtdName);
		}

	}

	public void onTestSuccess(ITestResult result) {

		// Creating a Label for the test case(with color code) and indicating its passed
		String testCaseResult = "Test case: " + result.getMethod().getMethodName() + " is passed";
		Markup m = MarkupHelper.createLabel(testCaseResult, ExtentColor.GREEN);
		test.pass(m);

	}

	public void onTestFailure(ITestResult result) {
		// Generating exception message in extent report
		String expMsg = Arrays.toString(result.getThrowable().getStackTrace());
		test.fail(expMsg);

		// Capturing Screenshot
		ScreenshotUtil.capturePageScreenshot();
		String scrFileName = ScreenshotUtil.scrFileName;

		// Attaching the screenshot in Reportng report
		System.setProperty("org.uncommons.reportng.escape-output", "false");
		Reporter.log("<a href=" + scrFileName + " target=\"_blank\">Failed test case screenshot</a>");
		Reporter.log("<br>");
		Reporter.log("<a href=" + scrFileName + " target=\"_blank\"><img src=" + scrFileName
				+ " width=200 height=200></img></a>");

		// Attaching the screenshot in Extent report
		String extentReportScr = "<b>Failed test case screenshot</b><br>";
		test.fail(extentReportScr, MediaEntityBuilder.createScreenCaptureFromPath(scrFileName).build());

		// Creating a Label for the test case(with color code) and indicating its failed
		String testCaseResult = "Test case: " + result.getMethod().getMethodName() + " is failed";
		Markup m = MarkupHelper.createLabel(testCaseResult, ExtentColor.RED);
		test.fail(m);
	}

	public void onTestSkipped(ITestResult result) {

		// Creating a Label for the test case(with color code) and indicating its passed
		String testCaseResult = "Test case: " + result.getMethod().getMethodName() + " is skipped";
		Markup m = MarkupHelper.createLabel(testCaseResult, ExtentColor.YELLOW);
		test.skip(m);

	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {

	}

	public void onStart(ITestContext context) {

	}

	public void onFinish(ITestContext context) {

		if (extent != null) {
			extent.flush();
		}
	}

}
