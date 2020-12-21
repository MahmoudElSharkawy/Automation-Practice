package nextlevel.liveproject.pages;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class GoogleSearchResultsPage {
    private WebDriver driver;

    // Constructor
    public GoogleSearchResultsPage(WebDriver driver) {
	this.driver = driver;
    }

    // Elements
//    private By google_search_result = By.xpath("(//h3[@class='LC20lb'])[1]");

    // Methods
    private WebElement getGoogleSearchResultIndex(String resultIndex) {
	return driver.findElement(By.xpath("(//h3[contains (@class, 'LC20lb')])" + "[" + resultIndex + "]"));
    }

    public GoogleSearchResultsPage assertOnPageTitle(String searchData) {
	assertTrue(driver.getTitle().contains(searchData));
	return this;
    }

    public GoogleSearchResultsPage assertOnSearchResult(String searchData, String resultIndex) {
	assertEquals(getGoogleSearchResultIndex(resultIndex).getText(), searchData);
	return this;
    }

}
