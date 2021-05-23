package phptravels.gui.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import io.qameta.allure.Step;
import utils.BrowserActions;
import utils.ElementActions;
import utils.PropertiesReader;

public class PhpTravels_UserAccount_Page {
    private WebDriver driver;

    // Elements Locators
    private By hi_text = By.xpath("//*[@style='margin-left: 17px']");
    private By booking_status = By.xpath("//div[@id='bookings']//h5");

    // Constructor
    public PhpTravels_UserAccount_Page(WebDriver driver) {
	this.driver = driver;
    }

    //////////////////////////////////////////////////////////////////
    //////////////////////////// Actions ////////////////////////////

    public PhpTravels_UserAccount_Page navigateAccountPage() {
	BrowserActions.navigateToUrl(driver,
		PropertiesReader.getProperty("automationPractice.properties", "phptravels.baseuri") + "/account");
	return this;
    }

    @Step("Get the text of the Hi message")
    public String getHiMessage() {
	return ElementActions.getText(driver, hi_text);
    }

    @Step("Get the text of the current Booking status")
    public String getBookingStatus() {
	return ElementActions.getText(driver, booking_status);
    }

}
