package liveproject.phptravels.reservation.search.tests;

import java.io.File;

import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Issue;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import io.qameta.allure.TmsLink;
import liveproject.phptravels.pages.PhpTravelsHomePage;
import utils.AllureReport;
import utils.BrowserFactory;
import utils.PropertiesReader;
import utils.Spreadsheet;
import utils.BrowserFactory.BrowserType;

@Epic("Live Project")
@Feature("PHPTravels Reservation Search")
public class PhpTravelsReservationTest {
    private WebDriver driver;
    Spreadsheet spreadSheet;

    String phptravelsHomePageURL = PropertiesReader.getProperty("liveproject.properties", "phptravels.home.url");

    @BeforeClass
    public void setUp() {
	spreadSheet = new Spreadsheet(new File("src/test/resources/TestData/LiveProject_PHPTravels_ReservarionSearch_TestData.xlsx"));
	spreadSheet.switchToSheet("testsheet2");
	driver = BrowserFactory.openBrowser_remote(BrowserType.FROM_PROPERTIES);
	driver.get(phptravelsHomePageURL);
    }

    @Test(description = "Validating the search function of the HOTELS")
    @Story("Reservation Search Story")
    @Description("Given I'm on the PHPTravels home page; When I Enter the data And click search; Then I should be navigated to the search results page And get the search results related to the search value entered")
    @Severity(SeverityLevel.CRITICAL)
    @TmsLink("focus-case-1539798")
    @Issue("bug-tracker#1")
    public void testingHotelsSearch() {
//	String searchIndex = spreadSheet.getCellData("Search Index", 1);
//	String searchData = spreadSheet.getCellData("Search Data", 1);
//	String expected = spreadSheet.getCellData("Expected", 1);
//
//	new GoogleHomePage(driver)
//		.googleSearch(searchData)
//		.assertOnSearchResult(expected, searchIndex)
//		.assertOnPageTitle(searchData);
	new PhpTravelsHomePage(driver).hotelsSearch();
	
    }

    @AfterMethod
    public void AfterMethod(ITestResult result) {
	if (result.getStatus() == ITestResult.FAILURE) {
	    AllureReport.logMessage("The Test Case Failed!; Taking Screenshot....");
	    AllureReport.logScreenshot(driver);
	}
    }

    @AfterClass
    public void closingBrowser() {
	driver.quit();
    }
}
