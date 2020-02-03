package tom.hyprid.tests;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import generic.BrowserFactory;
import generic.BrowserFactory.BrowserType;
import tom.hyprid.pages.GoogleHomePage;
import tom.hyprid.pages.GoogleSearchResultsPage;

public class GoogleSearchTest {
    private WebDriver driver;

    GoogleHomePage google;
    GoogleSearchResultsPage searchResultsPage;

    @BeforeClass
    public void initWebDriver() {
	driver = BrowserFactory.browser(BrowserType.GOOGLE_CHROME);
	google = new GoogleHomePage(driver);
	searchResultsPage = new GoogleSearchResultsPage(driver);
    }

    @Test
    public void TestGoogleSearch() {
	google.openURL();
	assertTrue(google.getTitleText().equals("Google"));
	assertEquals(google.getTitleText(), "Google");
	String searchResultText = google.googleSearch("Selenium WebDriver").getSearchResultText();

	assertEquals(searchResultText, "WebDriver - Selenium");
	assertTrue(searchResultText.contains("WebDriver - Selenium"));

	searchResultsPage.clickOnSearchResult();

    }

    @AfterClass
    public void quitWebDriver() {
	driver.quit();
    }
}
