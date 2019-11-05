package assignment.booking.pages;

import static org.testng.Assert.assertTrue;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

import generic.Utils;

public class DestinationPage {
    WebDriver driver;
    private By hotel_name_text = By.cssSelector("h2.hp__hotel-name");

    public DestinationPage(WebDriver driver) {
	this.driver = driver;
    }

    public void assertHotelName(String hotelName) {
	Utils.getWait(driver).until(ExpectedConditions.visibilityOf(driver.findElement(hotel_name_text)));
	assertTrue(driver.findElement(hotel_name_text).getText().equals(hotelName));
    }
}
