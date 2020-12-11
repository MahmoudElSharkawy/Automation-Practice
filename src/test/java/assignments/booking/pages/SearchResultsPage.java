package assignments.booking.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import engine.Utils;

public class SearchResultsPage {
    private WebDriver driver;
    
    private By review_score = By.xpath("//a[@data-category='review_score_and_price']");
    private By loading_overlay = By.xpath("//div[@class='sr-usp-overlay__loading']");


    public SearchResultsPage(WebDriver driver) {
	this.driver = driver;
    }

    public SearchResultsPage sortByReviewScore() {
	Utils.getWait(driver).until(ExpectedConditions.visibilityOfElementLocated(review_score));
	driver.findElement(review_score).click();
	    Utils.getWait(driver).until(ExpectedConditions.invisibilityOfElementLocated(loading_overlay));
	   return this; 
    }

    public SearchResultsPage printHotelsAlongsideRatings(int numberOfHotels) {
	for (int i = 1; i <= numberOfHotels; i++) {
	    String s = String.valueOf(i);
	    System.out.println("Hotel Name: " + getTheHotelName(s).getText() + " || Hotel Score: " + getTheHotelScore(s).getText());
	}
	
	System.out.println();
	return this;
    }
    
    /////////////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////// PRIVATE METHODS ////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////////////////////

    private WebElement getTheHotelName(String hotelNumber) {
	Utils.getWait(driver).until(ExpectedConditions.visibilityOfElementLocated(
		By.xpath("(//span[contains(@class, 'hotel__name')])[" + hotelNumber + "]")));

	return driver.findElement(By.xpath("(//span[contains(@class, 'hotel__name')])[" + hotelNumber + "]"));
    }
    
    private WebElement getTheHotelScore(String hotelNumber) {
	Utils.getWait(driver).until(ExpectedConditions.visibilityOfElementLocated(
		By.xpath("(//div[contains(@class, 'score__badge')])[" + hotelNumber + "]")));
	
	return driver.findElement(By.xpath("(//div[contains(@class, 'score__badge')])[" + hotelNumber + "]"));
    }
}
