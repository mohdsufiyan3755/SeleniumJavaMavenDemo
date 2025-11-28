package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import base.baseTest;
import pages.loginPage;

public class loginTest extends baseTest {

	@Test(priority = 1, description = "Verify successful login with valid credentials")
	public void verifySuccessfulLogin() {
		// Initialize Page Object
		loginPage login = new loginPage(driver);
		login.enterUsername("student");
		login.enterPassword("Password123");
		login.clickLoginButton();
		System.out.println("Title of the Webpage is: " + driver.getTitle());
		Assert.assertEquals(driver.getTitle(), "Logged In Successfully | Practice Test Automation");
		

	}

}
