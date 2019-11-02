package pom.simple.tests;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import pom.simple.generic.BrowserFactory;
import pom.simple.generic.BrowserFactory.BrowserType;
import pom.simple.pages.GoogleHomePage;
import pom.simple.pages.GoogleSearchResultsPage;

public class GoogleSearchTest {
    private WebDriver driver;

    GoogleHomePage homePage;
    GoogleSearchResultsPage searchResultsPage;

    @BeforeClass
    public void initWebDriver() {
	driver = BrowserFactory.browser(BrowserType.GOOGLE_CHROME);
	homePage = new GoogleHomePage(driver);
	searchResultsPage = new GoogleSearchResultsPage(driver);
    }

    @Test
    public void TestGoogleSearch() {
	homePage.openURL();
	assertTrue(homePage.getTitleText().equals("Google"));
	assertEquals(homePage.getTitleText(), "Google");
	homePage.googleSearch("Selenium WebDriver");

	assertTrue(searchResultsPage.getTitleText().contains("Selenium WebDriver"));
	assertEquals(searchResultsPage.getSearchResultText(), "Selenium WebDriver");
	searchResultsPage.clickOnSearchResult();

    }

    @AfterClass
    public void quitWebDriver() {
	driver.quit();
    }
}
