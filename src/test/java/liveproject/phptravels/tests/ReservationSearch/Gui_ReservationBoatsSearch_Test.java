package liveproject.phptravels.tests.ReservationSearch;

import java.io.File;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
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
import liveproject.phptravels.gui.pages.PhpTravels_Home_Page;
import utils.BrowserActions;
import utils.BrowserFactory;
import utils.PropertiesReader;
import utils.ExcelFileManager;

@Epic("PHPTRAVELS")
@Feature("GUI")
public class Gui_ReservationBoatsSearch_Test {
    WebDriver driver;
    ExcelFileManager spreadSheet;

    String phptravelsHomePageURL = PropertiesReader.getProperty("liveproject.properties", "phptravels.home.url");

    @BeforeClass
    public void setUp() {
	spreadSheet = new ExcelFileManager(
		new File("src/test/resources/TestData/LiveProject_PhpTravels_ReservationBoatsSearch_TestData.xlsx"));
	spreadSheet.switchToSheet("GUI");
	driver = BrowserFactory.getBrowser();
	BrowserActions.navigateToUrl(driver, phptravelsHomePageURL);
    }

    @Test(description = "Validating the search function of the Boats")
    @Description("Given I'm on the PHPTravels home page; When I enter the data needed to search for Boats And click the search button; Then I should be navigated to the Boats search results page, Then I should get the search results related to the search value entered")
    @Story("Reservation Search")
    @Severity(SeverityLevel.CRITICAL)
    @TmsLink("Test_case")
    @Issue("Software_bug")
    public void testingBoatsSearch() {
	String boatName =
		new PhpTravels_Home_Page(driver)
			.dismissCookieBar()
			.boatsSearch(spreadSheet.getCellData("Boat Name", 2), spreadSheet.getCellData("Boat Type", 2),
				spreadSheet.getCellData("Boat Date", 2), spreadSheet.getCellData("Adults Count", 2))
			.getBoatNameText();
	Assert.assertEquals(boatName, spreadSheet.getCellData("Expected Boat Name", 2));

    }

    @AfterClass
    public void closingBrowser() {
	BrowserActions.closeAllOpenedBrowserWindows(driver);
    }
}
