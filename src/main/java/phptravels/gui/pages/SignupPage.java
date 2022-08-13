package phptravels.gui.pages;

import static org.testng.Assert.assertEquals;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;

import io.qameta.allure.Step;
import utils.BrowserActions;
import utils.ElementActions;
import utils.ElementActions.SelectType;
import utils.ExtentReport;

public class SignupPage {
    private WebDriver driver;
    private String signUpPageUrl = System.getProperty("phptravels.baseuri")
	    + "signup";

    // Elements Locators
    private By firstName_textField = By.name("first_name");
    private By lastName_textField = By.name("last_name");
    private By phone_textField = By.name("phone");
    private By email_textField = By.name("email");
    private By password_textField = By.name("password");
    private By accountType_select = By.id("account_type");
    private By signup_button = By.xpath("//button[@type='submit']");
    private By alertMessage_text = By.xpath("//div[@class='message']//div[contains(@class,'alert alert-') and not(contains(@class,'d-none'))]");
//    private By formErrorMessage_text = By.xpath("//div[@class='alert alert-danger']");

    // Constructor
    public SignupPage(WebDriver driver) {
	this.driver = driver;
    }

    //////////////////////////////////////////////////////////////////////////
    //////////////////////////////// Actions ////////////////////////////////

    public void navigateToSignupPage() {
	BrowserActions.navigateToUrl(driver, signUpPageUrl);
    }

    @Step("User Sign up with Data --> First Name: [{firstName}], Last Name: [{lastName}], Mobile Number: [{phoneNumber}], Email: [{email}] and Password: [{password}]")
    public void userSignUp(String firstName, String lastName, String phoneNumber, String email, String password, String accountType) {
	ExtentReport.info(MarkupHelper.createLabel("User Sign up", ExtentColor.BLUE));
	ElementActions.type(driver, firstName_textField, firstName);
	ElementActions.type(driver, lastName_textField, lastName);
	ElementActions.type(driver, phone_textField, phoneNumber);
	ElementActions.type(driver, email_textField, email);
	ElementActions.type(driver, password_textField, password);
	ElementActions.select(driver, accountType_select, SelectType.TEXT, accountType);
//	ElementActions.clickKeyboardKey(driver, password_textField, Keys.ENTER);
	ElementActions.click(driver, signup_button);
    }

    
    //////////////////////////////////////////////////////////////////////////
    ////////////////////////////// Validations //////////////////////////////
    @Step("Assert on Form Message; Expected Message: [{expectedMessage}]")
    public void assertOnFormMessage(String expectedMessage) {
	assertEquals(ElementActions.getText(driver, alertMessage_text), expectedMessage);
    }

    
}