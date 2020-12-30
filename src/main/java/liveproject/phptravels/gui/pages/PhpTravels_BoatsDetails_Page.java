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
    private By boatName_title = By.id("detail-content-sticky-nav-00");

    // Methods
    @Step("Get the text of the Boat Name")
    public String getBoatNameText() {
	return ElementActions.getText(driver, boatName_title);
    }
    
}
