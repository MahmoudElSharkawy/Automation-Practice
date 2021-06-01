package phptravels.tests;

import java.io.File;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
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
import phptravels.apis.PhptravelsApis;
import phptravels.gui.pages.PhpTravels_Home_Page;
import utils.ApiActions;
import utils.BrowserActions;
import utils.BrowserFactory;
import utils.ExcelFileManager;
import utils.Helper;

@Epic("PHPTRAVELS")
@Feature("GUI")
public class Gui_Login_Test {
    private WebDriver driver;
    private ApiActions apiObject;
    private PhptravelsApis phptravelsApis;
    private ExcelFileManager spreadSheet;
    
    private String firstName, lastName, mobileNumber, email, password;
    private String currentTime = Helper.getCurrentTime("ddMMyyyyHHmmssSSS");

    @BeforeClass
    public void setUp() {
	spreadSheet = new ExcelFileManager(
		new File("src/test/resources/TestData/LiveProject_PhpTravels_Login_TestData.xlsx"));
	spreadSheet.switchToSheet("GUI");

	apiObject = new ApiActions(PhptravelsApis.BASE_URL);
	phptravelsApis = new PhptravelsApis(apiObject);
    }

    @BeforeMethod
    public void beforeMethod() {
	driver = BrowserFactory.getBrowser();
	new PhpTravels_Home_Page(driver).navigateToHomePage();
    }

    @Test(description = "PHPTRAVELS - GUI - Valid User Login")
    @Description("When I login with an already signed up user, Then I should login successfully")
    @Story("Login")
    @Severity(SeverityLevel.CRITICAL)
    @TmsLink("Test_case")
    @Issue("Software_bug")
    public void testingValidUserLogin() {
	firstName = spreadSheet.getCellData("FirstName", 2);
	lastName = spreadSheet.getCellData("LastName", 2);
	mobileNumber = spreadSheet.getCellData("Mobile Number", 2);
	email = spreadSheet.getCellData("Email", 2) + currentTime + "@test.com";
	password = spreadSheet.getCellData("Password", 2);
	//sign up using api
	phptravelsApis.userSignUp(firstName, lastName, mobileNumber, email, password);
	
	String hiMessage = new PhpTravels_Home_Page(driver)
		.dismissCookieBar()
		.navigateToLoginPage()
		.userLogin(email, password)
		.getHiMessage();
	Assert.assertEquals(hiMessage, "Hi, " + firstName + " " + lastName, "No/Wrong Hi Message Names!;");
    }
    
    @Test(description = "PHPTRAVELS - GUI - Invalid User Login")
    @Description("When I enter a not signed up user , Then I should get an error message ")
    @Story("Login")
    @Severity(SeverityLevel.CRITICAL)
    @TmsLink("Test_case")
    @Issue("Software_bug")
    public void testingInvalidUserLogin() {
	email = spreadSheet.getCellData("Email", 3) + "@test.com";
	password = spreadSheet.getCellData("Password", 3);

	String alertMessage = new PhpTravels_Home_Page(driver)
		.dismissCookieBar()
		.navigateToLoginPage()
		.invalidUserLogin(email, password)
		.getAlertMessage();
	Assert.assertEquals(alertMessage, spreadSheet.getCellData("Expected Alert Message", 3),
		"No/Wrong Alert Message!;");
    }

    @AfterMethod
    public void afterMethod() {
	BrowserActions.closeAllOpenedBrowserWindows(driver);
    }
}
