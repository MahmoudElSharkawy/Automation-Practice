package liveproject.google.gui.pages;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import io.qameta.allure.Step;
import utils.ElementActions;

public class Google_SearchResultsPage {
    private WebDriver driver;

    // Constructor
    public Google_SearchResultsPage(WebDriver driver) {
	this.driver = driver;
    }

    // Elements
    private By result_link(String resultIndex) {
	return By.xpath("(//h3[contains (@class, 'LC20lb')])" + "[" + resultIndex + "]");
    }

    // Methods
    @Step("Assert on the page title; Expected: [{searchData}] - Google Search")
    public Google_SearchResultsPage assertOnPageTitle(String searchData) {
	assertEquals(driver.getTitle(), searchData + " - Google Search");
	return this;
    }

    @Step("Assert on the search results of results number: [{resultIndex}]; Expected: [{expected}], ")
    public Google_SearchResultsPage assertOnSearchResult(String expected, String resultIndex) {
	assertTrue(ElementActions.getText(driver, result_link(resultIndex)).contains(expected));
	return this;
    }
    
}
