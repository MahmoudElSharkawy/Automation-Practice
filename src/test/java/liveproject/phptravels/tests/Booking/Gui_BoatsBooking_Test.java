package liveproject.phptravels.tests.Booking;

import java.io.File;
import java.util.Date;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
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
import liveproject.phptravels.apis.PhpTravels_APIs;
import liveproject.phptravels.gui.pages.PhpTravels_BoatsDetails_Page;
import liveproject.phptravels.gui.pages.PhpTravels_UserAccount_Page;
import utils.Logger;
import utils.BrowserActions;
import utils.BrowserFactory;
import utils.PropertiesReader;
import utils.ExcelFileManager;
import utils.BrowserFactory.BrowserType;
import utils.BrowserFactory.ExecutionType;

@Epic("PHPTRAVELS")
@Feature("GUI")
public class Gui_BoatsBooking_Test {
    WebDriver driver;
    ExcelFileManager spreadSheet;
    PhpTravels_APIs apis;
    String phptravelsBaseUrl = PropertiesReader.getProperty("liveproject.properties", "phptravels.baseuri");

    Date date = new Date();
    String firstName, lastName, mobileNumber, email, password;
    String currentTime = date.getTime() + "";

    @BeforeClass
    public void setUp() {
	spreadSheet = new ExcelFileManager(
		new File("src/test/resources/TestData/LiveProject_PhpTravels_BoatsBooking_TestData.xlsx"));
	spreadSheet.switchToSheet("GUI");
	apis = new PhpTravels_APIs();
	driver = BrowserFactory.openBrowser(BrowserType.FROM_PROPERTIES, ExecutionType.FROM_PROPERTIES);
	BrowserActions.navigateToUrl(driver, phptravelsBaseUrl
		+ "/boats/sri-lanka/colombo/Speedboat-Bravo-410---2016-refit-2016-?date=01/01/2025&adults=2");
    }

    @Test(description = "Validating the booking function of the Boats - PAY ON ARRIVAL payment method")
    @Description("When I book a boat, And confirm the booking request with PAY ON ARRIVAL payment method, Then the boat should be Reserved on my profile")
    @Story("Booking")
    @Severity(SeverityLevel.CRITICAL)
    @TmsLink("focus-case-1637103")
    @Issue("bug-tracker#1")
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
	
	BrowserActions.navigateToUrl(driver, phptravelsBaseUrl + "/account");
	String profileBookingStatus = new PhpTravels_UserAccount_Page(driver)
		.getBookingStatus();
	Assert.assertEquals(profileBookingStatus, spreadSheet.getCellData("Expected Profile Status", 2));

    }

    @AfterMethod
    public void AfterMethod(ITestResult result) {
	if (result.getStatus() == ITestResult.FAILURE) {
	    Logger.attachScreenshotInCaseOfFailure(driver);
	}
    }

    @AfterClass
    public void closingBrowser() {
	BrowserActions.closeAllOpenedBrowserWindows(driver);
    }
}
