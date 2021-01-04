package liveproject.phptravels.apis;

import java.util.HashMap;
import java.util.Map;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import utils.ApiActions;

public class PhpTravels_APIs {
    ApiActions api = new ApiActions();

    private String account_endpoint = "account/";
    private String login_endpoint = "account/login";
    private String signup_endpoint = "account/signup";
    private String hotelsdetails_endpoint = "hotels/detail";
    private String boats_endpoint = "boats";
    private String processbookinglogged_endpoint = "admin/ajaxcalls/processBookinglogged";
//    private String invoice_updatepayonarrival_endpoint = "invoice/updatePayOnArrival";

    @Step("User Sign up with Data --> First Name: [{firstName}], Last Name: [{lastName}], Mobile Number: [{mobileNumber}], Email: [{email}] and Password: [{password}]")
    public Response userSignUp(String firstName, String lastName, String mobileNumber, String email, String password) {
	Map<String, Object> formParams = new HashMap<String, Object>();
	formParams.put("firstname", firstName);
	formParams.put("lastname", lastName);
	formParams.put("phone", mobileNumber);
	formParams.put("email", email);
	formParams.put("password", password);
	formParams.put("confirmpassword", password);

	return api.performPostRequest_withFormParams(signup_endpoint, formParams);

    }

    @Step("User Login with Data --> Email: [{email}] and Password: [{password}]")
    public Response userLogin(String email, String password) {
	Map<String, Object> formParams = new HashMap<String, Object>();
	formParams.put("username", email);
	formParams.put("password", password);
	
	Map<String, Object> headers = new HashMap<String, Object>();
	headers.put("x-requested-with", "XMLHttpRequest");

	return api.performPostRequest_withFormParamsAndHeaders(login_endpoint, formParams, headers);

    }

    @Step("Get User Account")
    public Response userAccount(Map<String, String> cookies) {
	return api.performGetRequest_withCookies(account_endpoint, cookies);

    }

    @Step("Get Hotel Details with Data --> City Name: [{cityName}], Hotel Name: [{hotelName}], Check In Date: [{checkInDate}], Check Out Date: [{checkOutDate}], Adults Count: [{adultsCount}], Child Count: [{childCount}]")
    public Response hotelsSearch(String cityName, String hotelName, String checkInDate, String checkOutDate,
	    String adultsCount, String childCount) {
	return api.performPostRequest(hotelsdetails_endpoint + "/" + cityName + "/" + hotelName + "/" + checkInDate
		+ "/" + checkOutDate + "/" + adultsCount + "/" + childCount);
    }

    @Step("Get Boat Details with Data --> Country Name: [{countryName}], City Name: [{cityName}], Boat Name: [{boatName}], Boat Date: [{boatDate}] and Adults Count: [{adultsCount}]")
    public Response boatsSearch(String countryName, String cityName, String boatName, String boatDate,
	    String adultsCount) {
	Map<String, Object> queryParams = new HashMap<String, Object>();
	queryParams.put("date", boatDate);
	queryParams.put("adults", adultsCount);
	return api.performPostRequest_withQueryParams(
		boats_endpoint + "/" + countryName + "/" + cityName + "/" + boatName, queryParams);
    }

    @Step("Process Booking with Logged User")
    public Response processBookingLogged(Map<String, String> cookies, String additionalNotes, String itemId,
	    String adultsCount, String childrenCount, String bookingType, String checkInDate, String checkOutDate) {
	Map<String, Object> formParams = new HashMap<String, Object>();
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

	return api.performPostRequest_withFormParamsAndCookies(processbookinglogged_endpoint, cookies, formParams);
    }

//    @Step("Pay on Arrival")
//    public Response updatePayOnArrival(Map<String, String> cookies) {
//	Map<String, Object> formParams = new HashMap<String, Object>();
//	formParams.put("id", "55");
//	formParams.put("module", "boats");
//
//	return api.performPostRequest_withFormParamsAndCookies(invoice_updatepayonarrival_endpoint, cookies, formParams);
//    }

}
