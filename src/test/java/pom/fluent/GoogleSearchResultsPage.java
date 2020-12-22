package pom.fluent;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class GoogleSearchResultsPage {
    private WebDriver driver;

    private By google_search_result = By.xpath("(//h3[contains(@class,'LC20lb')])[1]");

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
