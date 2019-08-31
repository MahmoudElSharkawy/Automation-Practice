package pom.pages;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


public class GoogleSearchResultsPage {
	//variables
	
	//Elements
	private By google_first_search_result = By.xpath("(//h3[@class='LC20lb'])[1]");
	
	//Methods
	public void assertOnPageTitle(String searchData, WebDriver driver) {
		assertTrue(driver.getTitle().contains(searchData));

	}
	
	public void assertOnSearchResult(String searchData, WebDriver driver) {
		WebElement searchResults = driver.findElement(google_first_search_result);
		assertEquals(searchResults.getText(), searchData);
		
	}
	
	public void clickOnSearchResult(WebDriver driver) {
		driver.findElement(google_first_search_result).click();
	}

}
