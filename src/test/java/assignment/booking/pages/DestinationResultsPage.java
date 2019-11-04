package assignment.booking.pages;

import static org.testng.Assert.assertTrue;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import generic.Utils;

public class DestinationResultsPage {
    WebDriver driver;

    public DestinationResultsPage(WebDriver driver) {
	this.driver = driver;
    }

    public void assertHotelIsListedInTheResultSet(String hotelName) {
	Utils.getWait(driver).until(ExpectedConditions.visibilityOf(getHotelNameLocator(hotelName)));
	Utils.getJsExecutor(driver, "arguments[0].scrollIntoView(true);", getHotelNameLocator(hotelName));
	assertTrue(getHotelNameLocator(hotelName).getText().contains("Aqua Park"));
    }

    public void clickOnSeeAvailability(String hotelName) {
	getSeeAvailabilityLocator(hotelName).click();
    }
    
    /////////////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////// PRIVATE METHODS ////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////////////////////////////////

    private WebElement getHotelNameLocator(String hotelName) {
	return driver.findElement(By.xpath("(//span[contains(text(),'" + hotelName + "')])[1]"));
    }

    private WebElement getSeeAvailabilityLocator(String hotelName) {
	return driver.findElement(By.xpath("(//div[@data-et-click][contains(.,'" + hotelName
		+ "')]//following::a[contains(.,'See availability')])[1]"));
    }

}
