package liveproject.phptravels.gui.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import io.qameta.allure.Step;
import utils.ElementActions;

public class PhpTravels_BoatsDetails_Page {
    private WebDriver driver;

    // Constructor
    public PhpTravels_BoatsDetails_Page(WebDriver driver) {
	this.driver = driver;
    }

    // Elements
    private By gotit_cookie_button = By.xpath("//button[@aria-label='dismiss cookie message']");
    private By boatName_title = By.id("detail-content-sticky-nav-00");
    private By booknow_button = By.xpath("//div[@class='booking-selection-box']//button[contains(.,'Book Now')]");

    // Methods
    public PhpTravels_BoatsDetails_Page dismissCookieBar() {
	ElementActions.click(driver, gotit_cookie_button);
	return this;
    }

    @Step("Get the text of the Boat Name")
    public String getBoatNameText() {
	return ElementActions.getText(driver, boatName_title);
    }
    
    @Step("Click on BOOK NOW button")
    public PhpTravels_BoatsBook_Page clickOnBookNow() {
	ElementActions.click(driver, booknow_button);
	return new PhpTravels_BoatsBook_Page(driver);
    } 

}
