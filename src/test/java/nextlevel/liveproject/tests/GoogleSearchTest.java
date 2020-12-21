package nextlevel.liveproject.tests;

import java.io.File;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.qameta.allure.Description;
import io.qameta.allure.Issue;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.TmsLink;
import nextlevel.liveproject.pages.GoogleHomePage;
import nextlevel.liveproject.utils.BrowserFactory;
import nextlevel.liveproject.utils.PropertiesReader;
import nextlevel.liveproject.utils.SpreadsheetUtil;
import nextlevel.liveproject.utils.BrowserFactory.BrowserType;

public class GoogleSearchTest {
    private WebDriver driver;
    SpreadsheetUtil spreadSheet;
    String sheet = "testsheet2";

    String googleHomePageURL = PropertiesReader.getProperty("liveproject.properties", "google.home.url");

    @BeforeClass
    public void setUp() {
	spreadSheet = new SpreadsheetUtil(new File("src/test/resources/TestData/LiveProject_TestData.xlsx"));
	spreadSheet.switchToSheet(sheet);
	driver = BrowserFactory.openBrowser(BrowserType.FROM_PROPERTIES);
	driver.get(googleHomePageURL);
    }

    @Test
    @Description("Validating the Goole home page title")
    @Severity(SeverityLevel.CRITICAL)
    @TmsLink("focus-case-1539798")
    @Issue("bug-tracker#1")
    public void testingGoogleSearch() {
	String searchIndex = spreadSheet.getCellData("Search Index", 1);
	String searchData = spreadSheet.getCellData("Search Data", 1);
	String expected = spreadSheet.getCellData("Expected", 1);
	new GoogleHomePage(driver).googleSearch(searchData)
		.assertOnPageTitle(searchData)
		.assertOnSearchResult(expected, searchIndex);
    }

    @AfterClass
    public void tearDown() {
	driver.quit();
    }
}
