package phptravels.gui.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;

import io.qameta.allure.Step;
import utilities.ElementActions;
import utilities.ExtentReport;

public class PhpTravels_Login_Page {
    private WebDriver driver;

    // Elements Locators
    private By email_field = By.name("username");
    private By password_field = By.name("password");
    private By alert_text = By.xpath("//div[contains(@class, 'alert')]");

    // Constructor
    public PhpTravels_Login_Page(WebDriver driver) {
	this.driver = driver;
    }

    //////////////////////////////////////////////////////////////////
    //////////////////////////// Actions ////////////////////////////

    @Step("User Login with Data --> Email: [{email}] and Password: [{password}]")
    public PhpTravels_UserAccount_Page userLogin(String email, String password) {
	ExtentReport.info(MarkupHelper.createLabel("User Login", ExtentColor.BLUE));
	enterEmail(email);
	enterPassword(password);
	ElementActions.clickKeyboardKey(driver, password_field, Keys.ENTER);
	return new PhpTravels_UserAccount_Page(driver);
    }

    public PhpTravels_Login_Page invalidUserLogin(String email, String password) {
	userLogin(email, password);
	return this;
    }

    @Step("Enter Email --> [{email}]")
    public PhpTravels_UserAccount_Page enterEmail(String email) {
	ElementActions.type(driver, email_field, email);
	return new PhpTravels_UserAccount_Page(driver);
    }

    @Step("Enter password --> [{password}]")
    public PhpTravels_UserAccount_Page enterPassword(String password) {
	ElementActions.type(driver, password_field, password);
	return new PhpTravels_UserAccount_Page(driver);
    }

    @Step("Get the text of the Alert message")
    public String getAlertMessage() {
	return ElementActions.getText(driver, alert_text);
    }

}
