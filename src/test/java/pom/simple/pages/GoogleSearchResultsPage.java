package pom.simple.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class GoogleSearchResultsPage {
	private final static By google_search_result = By.xpath("(//h3[@class='LC20lb'])[1]");
	
    public String getTitleText(WebDriver driver) {
    	return driver.getTitle();
    }
    
	public String getSearchResultText(WebDriver driver) {
		return driver.findElement(google_search_result).getText();
	}

	public void clickOnSearchResult(WebDriver driver) {
		driver.findElement(google_search_result).click();
	}
}
