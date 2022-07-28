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
import phptravels.gui.pages.SigninPage;
import phptravels.gui.pages.SignupPage;
import phptravels.gui.pages.UserAccountLeftMenu;
import utils.BrowserActions;
import utils.BrowserFactory;
import utils.Helper;
import utils.JsonFileManager;

@Epic("PHPTRAVELS")
@Feature("User Management")
public class SignupTests {
    private WebDriver driver;
    
    JsonFileManager testData;

    private SignupPage signupPage;
    private SigninPage signinPage;
    private UserAccountLeftMenu userAccountLeftMenu;

    private String currentTime = Helper.getCurrentTime();

    //////////////////////////////////////////////////////
    /////////////////// Test Cases //////////////////////

    @Test(description = "PHPTRAVELS - Valid User Signup")
    @Description("When I enter valid data in the sign up form And click the signup button, Then I should be registered successfully And be navigated to the user account page And I can see my user data and Hi message")
    @Story("Signup")
    @Severity(SeverityLevel.BLOCKER)
    @TmsLink("Test_case")
    public void testingValidUserSignUp() {
	String email = testData.getTestData("user.email") + currentTime + "@" + testData.getTestData("user.emailDomain");
	
	signupPage		.userSignUp(testData.getTestData("user.firstName"), testData.getTestData("user.lastName"), testData.getTestData("user.phoneNumber"), email, testData.getTestData("user.password"), testData.getTestData("user.accountType"));
	signinPage		.assertOnFormMessage(testData.getTestData("messages.successfulSignup"));
	
	signinPage		.userLogin(email, testData.getTestData("user.password"));
	userAccountLeftMenu	.assertOnUserProfileName(testData.getTestData("user.firstName"));
    }
    
    @Test(description = "PHPTRAVELS - Invalid User Signup - Email Already Exists", dependsOnMethods = { "testingValidUserSignUp" })
    @Description("Given i already signed up with an email, When I use the same email for new sign up , Then I should get an error message ")
    @Story("Signup")
    @Severity(SeverityLevel.CRITICAL)
    @TmsLink("Test_case")
    public void testingInvalidUserSignUp_emailAlreadyExists() {
	String email = testData.getTestData("user.email") + currentTime + "@" + testData.getTestData("user.emailDomain");
	
	signupPage	.userSignUp(testData.getTestData("user.firstName"), testData.getTestData("user.lastName"), testData.getTestData("user.phoneNumber"), email, testData.getTestData("user.password"), testData.getTestData("user.accountType"));
	signupPage	.assertOnFormMessage(testData.getTestData("messages.emailExists"));
    }
    
    @Test(description = "PHPTRAVELS - Invalid User Sign Up - Wrong Email Format")
    @Description("When I use a wrong email format on the sign up , Then I should get an error message ")
    @Story("Signup")
    @Severity(SeverityLevel.NORMAL)
    @TmsLink("Test_case")
    @Issue("Software_bug")
    public void testingInvalidUserSignUp_emailWrongFormat() {	
	signupPage	.userSignUp(testData.getTestData("user.firstName"), testData.getTestData("user.lastName"), testData.getTestData("user.phoneNumber"), testData.getTestData("user.email") + currentTime, testData.getTestData("user.password"), testData.getTestData("user.accountType"));
	signupPage	.assertOnFormMessage(testData.getTestData("messages.emailWrongFormat"));	
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
	
	signupPage = new SignupPage(driver);
	signinPage = new SigninPage(driver);
	userAccountLeftMenu = new UserAccountLeftMenu(driver);
	
	signupPage.navigateToSignupPage();
    }

    @AfterMethod
    public void methodTearDown() {
	BrowserActions.closeAllOpenedBrowserWindows(driver);
    }
}
