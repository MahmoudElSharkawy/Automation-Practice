package liveproject.phptravels.gui.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import io.qameta.allure.Step;
import utils.ElementActions;

public class PhpTravels_BoatsBook_Page {
    private WebDriver driver;

    // Constructor
    public PhpTravels_BoatsBook_Page(WebDriver driver) {
	this.driver = driver;
    }

    // Elements
    private By confirmthisbooking_button = By.name("logged");

    // Methods
    @Step("Dismiss the cookie bar")
    public PhpTravels_BoatsBook_Page dismissCookieBar() {
	ElementActions.click(driver, confirmthisbooking_button);
	return this;
    }

}
