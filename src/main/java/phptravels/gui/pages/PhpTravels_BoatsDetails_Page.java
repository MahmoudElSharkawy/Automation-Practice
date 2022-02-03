package phptravels.gui.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import io.qameta.allure.Step;
import utils.BrowserActions;
import utils.ElementActions;
import utils.PropertiesReader;

public class PhpTravels_BoatsDetails_Page {
    private WebDriver driver;

    // Elements Locators
    private By gotit_cookie_button = By.xpath("//button[@aria-label='dismiss cookie message']");
    private By boatName_title = By.id("detail-content-sticky-nav-00");
    private By bookNow_button = By.xpath("//div[@class='booking-selection-box']//button[contains(.,'Book Now')]");

    // Constructor
    public PhpTravels_BoatsDetails_Page(WebDriver driver) {
	this.driver = driver;
    }
    
    //////////////////////////////////////////////////////////////////
    //////////////////////////// Actions ////////////////////////////
    
    public PhpTravels_BoatsDetails_Page navigateBoatPage(String boatUrl) {
	BrowserActions.navigateToUrl(driver,
		PropertiesReader.getProperty("automationPractice.properties", "phptravels.baseuri") + boatUrl);
	return this;
    }
    
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
	ElementActions.click(driver, bookNow_button);
	return new PhpTravels_BoatsBook_Page(driver);
    } 

}
