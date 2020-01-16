package assignments;

import java.io.File;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import generic.BrowserFactory;
import examples.datadriven.FileDataReader;
import generic.BrowserFactory.BrowserType;
import assignment.booking.pages.DestinationPage;
import assignment.booking.pages.DestinationResultsPage;
import assignment.booking.pages.HomePage;
import assignment.booking.pages.RegistrationPage;

public class BookingTest {
    private WebDriver driver;
    Date date;

    String email;
    String password;
    String checkInDate;
    String checkOutDate;

    HomePage homePage;
    RegistrationPage registrationPage;
    DestinationResultsPage resultsPage;
    DestinationPage destinationPage;

    @BeforeClass
    public void setUp() {
	date = new Date();

	driver = BrowserFactory.browser(BrowserType.GOOGLE_CHROME);
	driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	homePage = new HomePage(driver);
	registrationPage = new RegistrationPage(driver);
	resultsPage = new DestinationResultsPage(driver);
	destinationPage = new DestinationPage(driver);

    }

    @Test
    public void TestBooking() {
	email = FileDataReader.readFromExcel("BookingTestData", 2) + date.getTime() + "@test.com";
	password = FileDataReader.readFromExcel("BookingTestData", 3);

	homePage.openURL();
	homePage.clickOnTheRegistrationButton();

	registrationPage.Register(email, password);

	homePage.clickOnCloseButton();
	homePage.searchDestination(FileDataReader.readFromExcel("BookingTestData", 4),
		FileDataReader.readFromExcel("BookingTestData", 5), FileDataReader.readFromExcel("BookingTestData.xlsx", 6),
		Integer.parseInt(FileDataReader.readFromExcel("BookingTestData", 7)));

	resultsPage.assertHotelIsListedInTheResultSet(FileDataReader.readFromExcel("BookingTestData", 8));
	resultsPage.clickOnSeeAvailabilityAndSwitchToDestinationPage(FileDataReader.readFromExcel("BookingTestData", 8));

    }

    @AfterMethod
    public void takeScreenShot(ITestResult result) {
	if (result.getStatus() == ITestResult.FAILURE) {
	    TakesScreenshot ts = (TakesScreenshot) driver;
	    File source = ts.getScreenshotAs(OutputType.FILE);
	    try {
		FileUtils.copyFile(source, new File("src/test/resources/ScreenShots/" + result.getName() + ".png"));
	    } catch (Exception e) {
		System.out.println(e.getMessage());
	    }
	}
    }

    @AfterClass
    public void quitWebDriver() {
	driver.quit();
    }
}
