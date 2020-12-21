package nextlevel.liveproject.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Step;

@Epic("Live Project")
@Feature("Google Search")

public class GoogleHomePage {
    private WebDriver driver;

    // Constructor
    public GoogleHomePage(WebDriver driver) {
	this.driver = driver;
    }

    // Elements
    private By google_search_bar = By.name("q");

    // Method
    @Step("Searching and clicking Enter")
    public GoogleSearchResultsPage googleSearch(String searchData) {
	driver.findElement(google_search_bar).sendKeys(searchData, Keys.ENTER);
	return new GoogleSearchResultsPage(driver);
    }

}
