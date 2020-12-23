package liveproject.google.gui.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

import io.qameta.allure.Step;

public class Google_HomePage {
    private WebDriver driver;

    // Constructor
    public Google_HomePage(WebDriver driver) {
	this.driver = driver;
    }

    // Elements
    private By google_search_bar = By.name("q");
    private By google_search_btn = By.name("btnK");

    // Methods
    @Step("click on Google search button for no reason")
    public Google_HomePage clickOnGoogleSearchButton() {
	driver.findElement(google_search_btn).click();
	return this;
    }
    @Step("Searching and clicking Enter")
    public Google_SearchResultsPage googleSearch(String searchData) {
	driver.findElement(google_search_bar).sendKeys(searchData, Keys.ENTER);
	return new Google_SearchResultsPage(driver);
    }

}
