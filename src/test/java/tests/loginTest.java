package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import base.baseTest;
import pages.loginPage;
import utils.ExtentReportManager;
import utils.log;

public class loginTest extends baseTest {

	@Test(priority = 1, description = "Verify successful login with valid credentials")
	public void verifySuccessfulLogin() {
		// Initialize Page Object
		log.info("**********Starting Login test**************");
		test = ExtentReportManager.createTest("Login Test - ");
		loginPage login = new loginPage(driver);
		log.info("**********Entering login credentials**************");
		test.info("Adding Credentails");
		login.enterUsername("student");
		login.enterPassword("Password123");
		test.info("Clicking on Login button");
		login.clickLoginButton();
		log.info("**********Validating Page Title**************");
		System.out.println("Title of the Webpage is: " + driver.getTitle());
		Assert.assertEquals(driver.getTitle(), "Logged In Successfully | Practice Test Automation");
		test.pass("Login Successful");

	}

}
