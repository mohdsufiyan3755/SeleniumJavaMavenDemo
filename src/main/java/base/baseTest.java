package base;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;

import config.PropertiesFile;
import io.github.bonigarcia.wdm.WebDriverManager;
import utils.EmailUtils;
import utils.ExtentReportManager;
import utils.log;

public class baseTest {

	protected WebDriver driver;
	protected static ExtentReports extent;
	protected ExtentTest test;
	public static String browserName=null;
	public static String baseURL = null;

	@BeforeSuite
	public void setupReport() {
		extent = ExtentReportManager.getReportInstance();
	}

	@AfterSuite
	public void teardownReport() {
		extent.flush();
		 String reportPath = ExtentReportManager.reportPath;
		 EmailUtils.sendTestReport(reportPath);
	}

	@BeforeMethod
	public void setUp() {
		
		PropertiesFile.getProperties();
		if(browserName.equalsIgnoreCase("chrome")) {
			log.info("**********Starting webdriver**************");
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
		}
		else if(browserName.equalsIgnoreCase("firefox")) {
			log.info("**********Starting webdriver**************");
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		}
		else if(browserName.equalsIgnoreCase("edge")) {
			log.info("**********Starting webdriver**************");
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
		}

		// General setup
		driver.manage().window().maximize();
		// driver.manage().timeouts().implicitlyWait(3000); // Implicit Wait

		log.info("**********Launching URL**************");
		driver.get(baseURL);
	}

	@AfterMethod
	public void tearDown(ITestResult result) {

		if (result.getStatus() == ITestResult.FAILURE) {

			String screenshotPath = ExtentReportManager.captureScreenshot(driver, "LoginFailure");
			test.fail("Test Failed.. Check Screenshot",
					MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
		}

		if (driver != null) {
			log.info("**********Closing the browser**************");
			driver.quit();
		}
	}

}
