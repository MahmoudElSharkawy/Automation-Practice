package phptravels.tests;

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
import phptravels.gui.pages.PhpTravels_Home_Page;
import utils.BrowserActions;
import utils.BrowserFactory;
import utils.ExcelFileManager;

@Epic("PHPTRAVELS")
@Feature("GUI")
public class Gui_ReservationHotelsSearch_Test {
    private WebDriver driver;
    private ExcelFileManager spreadSheet;

    @BeforeClass
    public void setUp() {
	spreadSheet = new ExcelFileManager(
		new File("src/test/resources/TestData/PhpTravels_ReservationHotelsSearch_TestData.xlsx"));
	spreadSheet.switchToSheet("GUI");
	driver = BrowserFactory.getBrowser();
	new PhpTravels_Home_Page(driver).navigateToHomePage();
    }

    @Test(description = "PHPTRAVELS - GUI - Validating the search function of the Hotels")
    @Description("Given I'm on the PHPTravels home page; When I enter the data needed to search for hotels And click the search button; Then I should be navigated to the hotels search results page, Then I should get the search results related to the search value entered")
    @Story("Reservation Search")
    @Severity(SeverityLevel.CRITICAL)
    @TmsLink("Test_case")
    @Issue("Software_bug")
    public void testingHotelsSearch() {
	String hotelName = new PhpTravels_Home_Page(driver)
		.dismissCookieBar()
		.hotelsSearch(spreadSheet.getCellData("Destination", 2),
				spreadSheet.getCellData("Check In Date", 2),
				spreadSheet.getCellData("Check Out Date", 2),
				spreadSheet.getCellData("Adults Count", 2), spreadSheet.getCellData("Child Count", 2))
		.getHotelNameText();
	Assert.assertEquals(hotelName, spreadSheet.getCellData("Expected Hotel Name", 2));

    }

    @AfterClass
    public void closingBrowser() {
	BrowserActions.closeAllOpenedBrowserWindows(driver);
    }
}
