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
    private By hotels_link = By.xpath("//div[@class = 'menu-horizontal-wrapper-02']//a[contains(text(),'Hotels')]");
    private By destination_field = By.id("s2id_autogen16");
    private By checkin_field = By.id("checkin");
    private By checkout_field = By.id("checkout");
    private By hotels_search_button = By.xpath("//form[@name='HOTELS']//button[contains(text(),'Search')]");

    private By hotles_result(String hotelsResult) {
	return By.xpath("//div[contains(text(),'" + hotelsResult + "')]");
    }

    // Methods
    @Step("Navigate to Login page")
    public PhpTravels_Login_Page navigateToLoginPage() {
	ElementActions.click(driver, myaccount_button);
	ElementActions.click(driver, login_button);
	return new PhpTravels_Login_Page(driver);
    }

    @Step("Navigate to Sign Up page")
    public PhpTravels_SignUp_Page navigateToSignUpPage() {
	ElementActions.click(driver, myaccount_button);
	ElementActions.click(driver, signup_button);
	return new PhpTravels_SignUp_Page(driver);
    }

    // Hotels Search Methods
    @Step("Search for Hotels with Data --> DESTINATION: [{hotelsOrCityname}], CHECK IN date: [{checkinDate}] and CHECK OUT date: [{checkoutDate}]")
    public PhpTravels_Home_Page hotelsSearch(String hotelsOrCityname, String checkinDate, String checkoutDate) {
	clickOnHotelsLink();
	selectDestination(hotelsOrCityname);
	enterCheckinDate(checkinDate);
	enterCheckoutDate(checkoutDate);
	clickOnseachButton();
	return this;
    }

    @Step("Click on the HOTELS to Display the Hotels Fields")
    public PhpTravels_Home_Page clickOnHotelsLink() {
	ElementActions.click(driver, hotels_link);
	return this;
    }

    @Step("Select DESTINATION --> [{hotelsOrCityname}]")
    public PhpTravels_Home_Page selectDestination(String hotelsOrCityname) {
	ElementActions.click(driver, destination_field);
//	ElementActions.type(driver, destination_field, hotelsOrCityname);
	ElementActions.click(driver, hotles_result(hotelsOrCityname));
	return this;
    }

    @Step("Enter CHECK IN Date --> [{checkinDate}]")
    public PhpTravels_Home_Page enterCheckinDate(String checkinDate) {
	ElementActions.type(driver, checkin_field, checkinDate);
	return this;
    }

    @Step("Enter CHECK OUT Date --> [{checkoutDate}]")
    public PhpTravels_Home_Page enterCheckoutDate(String checkoutDate) {
	ElementActions.type(driver, checkout_field, checkoutDate);
	return this;
    }

    @Step("Click on SEARCH button")
    public PhpTravels_Home_Page clickOnseachButton() {
	ElementActions.click(driver, hotels_search_button);
	return this;
    }

}
