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

    
//    private RequestSpecification requestSpec = new RequestSpecBuilder()
//	    .setBaseUri(PropertiesReader.getProperty("liveproject.properties", "phptravels.home.url"))
//	    .log(LogDetail.ALL)
//	    .build();
//    private ResponseSpecification responseSpec = new ResponseSpecBuilder()
//	    .expectStatusCode(200)
//	    .log(LogDetail.BODY)
//	    .build();

    @Step("User Sign up with Data --> First Name: [{firstName}], Last Name: [{lastName}], Mobile Number: [{mobileNumber}], Email: [{email}] and Password: [{password}]")
    public Response userSignUp(String firstName, String lastName, String mobileNumber, String email, String password) {
	Map<String, Object> formParams = new HashMap<String, Object>();
	formParams.put("firstname", firstName);
	formParams.put("lastname", lastName);
	formParams.put("phone", mobileNumber);
	formParams.put("email", email);
	formParams.put("password", password);
	formParams.put("confirmpassword", password);
	
	return	api.performRequest_withFormParams(signup_endpoint, formParams);
	
//	given()
//		.formParam("firstname", firstName)
//		.formParam("lastname", lastName)
//		.formParam("phone", mobileNumber)
//		.formParam("email", email)
//		.formParam("password", password)
//		.formParam("confirmpassword", password)
//	.and()
//		.spec(requestSpec)
//	.when()
//		.post(signup_endpoint)
//	.then()
//		.spec(responseSpec)
//	.and()
//		.extract().response();
		
    }
    
    @Step("User Login with Data --> Email: [{email}] and Password: [{password}]")
    public Response userLogin(String email, String password) {
	Map<String, Object> formParams = new HashMap<String, Object>();
	formParams.put("email", email);
	formParams.put("password", password);
	
	return api.performRequest_withFormParams(login_endpoint, formParams);
	
//	given()
//		.formParam("username", email)
//		.formParam("password", password)
//	.and()
//		.spec(requestSpec)
//	.when()
//		.post(login_endpoint)
//	.then()
//		.spec(responseSpec)
//	.and()
//		.extract().response();
    }
    
    @Step("Get User Account")
    public Response userAccount(Map<String, String> cookies) {
	return api.performRequest_withCookies(account_endpoint, cookies);

//	given()
//		.cookies(cookies)
//	.and()
//		.spec(requestSpec)
//	.when()
//		.get(account_endpoint)
//	.then()
//		.spec(responseSpec)
//	.and()
//		.extract().response();
    }
    
    @Step("Get Hotel Details with Data --> City Name: [{cityName}], Hotel Name: [{hotelName}], Check In Date: [{checkInDate}], Check Out Date: [{checkOutDate}], Adults Count: [{adultsCount}], Child Count: [{childCount}]")
    public Response hotelsSearch(String cityName, String hotelName, String checkInDate, String checkOutDate, String adultsCount, String childCount) {
	return api.performRequest(hotelsdetails_endpoint + "/" + cityName + "/" + hotelName + "/" + checkInDate + "/"
		+ checkOutDate + "/" + adultsCount + "/" + childCount);
    }
    
    @Step("Get Boat Details with Data --> Country Name: [{countryName}], City Name: [{cityName}], Boat Name: [{boatName}], Boat Date: [{boatDate}] and Adults Count: [{adultsCount}]")
    public Response boatsSearch(String countryName, String cityName, String boatName, String boatDate, String adultsCount) {
	Map<String, Object> queryParams = new HashMap<String, Object>();
	queryParams.put("date", boatDate);
	queryParams.put("adults", adultsCount);
	return api.performRequest_withQueryParams(boats_endpoint + "/" + countryName + "/" + cityName + "/" + boatName, queryParams);
    }

}
