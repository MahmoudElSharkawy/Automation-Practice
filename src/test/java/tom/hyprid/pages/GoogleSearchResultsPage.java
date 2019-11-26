package tom.hyprid.pages;

import static org.testng.Assert.assertTrue;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class GoogleSearchResultsPage {
    private WebDriver driver;

    private By google_search_result = By.xpath("(//h3[@class='LC20lb'])[1]");

    public GoogleSearchResultsPage(WebDriver driver) {
	this.driver = driver;
    }

    public void assertPageTitle() {
	assertTrue(driver.getTitle().contains("Selenium WebDriver"));
    }

    public void assertSearchResultText() {
	assertTrue(driver.findElement(google_search_result).getText().equals("Selenium WebDriver"));
    }

    public void clickOnSearchResult() {
	driver.findElement(google_search_result).click();
    }

}
