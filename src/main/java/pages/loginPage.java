package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class loginPage {
	
	public WebDriver driver;

    // Locators (using PageFactory @FindBy)
    @FindBy(id = "username")
    WebElement usernameField;

    @FindBy(name = "password")
    WebElement passwordField;

    @FindBy(xpath = "//button[@class='btn']")
    WebElement loginButton;
    
    // Constructor
    public loginPage(WebDriver driver) {
        this.driver = driver;
        // Initialize elements
        PageFactory.initElements(driver, this); 
    }

    // Page Actions
    public void enterUsername(String username) {
    	usernameField.clear();
        usernameField.sendKeys(username);
    }

    public void enterPassword(String password) {
    	passwordField.clear();
        passwordField.sendKeys(password);
    }

    public void clickLoginButton() {
        loginButton.click();
    }
    
 }
