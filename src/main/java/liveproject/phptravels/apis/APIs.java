package liveproject.phptravels.apis;

import static io.restassured.RestAssured.given;

import java.util.Map;

import io.qameta.allure.Step;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import utils.PropertiesReader;

public class APIs {
    private static final String account_endpoint = "account/";
    private static final String login_endpoint = "account/login";
    private static final String signup_endpoint = "account/signup";
    
    private RequestSpecification requestSpec = new RequestSpecBuilder()
	    .setBaseUri(PropertiesReader.getProperty("liveproject.properties", "phptravels.home.url"))
	    .log(LogDetail.ALL)
	    .build();
    private ResponseSpecification responseSpec = new ResponseSpecBuilder()
	    .expectStatusCode(200)
	    .log(LogDetail.BODY)
	    .build();

    @Step("User Sign up with Data --> First Name: [{firstName}], Last Name: [{lastName}], Mobile Number: [{mobileNumber}], Email: [{email}] and Password: [{password}]")
    public Response userSignUp(String firstName, String lastName, String mobileNumber, String email, String password) {
	return
	given()
		.formParam("firstname", firstName)
		.formParam("lastname", lastName)
		.formParam("phone", mobileNumber)
		.formParam("email", email)
		.formParam("password", password)
		.formParam("confirmpassword", password)
	.and()
		.spec(requestSpec)
	.when()
		.post(signup_endpoint)
	.then()
		.spec(responseSpec)
	.and()
		.extract().response();
    }
    
    @Step("User Login with Data --> Email: [{email}] and Password: [{password}]")
    public Response userLogin(String email, String password) {
	return
	given()
		.formParam("username", email)
		.formParam("password", password)
	.and()
		.spec(requestSpec)
	.when()
		.post(login_endpoint)
	.then()
		.spec(responseSpec)
	.and()
		.extract().response();
    }
    
    @Step("Getting User Account")
    public Response userAccount(Map<String, String> cookies) {
	return
	given()
		.cookies(cookies)
	.and()
		.spec(requestSpec)
	.when()
		.get(account_endpoint)
	.then()
		.spec(responseSpec)
	.and()
		.extract().response();
    }

}
