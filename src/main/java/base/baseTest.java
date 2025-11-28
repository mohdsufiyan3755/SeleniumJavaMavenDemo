package base;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import io.github.bonigarcia.wdm.WebDriverManager;
import utils.log;

public class baseTest {
	
	protected WebDriver driver;
	
	@BeforeMethod
	public void setUp() {
		
		log.info("**********Starting webdriver**************");
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		
		// General setup
        driver.manage().window().maximize();
        //driver.manage().timeouts().implicitlyWait(3000);  // Implicit Wait
        
        log.info("**********Launching URL**************");
		driver.get("https://practicetestautomation.com/practice-test-login/");
	}
	
	@AfterMethod
	public void tearDown() {
		if (driver != null) {
			log.info("**********Closing the browser**************");
            driver.quit();
		}
	}

}
