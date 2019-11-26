package tom.hyprid.tests;

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
    }

    @Test
    public void TestGoogleSearch() {
	google.openURL();
	google.assertPageTitle();

//	google.googleSearch("Selenium WebDriver").assertTitleText();
	GoogleSearchResultsPage google2 = google.googleSearch("Selenium WebDriver");
	google2.assertPageTitle();
	google2.assertSearchResultText();
	google2.clickOnSearchResult();

    }

    @AfterClass
    public void quitWebDriver() {
	driver.quit();
    }
}
