package restfulbooker.apis;

import java.util.HashMap;
import java.util.Map;

import org.json.simple.JSONObject;

import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;

import io.qameta.allure.Step;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import restfulbooker.apis.RestfulBookerApis.Status;
import utils.ApiActions;
import utils.ExtentReport;
import utils.ApiActions.RequestType;

public class RestfulBookerApisBooking {
    private ApiActions apiObject;
    private String accessToken;

    // Services Names
    private String booking_serviceName = "booking";

    // Constructor
    public RestfulBookerApisBooking(ApiActions apiObject, String accessToken) {
	this.apiObject = apiObject;
	this.accessToken = accessToken;
    }

    //////////////////////////////////////////////////////////////////
    //////////////////////////// Actions ////////////////////////////

    @Step("Get Booking Ids with --> First Name: [{firstName}] and Last Name: [{lastName}]")
    public Response getBookingIds(String firstName, String lastName) {
	ExtentReport.info(MarkupHelper.createLabel("Get Booking Ids", ExtentColor.BLUE));

	return apiObject.performRequest(RequestType.GET,
		booking_serviceName + "?firstname=" + firstName + "&lastname=" + lastName, Status.SUCCESS.getCode(),
		null, null, null, null, null, null);
    }

    @Step("Get Booking")
    public Response getBooking(String bookingId) {
	ExtentReport.info(MarkupHelper.createLabel("Get Booking", ExtentColor.BLUE));

	return apiObject.performRequest(RequestType.GET, booking_serviceName + "/" + bookingId,
		Status.SUCCESS.getCode(), null, null, null, null, null, null);
    }

    @Step("Create Booking")
    public Response createBooking(String firstName, String lastName, int totalPrice, boolean depositePaid,
	    String checkIn, String checkOut, String additionalNeeds) {
	ExtentReport.info(MarkupHelper.createLabel("Create Booking", ExtentColor.BLUE));

	return apiObject.performRequest(RequestType.POST, booking_serviceName, Status.SUCCESS.getCode(), null,
		ContentType.JSON, null, null,
		createBookingBody(firstName, lastName, totalPrice, depositePaid, checkIn, checkOut, additionalNeeds),
		null);
    }

    @Step("Delete Booking")
    public Response deleteBooking(String bookigId) {
	ExtentReport.info(MarkupHelper.createLabel("Delete Booking", ExtentColor.BLUE));

	Map<String, Object> headers = new HashMap<>();
	headers.put("Cookie", "token=" + accessToken);

	return apiObject.performRequest(RequestType.DELETE, booking_serviceName + "/" + bookigId,
		Status.SUCCESS_DELETE.getCode(), headers, null, null, null, null, null);
    }

    /////////////////////////////////////////////////////////////////////////////////////////////

    @SuppressWarnings("unchecked")
    private JSONObject createBookingBody(String firstName, String lastName, int totalPrice, boolean depositePaid,
	    String checkIn, String checkOut, String additionalNeeds) {
	JSONObject createBookingBody = new JSONObject();
	createBookingBody.put("firstname", firstName);
	createBookingBody.put("lastname", lastName);
	createBookingBody.put("totalprice", totalPrice);
	createBookingBody.put("depositpaid", depositePaid);
	JSONObject bookingDates = new JSONObject();
	bookingDates.put("checkin", checkIn);
	bookingDates.put("checkout", checkOut);
	createBookingBody.put("bookingdates", bookingDates);
	createBookingBody.put("additionalneeds", additionalNeeds);

	return createBookingBody;
    }
}
