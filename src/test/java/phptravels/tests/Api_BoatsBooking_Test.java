package phptravels.tests;

import java.io.File;
import java.util.Map;

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
import utils.ApiActions;
import utils.ExcelFileManager;
import utils.Helper;

@Epic("PHPTRAVELS")
@Feature("API")
public class Api_BoatsBooking_Test {
    private ApiActions apiObject;
    private PhptravelsApis phptravelsApis;
    private ExcelFileManager spreadSheet;

    private String firstName, lastName, mobileNumber, email, password;
    private String currentTime = Helper.getCurrentTime("yyyyMMddhhmmss");

    @BeforeClass
    public void beforeClass() {
	spreadSheet = new ExcelFileManager(
		new File("src/test/resources/TestData/LiveProject_PhpTravels_BoatsBooking_TestData.xlsx"));
	spreadSheet.switchToSheet("API");
	
	apiObject = new ApiActions(PhptravelsApis.BASE_URL);
	phptravelsApis = new PhptravelsApis(apiObject);
    }

    @Test(description = "PHPTRAVELS - API - Validating the booking function of the Boats without applying any payment method")
    @Description("When I book a boat without confirming any payment methods, Then the boat booking should has Unpaid status on my accpunt profile")
    @Story("Booking")
    @Severity(SeverityLevel.CRITICAL)
    @TmsLink("Test_case")
    @Issue("Software_bug")
    public void testingBoatsBooking_noPaymentMethod() {
	firstName = spreadSheet.getCellData("FirstName", 2);
	lastName = spreadSheet.getCellData("LastName", 2);
	mobileNumber = spreadSheet.getCellData("Mobile Number", 2);
	email = spreadSheet.getCellData("Email", 2) + currentTime + "@test.com";
	password = spreadSheet.getCellData("Password", 2);
	Response signUp = phptravelsApis.userSignUp(firstName, lastName, mobileNumber, email, password);

	Map<String, String> cookies = signUp.getCookies();
	phptravelsApis.processBookingLogged(cookies, "", spreadSheet.getCellData("Item ID", 2),
		spreadSheet.getCellData("Adults Count", 2), spreadSheet.getCellData("CheckIn Date", 2), "boats", "",
		"");
	Response account = phptravelsApis.getUserAccount(cookies);
	Assert.assertTrue(account.getBody().asString().contains(spreadSheet.getCellData("Expected Profile Status", 2)),
		"No/Wrong Booking Status!; The Account response doesn't contain the expected booking status: " + "["
			+ spreadSheet.getCellData("Expected Profile Status", 2) + "]");

    }

}
