package phptravels.gui.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

import io.qameta.allure.Step;
import utils.BrowserActions;
import utils.ElementActions;
import utils.PropertiesReader;

public class AdminLoginPage {
    private WebDriver driver;
    private String url = "https://phptravels.net/api/admin";

    // Elements Locators
    private By email_input = By.name("email");
    private By password_input = By.name("password");
    
    // Constructor
    public AdminLoginPage(WebDriver driver) {
	this.driver = driver;
    }

    //////////////////////////////////////////////////////////////////
    //////////////////////////// Actions ////////////////////////////

    @Step("Navigate to Admin Login page")
    public void navigateToAdminLoginPage() {
	BrowserActions.navigateToUrl(driver, url);
    }

    public void loginAsDefaultAdminUser() {
	new ElementActions(driver)
	.type(email_input, PropertiesReader.getProperty("phptravels.properties", "defaultAdminUserEmail"))
	.type(password_input, PropertiesReader.getProperty("phptravels.properties", "defaultAdminUserPassword"))
	.clickKeyboardKey(password_input, Keys.ENTER);
    }

}
