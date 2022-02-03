package phptravels.gui.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import io.qameta.allure.Step;
import utilities.ElementActions;

public class PhpTravels_HotelsDetails_Page {
    private WebDriver driver;

    // Elements Locators
    private By hotelName_title = By.id("detail-content-sticky-nav-00");

    // Constructor
    public PhpTravels_HotelsDetails_Page(WebDriver driver) {
	this.driver = driver;
    }

    //////////////////////////////////////////////////////////////////
    //////////////////////////// Actions ////////////////////////////

    @Step("Get the text of the Hotel Name")
    public String getHotelNameText() {
	return ElementActions.getText(driver, hotelName_title);
    }

}
