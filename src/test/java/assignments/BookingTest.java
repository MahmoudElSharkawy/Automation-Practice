package assignments;

import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import examples.datadriven.FileDataReader;
import generic.BrowserFactory;
import generic.BrowserFactory.BrowserType;
import generic.Utils;
import assignments.booking.pages.HomePage;

public class BookingTest {
    private WebDriver driver;
    String testDataFileName = "BookingTestData";
        
    @BeforeClass
    public void setUp() {
	if (FileDataReader.readFromExcel(testDataFileName, 2).equalsIgnoreCase("chrome")
		|| FileDataReader.readFromExcel(testDataFileName, 2).equalsIgnoreCase("google chrome")) {
	    driver = BrowserFactory.browser(BrowserType.GOOGLE_CHROME);

	} else if (FileDataReader.readFromExcel(testDataFileName, 2).equalsIgnoreCase("firefox")
		|| FileDataReader.readFromExcel(testDataFileName, 2).equalsIgnoreCase("mozilla firefox")) {
	    driver = BrowserFactory.browser(BrowserType.MOZILLA_FIREFOX);
	}

	driver.manage().window().maximize();
    }

    @Test(priority = 1)
    public void riyadh_firstWeekAugust() {
	new HomePage(driver).
		navigateToURL().
		inputDestination(FileDataReader.readFromExcel(testDataFileName, 3)).
		inputAccommodationDates(FileDataReader.readFromExcel(testDataFileName, 6), FileDataReader.readFromExcel(testDataFileName, 7)).
		clickOnTheSearchButton().
		sortByReviewScore().
		printHotelsAlongsideRatings(5);
    }

    @Test(priority = 2)
    public void jeddah_secondWeekSeptember() {
	new HomePage(driver).
		navigateToURL().
		inputDestination(FileDataReader.readFromExcel(testDataFileName, 4)).
		clickOnCalenderNextButton(1).
		inputAccommodationDates(FileDataReader.readFromExcel(testDataFileName, 8), FileDataReader.readFromExcel(testDataFileName, 9)).
		clickOnTheSearchButton().
		sortByReviewScore().
		printHotelsAlongsideRatings(5);
    }

    @Test(priority = 3)
    public void makkah_thirdWeekDecember() {
	new HomePage(driver).
		navigateToURL().
		inputDestination(FileDataReader.readFromExcel(testDataFileName, 5)).
		clickOnCalenderNextButton(2).
		inputAccommodationDates(FileDataReader.readFromExcel(testDataFileName, 10), FileDataReader.readFromExcel(testDataFileName, 11)).
		clickOnTheSearchButton().
		sortByReviewScore().
		printHotelsAlongsideRatings(5);
    }

    @AfterMethod
    public void takeScreenShot_failure(ITestResult result) {
	Utils.takeScreenShotInCaseOfFailure(result, driver);
    }

    @AfterClass
    public void tearDown() {
	driver.quit();
    }
}
