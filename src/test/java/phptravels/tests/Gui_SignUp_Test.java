package phptravels.tests;

import java.io.File;
import java.util.Date;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
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
public class Gui_SignUp_Test {
    private WebDriver driver;
    private ExcelFileManager spreadSheet;

    private String firstName, lastName, mobileNumber, email, password;
    private String currentTime = new Date().getTime() + "";

    @BeforeClass
    public void setUp() {
	spreadSheet = new ExcelFileManager(
		new File("src/test/resources/TestData/LiveProject_PhpTravels_SignUp_TestData.xlsx"));
	spreadSheet.switchToSheet("GUI");
    }

    @BeforeMethod
    public void beforeMethod() {
	driver = BrowserFactory.getBrowser();
	new PhpTravels_Home_Page(driver).navigateToHomePage();
    }

    @Test(description = "PHPTRAVELS - GUI - Valid User Sign Up")
    @Description("When I enter valid data in the sign up form And click the signup button, Then I should be registered successfully And be navigated to the user account page And I can see my user data and Hi message")
    @Story("Sign Up")
    @Severity(SeverityLevel.CRITICAL)
    @TmsLink("Test_case")
    @Issue("Software_bug")
    public void testingValidUserSignUp() {
	firstName = spreadSheet.getCellData("FirstName", 2);
	lastName = spreadSheet.getCellData("LastName", 2);
	mobileNumber = spreadSheet.getCellData("Mobile Number", 2);
	email = spreadSheet.getCellData("Email", 2) + currentTime + "@test.com";
	password = spreadSheet.getCellData("Password", 2);

	String hiMessage = new PhpTravels_Home_Page(driver)
		.dismissCookieBar()
		.navigateToSignUpPage()
		.userSignUp(firstName, lastName, mobileNumber, email, password)
		.getHiMessage();
	Assert.assertEquals(hiMessage, "Hi, " + firstName + " " + lastName, "Wrong Hi Message Names!");
    }

    @Test(description = "PHPTRAVELS - GUI - Invalid User Sign Up - Email Already Exists", dependsOnMethods = { "testingValidUserSignUp" })
    @Description("Given i already signed up with an email, When I use the same email for new sign up , Then I should get an error message ")
    @Story("Sign Up")
    @Severity(SeverityLevel.CRITICAL)
    @TmsLink("Test_case")
    @Issue("Software_bug")
    public void testingInvalidUserSignUp_emailAlreadyExists() {
	firstName = spreadSheet.getCellData("FirstName", 3);
	lastName = spreadSheet.getCellData("LastName", 3);
	mobileNumber = spreadSheet.getCellData("Mobile Number", 3);
	email = spreadSheet.getCellData("Email", 3) + currentTime + "@test.com";
	password = spreadSheet.getCellData("Password", 3);

	String alertMessage = new PhpTravels_Home_Page(driver)
		.dismissCookieBar()
		.navigateToSignUpPage()
		.invalidUserSignUp(firstName, lastName, mobileNumber, email, password)
		.getAlertMessage();
	Assert.assertEquals(alertMessage, spreadSheet.getCellData("Expected Alert Message", 3),
		"No/Wrong Alert Message!;");
    }

    @Test(description = "PHPTRAVELS - GUI - Invalid User Sign Up - Wrong Email Format")
    @Description("When I use a wrong email format on the sign up , Then I should get an error message ")
    @Story("Sign Up")
    @Severity(SeverityLevel.CRITICAL)
    @TmsLink("Test_case")
    @Issue("Software_bug")
    public void testingInvalidUserSignUp_emailWrongFormat() {
	firstName = spreadSheet.getCellData("FirstName", 4);
	lastName = spreadSheet.getCellData("LastName", 4);
	mobileNumber = spreadSheet.getCellData("Mobile Number", 4);
	email = spreadSheet.getCellData("Email", 4) + currentTime;
	password = spreadSheet.getCellData("Password", 4);

	String alertMessage = new PhpTravels_Home_Page(driver)
		.dismissCookieBar()
		.navigateToSignUpPage()
		.invalidUserSignUp(firstName, lastName, mobileNumber, email, password)
		.getAlertMessage();
	Assert.assertEquals(alertMessage, spreadSheet.getCellData("Expected Alert Message", 4),
		"No/Wrong Alert Message!;");
    }

    @AfterMethod
    public void afterMethod(ITestResult result) {
	BrowserActions.closeAllOpenedBrowserWindows(driver);
    }
}
