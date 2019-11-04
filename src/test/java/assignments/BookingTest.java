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
import assignment.booking.pages.DestinationResultsPage;
import assignment.booking.pages.HomePage;
import assignment.booking.pages.RegistrationPage;

public class BookingTest {
    private WebDriver driver;
    Date date;
    FileDataReader dataReader;

    String email;
    String password;
    String checkInDate;
    String checkOutDate;

    HomePage homePage;
    RegistrationPage registrationPage;
    DestinationResultsPage destinationPage;

    @BeforeClass
    public void setUp() {
	date = new Date();
	dataReader = new FileDataReader();

	driver = BrowserFactory.browser(BrowserType.GOOGLE_CHROME);
	driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	homePage = new HomePage(driver);
	registrationPage = new RegistrationPage(driver);
	destinationPage = new DestinationResultsPage(driver);

    }

    @Test
    public void TestBooking() {
	email = dataReader.read("BookingTestData.xlsx", 2, 2) + date.getTime() + "@test.com";
	password = dataReader.read("BookingTestData.xlsx", 3, 2);

	homePage.openURL();
	homePage.clickOnTheRegistrationButton();

	registrationPage.Register(email, password);

	homePage.clickOnCloseButton();
	homePage.searchDestination(dataReader.read("BookingTestData.xlsx", 4, 2), dataReader.read("BookingTestData.xlsx", 5, 2),
		dataReader.read("BookingTestData.xlsx", 6, 2), Integer.parseInt(dataReader.read("BookingTestData.xlsx", 7, 2)));

	destinationPage.assertHotelIsListedInTheResultSet(dataReader.read("BookingTestData.xlsx", 8, 2));
	destinationPage.clickOnSeeAvailability(dataReader.read("BookingTestData.xlsx", 8, 2));

	
	
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
