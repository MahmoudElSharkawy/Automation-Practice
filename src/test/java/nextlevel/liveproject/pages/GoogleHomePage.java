package nextlevel.liveproject.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

import io.qameta.allure.Step;

public class GoogleHomePage {
    private WebDriver driver;

    // Constructor
    public GoogleHomePage(WebDriver driver) {
	this.driver = driver;
    }

    // Elements
    private By google_search_bar = By.name("q");
    private By google_search_btn = By.name("btnK");

    // Methods
    @Step("click on Google search button for no reason")
    public GoogleHomePage clickOnGoogleSearchButton() {
	driver.findElement(google_search_btn).click();
	return this;
    }
    @Step("Searching and clicking Enter")
    public GoogleSearchResultsPage googleSearch(String searchData) {
	driver.findElement(google_search_bar).sendKeys(searchData, Keys.ENTER);
	return new GoogleSearchResultsPage(driver);
    }

}
