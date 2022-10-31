package google.tests;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import google.gui.pages.GoogleHomePage;
import google.gui.pages.GoogleSearchResultsPage;
import utils.BrowserActions;
import utils.BrowserFactory;

public class GoogleSearchTest {
    private WebDriver driver;

    GoogleHomePage homePage;
    GoogleSearchResultsPage searchResultsPage;

    @BeforeClass
    public void setup() {
	driver = BrowserFactory.getBrowser();

	homePage 		= new GoogleHomePage(driver);
	searchResultsPage 	= new GoogleSearchResultsPage(driver);
    }

    @Test(description = "Test Google Search")
    public void TestGoogleSearch() {
	String searchData = "Selenium WebDriver";
	
	homePage		.openURL();
	homePage		.assertOnPageTitle();
	homePage		.googleSearch(searchData);
	searchResultsPage	.assertOnPageTitle(searchData);
	searchResultsPage	.clickOnSearchResult("1");

    }

    @AfterClass
    public void quitWebDriver() {
	BrowserActions.closeAllOpenedBrowserWindows(driver);
    }
}
