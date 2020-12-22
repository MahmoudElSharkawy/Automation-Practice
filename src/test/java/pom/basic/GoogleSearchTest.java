package pom.basic;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class GoogleSearchTest {
    private WebDriver driver;

    GoogleHomePage homePage;
    GoogleSearchResultsPage searchResultsPage;

    @BeforeClass
    public void initWebDriver() {
	System.setProperty("webdriver.chrome.driver", "src/main/resources/drivers/windows-64/chromedriver.exe");
	driver = new ChromeDriver();
	driver.manage().window().maximize();

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
