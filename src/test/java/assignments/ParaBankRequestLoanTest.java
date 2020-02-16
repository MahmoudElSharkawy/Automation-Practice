package assignments;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import assignments.parabank.pages.GooglePage;
import assignments.parabank.pages.ParaBankHomePage;
import assignments.parabank.pages.ParaBankLoanRequestPage;
import assignments.parabank.pages.ParaBankRegistrationPage;
import assignments.parabank.pages.ParaBankSkeleton;
import examples.datadriven.FileDataReader;
import io.github.bonigarcia.wdm.WebDriverManager;

public class ParaBankRequestLoanTest {
	private WebDriver driver;
	Date date = new Date();

	GooglePage googlePage;
	ParaBankHomePage paraBankHomePage;
	ParaBankRegistrationPage paraBankRegistrationPage;
	ParaBankSkeleton paraBankSkeleton;
	ParaBankLoanRequestPage paraBankLoanRequestPage;

	@BeforeClass
	public void setUp() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

		googlePage = new GooglePage(driver);
		paraBankHomePage = new ParaBankHomePage(driver);
		paraBankRegistrationPage = new ParaBankRegistrationPage(driver);
		paraBankSkeleton = new ParaBankSkeleton(driver);
		paraBankLoanRequestPage = new ParaBankLoanRequestPage(driver);
	}

	@Test
	public void requestLoanTest() {
		googlePage.openURL();
		googlePage.googleSearch(FileDataReader.readFromExcel("ParaBankTestData", 2));
		googlePage.clickOnParabankSearchResult();

		paraBankHomePage.clickOnRegistrationLink();
		String userName = FileDataReader.readFromExcel("ParaBankTestData", 11) + date.getTime();
		paraBankRegistrationPage.userRegistration(
				FileDataReader.readFromExcel("ParaBankTestData", 3),
				FileDataReader.readFromExcel("ParaBankTestData", 4),
				FileDataReader.readFromExcel("ParaBankTestData", 5),
				FileDataReader.readFromExcel("ParaBankTestData", 6),
				FileDataReader.readFromExcel("ParaBankTestData", 7),
				FileDataReader.readFromExcel("ParaBankTestData", 8),
				FileDataReader.readFromExcel("ParaBankTestData", 9),
				FileDataReader.readFromExcel("ParaBankTestData", 10),
				userName,
				FileDataReader.readFromExcel("ParaBankTestData", 12));
		Assert.assertEquals(paraBankRegistrationPage.getWelcomeMessageText(),
				"Welcome " + userName);
		
		paraBankSkeleton.clickOnRequestLoanLink();
		paraBankLoanRequestPage.requestLoan(FileDataReader.readFromExcel("ParaBankTestData", 13),
				FileDataReader.readFromExcel("ParaBankTestData", 14));
		Assert.assertTrue(paraBankLoanRequestPage.getApprovalMessageText().contains("approved"));
		
		paraBankSkeleton.clickOnHomeLink();
	}

	@AfterClass
	public void tearDown() {
		driver.quit();
	}
}
