package phptravels.tests;


import org.openqa.selenium.WebDriver;
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
import phptravels.gui.pages.SigninPage;
import phptravels.gui.pages.UserAccountLeftMenu;
import utils.BrowserActions;
import utils.BrowserFactory;
import utils.Helper;
import utils.JsonFileManager;

@Epic("PHPTRAVELS")
@Feature("User Management")
public class SigninTests {
    private WebDriver driver;
    
    private JsonFileManager testData;

    private SigninPage signinPage;
    private UserAccountLeftMenu userAccountLeftMenu;
    
    private PhptravelsApis apis;

    private String currentTime = Helper.getCurrentTime();

    //////////////////////////////////////////////////////
    /////////////////// Test Cases //////////////////////

    @Test(description = "PHPTRAVELS - Valid User Signin")
    @Description("When I Signin with an already signed up user, Then I should Signin successfully")
    @Story("Signin")
    @Severity(SeverityLevel.CRITICAL)
    @TmsLink("Test_case")
    @Issue("Software_bug")
    public void testingValidUserSignin() {
	String email = testData.getTestData("user.email") + currentTime + "@" + testData.getTestData("user.emailDomain");
	
	apis			.userSignUp(testData.getTestData("user.firstName"), testData.getTestData("user.lastName"), testData.getTestData("user.phoneNumber"), email, testData.getTestData("user.password"), testData.getTestData("user.accountType"));
	
	signinPage		.navigateToSigninPage();
	signinPage		.userLogin(email, testData.getTestData("user.password"));
	userAccountLeftMenu	.assertOnUserProfileName(testData.getTestData("user.firstName"));
    }
    
    @Test(description = "PHPTRAVELS - Invalid User Signin")
    @Description("When I enter a not signed up user, Then I should get an error message ")
    @Story("Signin")
    @Severity(SeverityLevel.CRITICAL)
    @TmsLink("Test_case")
    @Issue("Software_bug")
    public void testingInvalidUserSignin() {
	String email = testData.getTestData("user.notRegisteredEmail") + currentTime + "@" + testData.getTestData("user.emailDomain");

	signinPage	.navigateToSigninPage();
	signinPage	.userLogin(email, testData.getTestData("user.password"));
	signinPage	.assertOnFormMessage(testData.getTestData("messages.wrongCredentials"));
    }

    //////////////////////////////////////////////////////
    ///////////////// Configurations ////////////////////
    @BeforeClass
    public void classSetup() {
	testData = new JsonFileManager("src/test/resources/TestData/phptravelsTestData.json");
    }
    
    @BeforeMethod
    public void methodSetup() {	
	driver = BrowserFactory.getBrowser();
	
	signinPage = new SigninPage(driver);
	userAccountLeftMenu = new UserAccountLeftMenu(driver);
	
	apis = new PhptravelsApis();
	
    }

    @AfterMethod
    public void methodTearDown() {
	BrowserActions.closeAllOpenedBrowserWindows(driver);
    }
}
