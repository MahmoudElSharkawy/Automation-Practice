package assignments.booking.pages;

import static org.testng.Assert.assertTrue;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import examples.datadriven.FileDataReader;
import generic.Utils;

public class DestinationResultsPage {
    WebDriver driver;
    FileDataReader dataReader;

    public DestinationResultsPage(WebDriver driver) {
	this.driver = driver;
    }

    public void assertHotelIsListedInTheResultSet(String hotelName) {
	Utils.getWait(driver).until(ExpectedConditions.visibilityOf(getHotelNameLocator(hotelName)));
	Utils.jsExecutor(driver, "arguments[0].scrollIntoView(true);", getHotelNameLocator(hotelName));
	assertTrue(getHotelNameLocator(hotelName).getText().contains(hotelName));
    }

    public void clickOnSeeAvailabilityAndSwitchToDestinationPage(String hotelName) {
	getSeeAvailabilityLocator(hotelName).click();

	for (String winHandle : driver.getWindowHandles()) {
	    if (driver.switchTo().window(winHandle).getTitle().contains(hotelName)) {
		driver.switchTo().window(winHandle);
	    }
	}
//	assertTrue(driver.getTitle().contains(hotelName));
	System.out.println(driver.getTitle());
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
