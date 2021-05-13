package phptravels.apis;

import java.util.HashMap;
import java.util.Map;

import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import utils.ApiActions;
import utils.ExtentReport;
import utils.PropertiesReader;
import utils.ApiActions.RequestType;

public class PhpTravels_APIs {
    ApiActions api = new ApiActions();
    String baseURI = PropertiesReader.getProperty("automationPractice.properties", "phptravels.baseuri");
    
    // Expected status codes
    private static final int successStatusCode = 200;

    // End Points
    private String account_endpoint = "account/";
    private String login_endpoint = "account/login";
    private String signup_endpoint = "account/signup";
    private String hotelsdetails_endpoint = "hotels/detail";
    private String boats_endpoint = "boats";
    private String processbookinglogged_endpoint = "admin/ajaxcalls/processBookinglogged";

    @Step("User Sign up with Data --> First Name: [{firstName}], Last Name: [{lastName}], Mobile Number: [{mobileNumber}], Email: [{email}] and Password: [{password}]")
    public Response userSignUp(String firstName, String lastName, String mobileNumber, String email, String password) {
	ExtentReport.info(MarkupHelper.createLabel("User Sign up", ExtentColor.BLUE));

	Map<String, Object> formParams = new HashMap<>();
	formParams.put("firstname", firstName);
	formParams.put("lastname", lastName);
	formParams.put("phone", mobileNumber);
	formParams.put("email", email);
	formParams.put("password", password);
	formParams.put("confirmpassword", password);

	return api.performRequest(RequestType.POST, baseURI + signup_endpoint, successStatusCode, null, null,
		formParams, null, null, null);

    }

    @Step("User Login with Data --> Email: [{email}] and Password: [{password}]")
    public Response userLogin(String email, String password) {
	ExtentReport.info(MarkupHelper.createLabel("User Login", ExtentColor.BLUE));

	Map<String, Object> headers = new HashMap<>();
	headers.put("x-requested-with", "XMLHttpRequest");
	
	Map<String, Object> formParams = new HashMap<>();
	formParams.put("username", email);
	formParams.put("password", password);

	return api.performRequest(RequestType.POST, baseURI + login_endpoint, successStatusCode, headers, null,
		formParams, null, null, null);

    }

    @Step("Get User Account")
    public Response getUserAccount(Map<String, String> cookies) {
	ExtentReport.info(MarkupHelper.createLabel("Get User Account", ExtentColor.BLUE));

	return api.performRequest(RequestType.GET, baseURI + account_endpoint, successStatusCode, null, null, null,
		null, null, cookies);

    }

    @Step("Get Hotel Details with Data --> City Name: [{cityName}], Hotel Name: [{hotelName}], Check In Date: [{checkInDate}], Check Out Date: [{checkOutDate}], Adults Count: [{adultsCount}], Child Count: [{childCount}]")
    public Response hotelsSearch(String cityName, String hotelName, String checkInDate, String checkOutDate,
	    String adultsCount, String childCount) {
	ExtentReport.info(MarkupHelper.createLabel("Get Hotel Details", ExtentColor.BLUE));

	return api.performRequest(RequestType.POST,
		baseURI + hotelsdetails_endpoint + "/" + cityName + "/" + hotelName + "/" + checkInDate + "/"
			+ checkOutDate + "/" + adultsCount + "/" + childCount,
		successStatusCode, null, null, null, null, null, null);

    }

    @Step("Get Boat Details with Data --> Country Name: [{countryName}], City Name: [{cityName}], Boat Name: [{boatName}], Boat Date: [{boatDate}] and Adults Count: [{adultsCount}]")
    public Response boatsSearch(String countryName, String cityName, String boatName, String boatDate,
	    String adultsCount) {
	ExtentReport.info(MarkupHelper.createLabel("Get Boat Details", ExtentColor.BLUE));

	Map<String, Object> queryParams = new HashMap<>();
	queryParams.put("date", boatDate);
	queryParams.put("adults", adultsCount);
	
	return api.performRequest(RequestType.POST,
		baseURI + boats_endpoint + "/" + countryName + "/" + cityName + "/" + boatName, successStatusCode, null,
		null, null, queryParams, null, null);

    }

    @Step("Process Booking with Logged-in User")
    public Response processBookingLogged(Map<String, String> cookies, String additionalNotes, String itemId,
	    String adultsCount, String childrenCount, String bookingType, String checkInDate, String checkOutDate) {
	ExtentReport.info(MarkupHelper.createLabel("Process Booking with Logged-in User", ExtentColor.BLUE));

	Map<String, Object> formParams = new HashMap<>();
	formParams.put("additionalnotes", additionalNotes);
	formParams.put("itemid", itemId);
	formParams.put("checkout", checkOutDate);
	formParams.put("adults", adultsCount);
	formParams.put("couponid", "");
	formParams.put("btype", bookingType);
	formParams.put("tourType", "");
	formParams.put("subitemid", itemId);
	formParams.put("children", childrenCount);
	formParams.put("checkin", checkInDate);
	formParams.put("infant", "0");
	formParams.put("passport[1][name]", "");
	formParams.put("passport[1][passportnumber]", "");
	formParams.put("passport[1][age]", "");
	formParams.put("passport[2][name]", "");
	formParams.put("passport[2][passportnumber]", "");
	formParams.put("passport[2][age]", "");

	return api.performRequest(RequestType.POST, baseURI + processbookinglogged_endpoint, successStatusCode, null,
		null, formParams, null, null, cookies);

    }

}
