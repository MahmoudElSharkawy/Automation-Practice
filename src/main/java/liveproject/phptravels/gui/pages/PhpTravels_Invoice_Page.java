package liveproject.phptravels.gui.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

import io.qameta.allure.Step;
import utils.ElementActions;
import utils.Helper;

public class PhpTravels_Invoice_Page {
    private WebDriver driver;

    // Constructor
    public PhpTravels_Invoice_Page(WebDriver driver) {
	this.driver = driver;
    }

    // Elements
    private By payonarrival_button = By.xpath("//button[contains(.,'Pay on Arrival')]");
    private By booking_status = By.xpath("//div[@class='content']//h4");

    // Methods
    @Step("Click on PAY ON ARRIVAL button and accept pay at arrival alert")
    public PhpTravels_Invoice_Page clickOnPayOnArrivalAndAcceptAlert() {
	ElementActions.click(driver, payonarrival_button);
	Helper.alert(driver).accept();
	Helper.getExplicitWait(driver).until(
		ExpectedConditions.invisibilityOfElementWithText(booking_status, "Your booking status is Unpaid"));
	return this;
    }

    @Step("Get the text of the current invoice status")
    public String getInvoiceStatus() {
	return ElementActions.getText(driver, booking_status);
    }

}
