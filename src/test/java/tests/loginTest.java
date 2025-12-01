package tests;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import base.baseTest;
import pages.loginPage;
import utils.ExcelUtils;
import utils.ExtentReportManager;
import utils.log;

public class loginTest extends baseTest {

	@DataProvider(name = "LoginData")
	public Object[][] getLoginData() throws IOException {

		String filePath = System.getProperty("user.dir") + "/test-data/TestData.xlsx";
		ExcelUtils.loadExcel(filePath, "Sheet1");
		int rowCount = ExcelUtils.getRowCount();
		Object[][] data = new Object[rowCount - 1][2];

		for (int i = 1; i < rowCount; i++) {

			data[i - 1][0] = ExcelUtils.getCellData(i, 0); // Username
			data[i - 1][1] = ExcelUtils.getCellData(i, 1); // Password
		}
		ExcelUtils.closeExcel();
		return data;
	}

	@DataProvider(name = "LoginData2")
	public Object[][] getData() {

		return new Object[][] { { "user1", "pass1" }, { "user2", "pass2" }, { "user3", "pass3" } };
	}

//	@Test(dataProvider = "LoginData2", priority = 1, description = "Verify successful login with valid credentials")
	@Test
//	@Parameters({"username","password"})
	public void verifySuccessfulLogin(String username, String password) {
		// Initialize Page Object
		log.info("**********Starting Login test**************");
		test = ExtentReportManager.createTest("Login Test - " + username);
		loginPage login = new loginPage(driver);
		log.info("**********Entering login credentials**************");
		test.info("Adding Credentails");
		login.enterUsername("student");
		login.enterPassword("Password123");
//		login.enterUsername(username);
//		login.enterPassword(password);
		test.info("Clicking on Login button");
		login.clickLoginButton();
		log.info("**********Validating Page Title**************");
		System.out.println("Title of the Webpage is: " + driver.getTitle());
		Assert.assertEquals(driver.getTitle(), "Logged In Successfully | Practice Test Automation");
		test.pass("Login Successful");

	}

}
