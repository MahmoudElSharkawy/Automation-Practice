package phptravels.gui.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;

import io.qameta.allure.Step;
import utilities.BrowserActions;
import utilities.ElementActions;
import utilities.ExtentReport;
import utilities.Helper;
import utilities.BrowserActions.ConfirmAlertType;

public class PhpTravels_Invoice_Page {
    private WebDriver driver;

    // Elements locators
    private By payonarrival_button = By.xpath("//button[contains(.,'Pay on Arrival')]");
    private By booking_status = By.xpath("//div[@class='content']//h4");

    // Constructor
    public PhpTravels_Invoice_Page(WebDriver driver) {
	this.driver = driver;
    }

    //////////////////////////////////////////////////////////////////
    //////////////////////////// Actions ////////////////////////////

    @Step("Click on PAY ON ARRIVAL button and accept pay at arrival alert")
    public PhpTravels_Invoice_Page clickOnPayOnArrivalAndAcceptAlert() {
	ExtentReport.info(MarkupHelper.createLabel("Click on PAY ON ARRIVAL button and accept pay at arrival alert",
		ExtentColor.BLUE));

	ElementActions.click(driver, payonarrival_button);
	BrowserActions.confirmAlert(driver, ConfirmAlertType.ACCEPT);
	Helper.getExplicitWait(driver).until(
		ExpectedConditions.invisibilityOfElementWithText(booking_status, "Your booking status is Unpaid"));
	return this;
    }

    @Step("Get the text of the current invoice status")
    public String getInvoiceStatus() {
	return ElementActions.getText(driver, booking_status);
    }

}
