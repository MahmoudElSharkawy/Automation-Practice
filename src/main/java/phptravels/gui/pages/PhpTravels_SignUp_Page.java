package phptravels.gui.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;

import io.qameta.allure.Step;
import utilities.ElementActions;
import utilities.ExtentReport;

public class PhpTravels_SignUp_Page {
    private WebDriver driver;

    // Elements
    private By firstname_field = By.name("firstname");
    private By lastname_field = By.name("lastname");
    private By phone_field = By.name("phone");
    private By email_field = By.name("email");
    private By password_field = By.name("password");
    private By confirmpassword_field = By.name("confirmpassword");
//    private By signup_button = By.xpath("//button[contains(text() ,'Sign Up')]");
    private By alert_text = By.xpath("//div[contains(@class, 'alert')]");

    // Constructor
    public PhpTravels_SignUp_Page(WebDriver driver) {
	this.driver = driver;
    }

    //////////////////////////////////////////////////////////////////
    //////////////////////////// Actions ////////////////////////////

    @Step("User Sign up with Data --> First Name: [{firstName}], Last Name: [{lastName}], Mobile Number: [{mobileNumber}], Email: [{email}] and Password: [{password}]")
    public PhpTravels_UserAccount_Page userSignUp(String firstName, String lastName, String mobileNumber, String email,
	    String password) {
	ExtentReport.info(MarkupHelper.createLabel("User Sign up", ExtentColor.BLUE));
	enterFirstNameField(firstName);
	enterLastNameField(lastName);
	enterMobileNumberField(mobileNumber);
	enterEmailField(email);
	enterPasswordFields(password);
	ElementActions.clickKeyboardKey(driver, confirmpassword_field, Keys.ENTER);
	return new PhpTravels_UserAccount_Page(driver);
    }

    public PhpTravels_SignUp_Page invalidUserSignUp(String firstName, String lastName, String phone, String email,
	    String password) {
	userSignUp(firstName, lastName, phone, email, password);
	return this;
    }

    @Step("Enter First Name --> [{firstName}]")
    public PhpTravels_SignUp_Page enterFirstNameField(String firstName) {
	ElementActions.type(driver, firstname_field, firstName);
	return this;
    }

    @Step("Enter Last Name --> [{lastName}]")
    public PhpTravels_SignUp_Page enterLastNameField(String lastName) {
	ElementActions.type(driver, lastname_field, lastName);
	return this;
    }

    @Step("Enter Mobile Number --> [{mobileNumber}]")
    public PhpTravels_SignUp_Page enterMobileNumberField(String mobileNumber) {
	ElementActions.type(driver, phone_field, mobileNumber);
	return this;
    }

    @Step("Enter Email --> [{email}]")
    public PhpTravels_SignUp_Page enterEmailField(String email) {
	ElementActions.type(driver, email_field, email);
	return this;
    }

    @Step("Enter Password and Confirm Password --> [{password}]")
    public PhpTravels_SignUp_Page enterPasswordFields(String password) {
	ElementActions.type(driver, password_field, password);
	ElementActions.type(driver, confirmpassword_field, password);
	return this;
    }

    @Step("Get the text of the Alert message")
    public String getAlertMessage() {
	return ElementActions.getText(driver, alert_text);
    }

}
