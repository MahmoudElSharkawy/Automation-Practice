package liveproject.phptravels.gui.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;

import io.qameta.allure.Step;
import utils.ElementActions;
import utils.ExtentReport;
import utils.Logger;

public class PhpTravels_UserAccount_Page {
    private WebDriver driver;

    // Constructor
    public PhpTravels_UserAccount_Page(WebDriver driver) {
	this.driver = driver;
    }

    // Elements
    private By hi_text = By.xpath("//*[@style='margin-left: 17px']");
    private By booking_status = By.xpath("//div[@id='bookings']//h5");

    // Methods
    @Step("Get the text of the Hi message")
    public String getHiMessage() {
	ExtentReport.info(MarkupHelper.createLabel("Get the text of the Hi message", ExtentColor.BLUE));

	String hiMessage = ElementActions.getText(driver, hi_text);
	Logger.logMessage("The Hi message is: " + hiMessage);
	return hiMessage;
    }
    
    @Step("Get the text of the current Booking status")
    public String getBookingStatus() {
	String bookingStatus = ElementActions.getText(driver, booking_status);
	Logger.logMessage("The Hi message is: " + bookingStatus);
	return bookingStatus;
    }
    
}
