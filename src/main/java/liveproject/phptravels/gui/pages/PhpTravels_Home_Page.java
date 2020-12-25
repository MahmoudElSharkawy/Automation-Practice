package liveproject.phptravels.gui.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import io.qameta.allure.Step;
import utils.ElementActions;

public class PhpTravels_Home_Page {
    private WebDriver driver;

    // Constructor
    public PhpTravels_Home_Page(WebDriver driver) {
	this.driver = driver;
    }

    // Elements
    private By myaccount_button = By.linkText("MY ACCOUNT");
    private By signup_button = By.linkText("Sign Up");
    private By login_button = By.linkText("Login");
    private By hotels_button = By.xpath("//div[@class = 'menu-horizontal-wrapper-02']//a[contains(text(),'Hotels')]");

    // Methods
    @Step("Navigating to Login page")
    public PhpTravels_Login_Page navigateToLoginPage() {
	ElementActions.click(driver, myaccount_button);
	ElementActions.click(driver, login_button);
	return new PhpTravels_Login_Page(driver);
    }

    @Step("Navigating to Sign Up page")
    public PhpTravels_SignUp_Page navigateToSignUpPage() {
	ElementActions.click(driver, myaccount_button);
	ElementActions.click(driver, signup_button);
	return new PhpTravels_SignUp_Page(driver);
    }

    @Step("Search for Hotels")
    public PhpTravels_Home_Page hotelsSearch() {
	ElementActions.click(driver, hotels_button);
	return this;
    }

}
