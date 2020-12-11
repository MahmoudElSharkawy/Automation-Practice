package assignments.booking.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import engine.Utils;

public class HomePage {
    private WebDriver driver;

    private String url = "https://www.booking.com/";
    
    private By search_bar_destination = By.id("ss");
    private By suggested_destination_list = By.cssSelector("[aria-label='List of suggested destinations ']");
    private By accommodation = By.xpath("//div[@data-visible='accommodation,flights,rentalcars' and @class ='xp__dates xp__group']");
    private By dates_window = By.xpath("//div[@class ='bui-calendar__main b-a11y-calendar-contrasts']");
    private By calender_next = By.cssSelector("[data-bui-ref='calendar-next']");
    private By search_button = By.xpath("//button[@data-sb-id='main' and @type='submit']");

    public HomePage(WebDriver driver) {
	this.driver = driver;
    }

    public HomePage navigateToURL() {
	driver.get(url);
	return this;
    }

    public HomePage inputDestination(String destination) {
	Utils.getWait(driver).until(ExpectedConditions.elementToBeClickable(search_bar_destination));
	driver.findElement(search_bar_destination).clear();
	driver.findElement(search_bar_destination).sendKeys(destination);

	Utils.getWait(driver).until(ExpectedConditions.visibilityOf(driver.findElement(suggested_destination_list)));
	getDestinationElementLocatorFromTheList(destination).click();

	System.out.println(" The Destination is: " + destination);
	return this;
    }

    public HomePage inputAccommodationDates(String checkInDate, String checkOutDate) {	
	Utils.getWait(driver)
		.until(ExpectedConditions.and(ExpectedConditions.visibilityOf(getCheckInElementLocator(checkInDate)),
			ExpectedConditions.visibilityOf(getCheckOutElementLocator(checkOutDate))));
	getCheckInElementLocator(checkInDate).click();
	getCheckOutElementLocator(checkOutDate).click();

	return this;
    }

    //TODO: This methods needs enhancement to be dynamic based on the target Month needed
    public HomePage clickOnCalenderNextButton(int numberOfClicks) {
	driver.findElement(accommodation).click();

	Utils.getWait(driver).until(ExpectedConditions.visibilityOfElementLocated(dates_window));

	for (int i = 0; i < numberOfClicks; i++) {
	    Utils.getWait(driver).until(ExpectedConditions.elementToBeClickable(calender_next));
	    driver.findElement(calender_next).click();
	}
	return this;
    }
//    public HomePage clickOnCalenderNextButton(String targetMonth) {
//	while (condition) {
//	    
//	}
//	return this;
//    }	

    public SearchResultsPage clickOnTheSearchButton() {
	driver.findElement(search_button).click();
	return new SearchResultsPage(driver);
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////// PRIVATE METHODS ////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////////////////////

    private WebElement getDestinationElementLocatorFromTheList(String destination) {
	return driver.findElement(By.xpath("//span[text()='" + destination + "']"));
    }

    private WebElement getCheckInElementLocator(String checkInDate) {
	return driver.findElement(By.cssSelector("[data-date =" + "'" + checkInDate + "']"));
    }

    private WebElement getCheckOutElementLocator(String checkOutDate) {
	return driver.findElement(By.cssSelector("[data-date =" + "'" + checkOutDate + "']"));
    }

}
