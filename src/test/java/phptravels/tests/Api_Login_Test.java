package phptravels.tests;

import java.io.File;

import org.testng.Assert;
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
import io.restassured.response.Response;
import phptravels.apis.PhptravelsApis;
import utilities.ApiActions;
import utilities.ExcelFileManager;
import utilities.Helper;

@Epic("PHPTRAVELS")
@Feature("API")
public class Api_Login_Test {
    private ApiActions apiObject;
    private PhptravelsApis phptravelsApis;
    private ExcelFileManager spreadSheet;

    private String firstName, lastName, mobileNumber, email, password;
    private String currentTime = Helper.getCurrentTime("ddMMyyyyHHmmssSSS");

    @BeforeClass
    public void beforeClass() {
	spreadSheet = new ExcelFileManager(
		new File("src/test/resources/TestData/PhpTravels_Login_TestData.xlsx"));
	spreadSheet.switchToSheet("API");
	
	apiObject = new ApiActions(PhptravelsApis.BASE_URL);
	phptravelsApis = new PhptravelsApis(apiObject);
    }

    @Test(description = "PHPTRAVELS - API - Valid User Login")
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
	phptravelsApis.userSignUp(firstName, lastName, mobileNumber, email, password);

	phptravelsApis.userLogin(email, password);
//	Map<String, String> cookies = login.getCookies();
//	Response account = phptravelsApis.getUserAccount(cookies);
//	Assert.assertTrue(account.getBody().asString().contains("Hi, " + firstName + " " + lastName),
//		"No/Wrong Hi Message!; The Account response doesn't contain the expected message: " + "[Hi, "
//			+ firstName + " " + lastName + "]");

    }

    @Test(description = "PHPTRAVELS - API - Invalid User Login")
    @Description("When I enter a not signed up user , Then I should get an error message ")
    @Story("Login")
    @Severity(SeverityLevel.CRITICAL)
    @TmsLink("Test_case")
    @Issue("Software_bug")
    public void testingInvalidUserLogin() {
	email = spreadSheet.getCellData("Email", 3) + "@test.com";
	password = spreadSheet.getCellData("Password", 3);

	Response login = phptravelsApis.userLogin(email, password);
	Assert.assertTrue(login.getBody().asString().contains(spreadSheet.getCellData("Expected Alert Message", 3)),
		"No/Wrong Error Message!; The message should be: ["
			+ spreadSheet.getCellData("Expected Alert Message", 3) + "]");

    }
}
