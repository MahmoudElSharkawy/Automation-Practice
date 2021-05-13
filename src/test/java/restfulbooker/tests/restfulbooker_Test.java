package restfulbooker.tests;

import static org.testng.Assert.assertEquals;

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
import restfulbooker.apis.RestfulBookerApis;
import restfulbooker.apis.RestfulBookerApisBooking;
import utils.ApiActions;

@Epic("Restful-Booker")
@Feature("API")
public class restfulbooker_Test {
    private ApiActions apiObject;
    private RestfulBookerApis restfulBookerApis;
    private RestfulBookerApisBooking restfulBookerApisBooking;

    @BeforeClass
    public void beforeClass() {
	apiObject = new ApiActions();
	restfulBookerApis = new RestfulBookerApis(apiObject);
	String accessToken = restfulBookerApis.getAccessToken("admin", "password123");
	restfulBookerApisBooking = new RestfulBookerApisBooking(apiObject, accessToken);
    }

    @Test(description = "Restful-Booker - API - Create Booking")
    @Description("Creates a new booking in the API")
    @Story("Booking")
    @Severity(SeverityLevel.CRITICAL)
    @TmsLink("Test_case")
    @Issue("Software_bug")
    public void createBooking() {
	Response createBooking = restfulBookerApisBooking.createBooking("Mahmoud", "ElSharkawy", 2000, true,
		"2021-01-01", "2022-01-01", "Cream Caramel");
	String bookingId = createBooking.jsonPath().get("bookingid").toString();
	Response getBooking = restfulBookerApisBooking.getBooking(bookingId);
	assertEquals(getBooking.jsonPath().get("firstname").toString(), "Mahmoud");
	assertEquals(getBooking.jsonPath().get("lastname").toString(), "ElSharkawy");
	assertEquals(getBooking.jsonPath().get("bookingdates.checkout").toString(), "2022-01-01");
	assertEquals(getBooking.jsonPath().get("additionalneeds").toString(), "Cream Caramel");
    }

    @Test(description = "Restful-Booker - API - Delete Booking", dependsOnMethods = {"createBooking"})
    @Description("Returns the ids of all the bookings that exist within the API. Can take optional query strings to search and return a subset of booking ids.")
    @Story("Booking")
    @Severity(SeverityLevel.CRITICAL)
    @TmsLink("Test_case")
    @Issue("Software_bug")
    public void deleteBooking() {
	Response bookingIds = restfulBookerApisBooking.getBookingIds("Mahmoud", "ElSharkawy");
	String firstBookingId = bookingIds.jsonPath().get("bookingid[0]").toString();
	
	Response deleteBooking = restfulBookerApisBooking.deleteBooking(firstBookingId);
	assertEquals(deleteBooking.getBody().asString(), "Created");
    }
}
