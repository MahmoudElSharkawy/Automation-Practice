package phptravels.gui.pages;

import static org.testng.Assert.assertEquals;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;

import io.qameta.allure.Step;
import utils.BrowserActions;
import utils.ElementActions;
import utils.ExtentReport;

public class SigninPage {
    private WebDriver driver;
    private String loginPageUrl = System.getProperty("phptravels.baseuri")
	    + "login";

    // Elements Locators
    private By email_textField = By.name("email");
    private By password_textField = By.name("password");
    private By alertMessage_text = By.xpath("//div[@class='message']//div[contains(@class,'alert alert-') and not(contains(@class,'d-none'))]");
//    private By successMessage_text = By.xpath("//div[@class='alert alert-success signup']");

    // Constructor
    public SigninPage(WebDriver driver) {
	this.driver = driver;
    }

    //////////////////////////////////////////////////////////////////////////
    //////////////////////////////// Actions ////////////////////////////////

    public void navigateToSigninPage() {
	BrowserActions.navigateToUrl(driver, loginPageUrl);
    }

    @Step("User Login with Data --> Email: [{email}] and Password: [{password}]")
    public void userLogin(String email, String password) {
	ExtentReport.info(MarkupHelper.createLabel("User Login", ExtentColor.BLUE));
	ElementActions.type(driver, email_textField, email);
	ElementActions.type(driver, password_textField, password);
	ElementActions.clickKeyboardKey(driver, password_textField, Keys.ENTER);
    }

    //////////////////////////////////////////////////////////////////////////
    ////////////////////////////// Validations //////////////////////////////
    @Step("Assert on Form Message; Expected Message: [{expectedMessage}]")
    public void assertOnFormMessage(String expectedMessage) {
	assertEquals(ElementActions.getText(driver, alertMessage_text), expectedMessage);
    }


}