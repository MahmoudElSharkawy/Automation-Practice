package phptravels.gui.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;

import io.qameta.allure.Step;
import utilities.ElementActions;
import utilities.ExtentReport;

public class PhpTravels_BoatsBook_Page {
    private WebDriver driver;

    // Constructor
    public PhpTravels_BoatsBook_Page(WebDriver driver) {
	this.driver = driver;
    }

    // Elements Locators
    private By gotit_cookie_button = By.xpath("//button[@aria-label='dismiss cookie message']");
    private By confirmThisBooking_button = By.xpath("//button[contains(.,'CONFIRM THIS BOOKING')]");
    private By signin_tab = By.id("signintab");
    private By email_textField = By.name("username");
    private By password_textField = By.id("password");

    //////////////////////////////////////////////////////////////////
    //////////////////////////// Actions ////////////////////////////

    public PhpTravels_BoatsBook_Page dismissCookieBar() {
	ElementActions.click(driver, gotit_cookie_button);
	return this;
    }

    @Step("Sign In with data --> Email: [{email}] and Password: [{password}]")
    public PhpTravels_BoatsBook_Page signIn(String email, String password) {
	ExtentReport.info(MarkupHelper.createLabel("Sign In", ExtentColor.BLUE));
	clickOnSignInTab();
	enterEmailField(email);
	enterPasswordField(password);
	return this;
    }

    @Step("click on Sign In tab")
    public PhpTravels_BoatsBook_Page clickOnSignInTab() {
	ElementActions.click(driver, signin_tab);
	return this;
    }

    @Step("Enter Email --> [{email}]")
    public PhpTravels_BoatsBook_Page enterEmailField(String email) {
	ElementActions.type(driver, email_textField, email);
	return this;
    }

    @Step("Enter Password --> [{password}]")
    public PhpTravels_BoatsBook_Page enterPasswordField(String password) {
	ElementActions.type(driver, password_textField, password);
	return this;
    }

    @Step("click on CONFIRM THIS BOOKING button")
    public PhpTravels_Invoice_Page clickOnConfirmThisBooking() {
	ElementActions.click(driver, confirmThisBooking_button);
	return new PhpTravels_Invoice_Page(driver);
    }

}
