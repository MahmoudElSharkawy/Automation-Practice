package liveproject.phptravels.tests.gui.SignUp;

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
import io.qameta.allure.TmsLink;
import liveproject.phptravels.gui.pages.PhpTravels_Home_Page;
import utils.Logger;
import utils.BrowserFactory;
import utils.PropertiesReader;
import utils.Spreadsheet;
import utils.BrowserFactory.BrowserType;

@Epic("Live Project")
@Feature("PHPTravels Sign Up")
public class PhpTravels_SignUp_Test {
    WebDriver driver;
    Spreadsheet spreadSheet;
    String phptravelsHomePageURL = PropertiesReader.getProperty("liveproject.properties", "phptravels.home.url");
    Date date;

    @BeforeClass
    public void setUp() {
	date = new Date();
	spreadSheet = new Spreadsheet(new File("src/test/resources/TestData/LiveProject_PhpTravels_SignUp_TestData.xlsx"));
	spreadSheet.switchToSheet("testsheet2");
	driver = BrowserFactory.openRemoteBrowser(BrowserType.FROM_PROPERTIES);
	driver.get(phptravelsHomePageURL);
    }

    @Test(description = "Valid User Sign Up")
    @Description("When I enter valid data in the sign up form And click the signup button, Then I should be registered successfully And be navigated to the user account page And I can see my user data and Hi message")
    @Severity(SeverityLevel.CRITICAL)
    @TmsLink("focus-case-1539798")
    @Issue("bug-tracker#1")
    public void testingValidUserSignUp() {
	String firstName = spreadSheet.getCellData("FirstName", 1);
	String lastName = spreadSheet.getCellData("LastName", 1);
	String phone = spreadSheet.getCellData("Phone", 1);
	String email = spreadSheet.getCellData("Email", 1) + date.getTime() + "@test.com";
	Logger.logMessage("The mail is: " + email);
	String password = spreadSheet.getCellData("Password", 1);

	String hiMessage = new PhpTravels_Home_Page(driver)
		.navigateToSignUpPage()
		.userSignUp(firstName, lastName, phone, email, password)
		.getHiMessage();
	Assert.assertEquals(hiMessage,  "Hi, " + firstName + " " + lastName);
    }

    @AfterMethod
    public void AfterMethod(ITestResult result) {
	if (result.getStatus() == ITestResult.FAILURE) {
	    Logger.logMessage("The Test Case Failed!; Taking Screenshot....");
	    Logger.logScreenshot(driver);
	}
    }

    @AfterClass
    public void closingBrowser() {
	driver.quit();
    }
}
