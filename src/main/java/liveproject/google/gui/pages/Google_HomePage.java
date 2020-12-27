package liveproject.google.gui.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import io.qameta.allure.Step;
import utils.ElementActions;

public class Google_HomePage {
    private WebDriver driver;

    // Constructor
    public Google_HomePage(WebDriver driver) {
	this.driver = driver;
    }

    // Elements
    private By google_search_bar = By.name("q");

    // Methods
    @Step("Search for [{searchData}]")
    public Google_SearchResultsPage googleSearch(String searchData) {
	ElementActions.type(driver, google_search_bar, searchData);
	ElementActions.clickEnterKey(driver, google_search_bar);
	return new Google_SearchResultsPage(driver);
    }

}
