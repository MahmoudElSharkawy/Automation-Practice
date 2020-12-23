package liveproject.google.gui.pages;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import io.qameta.allure.Step;

public class Google_SearchResultsPage {
    private WebDriver driver;

    // Constructor
    public Google_SearchResultsPage(WebDriver driver) {
	this.driver = driver;
    }

    // Elements
//    private By google_search_result = By.xpath("(//h3[@class='LC20lb'])[1]");

    // Methods
    private WebElement getGoogleSearchResultIndex(String resultIndex) {
	return driver.findElement(By.xpath("(//h3[contains (@class, 'LC20lb')])" + "[" + resultIndex + "]"));
    }

    @Step("Assert on the page title")
    public Google_SearchResultsPage assertOnPageTitle(String searchData) {
	assertEquals(driver.getTitle(), searchData + " - Google Search");
	return this;
    }

    @Step("Assert on the search results")
    public Google_SearchResultsPage assertOnSearchResult(String expected, String resultIndex) {
	assertTrue(getGoogleSearchResultIndex(resultIndex).getText().contains(expected));
	return this;
    }

}
