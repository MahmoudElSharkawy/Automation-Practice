package liveproject.phptravels.gui.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;

import io.qameta.allure.Step;
import utils.ElementActions;
import utils.ExtentReport;

public class PhpTravels_Home_Page {
    private WebDriver driver;

    // Constructor
    public PhpTravels_Home_Page(WebDriver driver) {
	this.driver = driver;
    }

    // Elements
    private By gotit_cookie_button = By.xpath("//button[@aria-label='dismiss cookie message']");
    private By myaccount_link = By.linkText("MY ACCOUNT");
    private By signup_link = By.linkText("Sign Up");
    private By login_link = By.linkText("Login");
    private By hotels_link = By.xpath("//a[contains(text(),'Hotels')]");
    private By hotels_destination_field = By.xpath("//div[contains(@class,'locationlistHotels')]");
    private By hotels_checkin_field = By.id("checkin");
    private By hotels_checkout_field = By.id("checkout");
    private By hotels_search_button = By.xpath("//form[@name='HOTELS']//button[contains(text(),'Search')]");
//    private By flights_link = By.xpath("//a[contains(text(),'Flights')]");
    private By hotels_parents_up_button = By.xpath(
	    "//form[@name='HOTELS']//input[@name='adults']//parent::div[contains(@class,'input-group')]//button[contains(@class,'bootstrap-touchspin-up')]");
    private By hotels_parents_down_button = By.xpath(
	    "//form[@name='HOTELS']//input[@name='adults']//parent::div[contains(@class,'input-group')]//button[contains(@class,'bootstrap-touchspin-down')]");
    private By hotels_child_up_button = By.xpath(
	    "//form[@name='HOTELS']//input[@name='children']//parent::div[contains(@class,'input-group')]//button[contains(@class,'bootstrap-touchspin-up')]");
//    private By hotels_children_down_button = By.xpath(
//	    "//form[@name='HOTELS']//input[@name='children']//parent::div[contains(@class,'input-group')]//button[contains(@class,'bootstrap-touchspin-down')]");
    private By boats_link = By.xpath("//a[contains(text(),'Boats')]");
    private By boats_destination_field = By.xpath("//div[contains(@class,'locationlistboats')]");
    private By boattype_choose = By.id("boattype_chosen");
    private By boats_date_field = By.xpath("//div[@id='boats']//input[@id='DateTours']");
    private By boats_adults_up_button = By
	    .xpath("//div[@id='boats']//button[contains(@class,'bootstrap-touchspin-up')]");
    private By boats_search_button = By.xpath("//div[@id='boats']//button[contains(text(),'Search')]");

    private By search_results(String searchresult) {
	return By.xpath("//div[contains(text(),'" + searchresult + "')]");
    }
    private By boattype_results(String searchresult) {
	return By.xpath("//li[contains(text(),'" + searchresult + "')]");
    }

    // Methods
    public PhpTravels_Home_Page dismissCookieBar() {
	ElementActions.click(driver, gotit_cookie_button);
	return this;
    }
    
    @Step("Navigate to Login page")
    public PhpTravels_Login_Page navigateToLoginPage() {
	ExtentReport.info(MarkupHelper.createLabel("Navigate to Login page", ExtentColor.BLUE));
	clickOnMyAccountLink();
	clickOnLoginLink();
	return new PhpTravels_Login_Page(driver);
    }

    @Step("Navigate to Sign Up page")
    public PhpTravels_SignUp_Page navigateToSignUpPage() {
	ExtentReport.info(MarkupHelper.createLabel("Navigate to Sign Up page", ExtentColor.BLUE));
	clickOnMyAccountLink();
	clickOnSignUpLink();
	return new PhpTravels_SignUp_Page(driver);
    }

    @Step("Click on MY ACCOUNT link")
    public PhpTravels_SignUp_Page clickOnMyAccountLink() {
	ElementActions.click(driver, myaccount_link);
	return new PhpTravels_SignUp_Page(driver);
    }

    @Step("Click on Login link")
    public PhpTravels_SignUp_Page clickOnLoginLink() {
	ElementActions.click(driver, login_link);
	return new PhpTravels_SignUp_Page(driver);
    }

    @Step("Click on Sign Up link")
    public PhpTravels_SignUp_Page clickOnSignUpLink() {
	ElementActions.click(driver, signup_link);
	return new PhpTravels_SignUp_Page(driver);
    }

    @Step("Search for HOTELS with Data --> DESTINATION: [{hotelsOrCityname}], CHECK IN date: [{checkinDate}], CHECK OUT date: [{checkoutDate}], ADULTS Count; [{adultsCount}], CHILD Count; [{childCount}]")
    public PhpTravels_HotelsDetails_Page hotelsSearch(String hotelsOrCityname, String checkinDate, String checkoutDate,
	    String adultsCount, String childCount) {
	ExtentReport.info(MarkupHelper.createLabel("Search for HOTELS", ExtentColor.BLUE));

	clickOnHotelsLink();
	selectHotelsDestination(hotelsOrCityname);
	enterCheckinDate(checkinDate);
	enterCheckoutDate(checkoutDate);
	enterHotelsAdultsCount(adultsCount);
	enterHotelsChildCount(childCount);
	clickOnHotelsSearchButton();
	return new PhpTravels_HotelsDetails_Page(driver);
    }

    @Step("Click on the HOTELS to Display the Hotels Fields")
    public PhpTravels_Home_Page clickOnHotelsLink() {
	ElementActions.click(driver, hotels_link);
	return this;
    }

    @Step("Select HOTELS DESTINATION --> [{hotelsOrCityName}]")
    public PhpTravels_Home_Page selectHotelsDestination(String hotelsOrCityName) {
	ElementActions.click(driver, hotels_destination_field);
//	ElementActions.type(driver, destination_field, hotelsOrCityname);
	ElementActions.click(driver, search_results(hotelsOrCityName));
	return this;
    }

    @Step("Enter HOTELS CHECK IN Date --> [{checkinDate}]")
    public PhpTravels_Home_Page enterCheckinDate(String checkinDate) {
	ElementActions.type(driver, hotels_checkin_field, checkinDate);
	return this;
    }

    @Step("Enter HOTELS CHECK OUT Date --> [{checkoutDate}]")
    public PhpTravels_Home_Page enterCheckoutDate(String checkoutDate) {
	ElementActions.type(driver, hotels_checkout_field, checkoutDate);
	return this;
    }

    @Step("Enter HOTELS ADULTS Count --> [{adultsCount}]")
    public PhpTravels_Home_Page enterHotelsAdultsCount(String adultsCount) {
	int count = Integer.parseInt(adultsCount);
	ElementActions.click(driver, hotels_parents_down_button);
	ElementActions.click(driver, hotels_parents_down_button);

	for (int i = 0; i < count; i++) {
	    ElementActions.click(driver, hotels_parents_up_button);
	}
	return this;
    }

    @Step("Enter HOTELS CHILD Count --> [{childCount}]")
    public PhpTravels_Home_Page enterHotelsChildCount(String childCount) {
	int count = Integer.parseInt(childCount);
	for (int i = 0; i < count; i++) {
	    ElementActions.click(driver, hotels_child_up_button);
	}
	return this;
    }

    @Step("Click on HOTLES SEARCH button")
    public PhpTravels_Home_Page clickOnHotelsSearchButton() {
	ElementActions.click(driver, hotels_search_button);
	return this;
    }

    @Step("Search for BOATS with Data --> DESTINATION: [{boatName}], BOAT TYPE: [{boatType}], BOAT date: [{boatDate}], ADULTS Count; [{adultsCount}]")
    public PhpTravels_BoatsDetails_Page boatsSearch(String boatName, String boatType, String boatDate,
	    String adultsCount) {
	ExtentReport.info(MarkupHelper.createLabel("Search for BOATS", ExtentColor.BLUE));
	clickOnBoatsLink();
	selectBoatsDestination(boatName);
	selectBoatType(boatType);
	enterboatDate(boatDate);
	enterBoatsAdultsCount(adultsCount);
	clickOnBoatsSearchButton();
	return new PhpTravels_BoatsDetails_Page(driver);
    }

    @Step("Click on BOATS Link")
    public PhpTravels_Home_Page clickOnBoatsLink() {
	ElementActions.click(driver, boats_link);
	return this;
    }

    @Step("Select BOATS DESTINATION --> [{boatName}]")
    public PhpTravels_Home_Page selectBoatsDestination(String boatName) {
	ElementActions.click(driver, boats_destination_field);
//	ElementActions.type(driver, destination_field, hotelsOrCityname);
	ElementActions.click(driver, search_results(boatName));
	return this;
    }

    @Step("Select a BOAT TYPE --> [{boatType}]")
    public PhpTravels_Home_Page selectBoatType(String boatType) {
	ElementActions.click(driver, boattype_choose);
	ElementActions.click(driver, boattype_results(boatType));
	return this;
    }

    @Step("Enter BOATS DATE --> [{boatDate}]")
    public PhpTravels_Home_Page enterboatDate(String boatDate) {
	ElementActions.type(driver, boats_date_field, boatDate);
	return this;
    }

    @Step("Enter BOATS ADULTS Count --> [{adultsCount}]")
    public PhpTravels_Home_Page enterBoatsAdultsCount(String adultsCount) {
	int count = Integer.parseInt(adultsCount);
	for (int i = 0; i < count; i++) {
	    ElementActions.click(driver, boats_adults_up_button);
	}
	return this;
    }

    @Step("Click on BOATS SEARCH button")
    public PhpTravels_Home_Page clickOnBoatsSearchButton() {
	ElementActions.click(driver, boats_search_button);
	return this;
    }

}
