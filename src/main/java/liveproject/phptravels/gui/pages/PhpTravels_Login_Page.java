package liveproject.phptravels.gui.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

import io.qameta.allure.Step;
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
    @Step("User Sign up")
    public PhpTravels_UserAccount_Page userLogin(String email, String password) {
	driver.findElement(email_field).sendKeys(email);
	driver.findElement(password_field).sendKeys(password, Keys.ENTER);
	return new PhpTravels_UserAccount_Page(driver);
    }

    @Step("User Invalid Login")
    public PhpTravels_Login_Page invalidUserLogin(String email, String password) {
	userLogin(email, password);
	return this;
    }

    @Step("Get the text of the Alert message")
    public String getAlertMessage() {
	String m = driver.findElement(alert_text).getText();
	Logger.logMessage("The Alert message is: " + m);
	return m;
    }

}
