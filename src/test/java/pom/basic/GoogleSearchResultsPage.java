package pom.basic;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class GoogleSearchResultsPage {
    WebDriver driver;

    private final static By google_search_result = By.xpath("(//h3[@class='LC20lb'])[1]");

    public GoogleSearchResultsPage(WebDriver driver) {
	this.driver = driver;
    }

    public String getTitleText() {
	return driver.getTitle();
    }

    public String getSearchResultText() {
	return driver.findElement(google_search_result).getText();
    }

    public void clickOnSearchResult() {
	driver.findElement(google_search_result).click();
    }
}
