package nextlevel.liveproject.tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.annotations.Test;

import nextlevel.liveproject.pages.GoogleHomePage;
import nextlevel.liveproject.pages.GoogleSearchResultsPage;

public class GoogleSearchTest extends BaseTests {
    EventFiringWebDriver driver;

    GoogleSearchResultsPage googleSearchResults = new GoogleSearchResultsPage(driver);
    GoogleHomePage homePage = new GoogleHomePage(driver);

    @Test
    public void testingGoogleSearch() {
	String searchIndex = "[1]";
	String searchData = "Selenium WebDriver";
	googleSearchResults = homePage.googleSearch(searchData);
	googleSearchResults.assertOnPageTitle(searchData);
	googleSearchResults.assertOnSearchResult(searchData, searchIndex);
	googleSearchResults.clickOnSearchResult(searchIndex);
    }
}
