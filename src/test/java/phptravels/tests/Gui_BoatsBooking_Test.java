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
import phptravels.apis.PhpTravels_APIs;
import phptravels.gui.pages.PhpTravels_BoatsDetails_Page;
import phptravels.gui.pages.PhpTravels_UserAccount_Page;
import utils.BrowserActions;
import utils.BrowserFactory;
import utils.ExcelFileManager;
import utils.Helper;

@Epic("PHPTRAVELS")
@Feature("GUI")
public class Gui_BoatsBooking_Test {
    WebDriver driver;
    ExcelFileManager spreadSheet;
    PhpTravels_APIs apis;

    String firstName, lastName, mobileNumber, email, password;
    String currentTime = Helper.getCurrentTime("yyyyMMddhhmmss");

    @BeforeClass
    public void setUp() {
	spreadSheet = new ExcelFileManager(
		new File("src/test/resources/TestData/LiveProject_PhpTravels_BoatsBooking_TestData.xlsx"));
	spreadSheet.switchToSheet("GUI");
	apis = new PhpTravels_APIs();
	driver = BrowserFactory.getBrowser();

	new PhpTravels_BoatsDetails_Page(driver).navigateBoatPage(
		"/boats/sri-lanka/colombo/Speedboat-Bravo-410---2016-refit-2016-?date=01/01/2025&adults=2");
    }

    @Test(description = "PHPTRAVELS - GUI - Validating the booking function of the Boats - PAY ON ARRIVAL payment method")
    @Description("When I book a boat, And confirm the booking request with PAY ON ARRIVAL payment method, Then the boat should be Reserved on my profile")
    @Story("Booking")
    @Severity(SeverityLevel.CRITICAL)
    @TmsLink("Test_case")
    @Issue("Software_bug")
    public void testingBoatsBooking_payOnArrival() {
	firstName = spreadSheet.getCellData("FirstName", 2);
	lastName = spreadSheet.getCellData("LastName", 2);
	mobileNumber = spreadSheet.getCellData("Mobile Number", 2);
	email = spreadSheet.getCellData("Email", 2) + currentTime + "@test.com";
	password = spreadSheet.getCellData("Password", 2);
	//sign up using api
	apis.userSignUp(firstName, lastName, mobileNumber, email, password);
	
	String invoiceStatus = new PhpTravels_BoatsDetails_Page(driver)
		.dismissCookieBar()
		.clickOnBookNow()
		.signIn(email, password)
		.dismissCookieBar()
		.clickOnConfirmThisBooking()
		.clickOnPayOnArrivalAndAcceptAlert()
		.getInvoiceStatus();
	Assert.assertEquals(invoiceStatus, spreadSheet.getCellData("Expected Invoice Status", 2));
	
	Assert.assertEquals(new PhpTravels_UserAccount_Page(driver).navigateAccountPage().getBookingStatus(),
		spreadSheet.getCellData("Expected Profile Status", 2));

    }

    @AfterClass
    public void closingBrowser() {
	BrowserActions.closeAllOpenedBrowserWindows(driver);
    }
}
