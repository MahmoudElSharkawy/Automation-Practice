package liveproject.phptravels.gui.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import io.qameta.allure.Step;
import utils.ElementActions;
import utils.Logger;

public class PhpTravels_Login_Page {
    private WebDriver driver;

    // Constructor
    public PhpTravels_Login_Page(WebDriver driver) {
	this.driver = driver;
    }

    // Elements
    private By email_field = By.name("username");
    private By password_field = By.name("password");
    private By alert_text = By.xpath("//div[contains(@class, 'alert')]");

    // Methods
    @Step("User Login with Data --> Email: [{email}] and Password: [{password}]")
    public PhpTravels_UserAccount_Page userLogin(String email, String password) {
	ElementActions.type(driver, email_field, email);
	ElementActions.type(driver, password_field, password);
	ElementActions.clickEnterKey(driver, password_field);
	return new PhpTravels_UserAccount_Page(driver);
    }

    @Step("User Invalid Login with Data --> Email: [{email}] and Password: [{password}]")
    public PhpTravels_Login_Page invalidUserLogin(String email, String password) {
	userLogin(email, password);
	return this;
    }

    @Step("Get the text of the Alert message")
    public String getAlertMessage() {
	String alertMessage = ElementActions.getText(driver, alert_text);
	Logger.logMessage("The Alert message is: " + alertMessage);
	return alertMessage;
    }

}
