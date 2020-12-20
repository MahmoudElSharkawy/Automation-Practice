package nextlevel.liveproject.tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import nextlevel.liveproject.pages.GoogleHomePage;
import nextlevel.liveproject.pages.GoogleSearchResultsPage;
import nextlevel.liveproject.utils.BrowserFactory;
import nextlevel.liveproject.utils.PropertiesReader;
import nextlevel.liveproject.utils.BrowserFactory.BrowserType;

public class GoogleSearchTest {
    private WebDriver driver;
    GoogleHomePage homePage;
    GoogleSearchResultsPage googleSearchResults;

    @BeforeClass
    public void setUp() {
	driver = BrowserFactory.openBrowser(BrowserType.GOOGLE_CHROME);
	driver.get(PropertiesReader.getProperty("liveproject.properties", "url"));

	homePage = new GoogleHomePage(driver);
	googleSearchResults = new GoogleSearchResultsPage(driver);
    }

    @Test
    public void testingGoogleSearch() {
	String searchIndex = "[1]";
	String searchData = "Selenium WebDriver";
	String expected = "Selenium Projects - Selenium WebDriver";
	homePage.googleSearch(searchData);
	googleSearchResults.assertOnPageTitle(searchData);
	googleSearchResults.assertOnSearchResult(expected, searchIndex);
	googleSearchResults.clickOnSearchResult(searchIndex);
    }

    @AfterClass
    public void tearDown() {
	driver.quit();
    }
}
