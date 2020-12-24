package liveproject.phptravels.gui.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import io.qameta.allure.Step;

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
    @Step("Navigate to Login page")
    public PhpTravels_Login_Page navigateToLoginPage() {
	driver.findElement(myaccount_button).click();
	driver.findElement(login_button).click();
	return new PhpTravels_Login_Page(driver);
    }

    @Step("Navigate to Sign Up page")
    public PhpTravels_SignUp_Page navigateToSignUpPage() {
	driver.findElement(myaccount_button).click();
	driver.findElement(signup_button).click();
	return new PhpTravels_SignUp_Page(driver);
    }

    @Step("Search for Hotels")
    public PhpTravels_Home_Page hotelsSearch() {
	driver.findElement(hotels_button).click();
	return this;
    }

}
