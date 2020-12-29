package liveproject.phptravels.apis;

import java.util.HashMap;
import java.util.Map;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import utils.API;

public class PhpTravels_APIs {
    API api = new API();

    private static final String account_endpoint = "account/";
    private static final String login_endpoint = "account/login";
    private static final String signup_endpoint = "account/signup";
    
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
	
	return	api.performRequest(signup_endpoint, formParams);
	
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
	
	return api.performRequest(login_endpoint, formParams);
	
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
    
    @Step("Getting User Account")
    public Response userAccount(Map<String, String> cookies) {
	
	return api.performRequestWithCookies(account_endpoint, cookies);

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

}
