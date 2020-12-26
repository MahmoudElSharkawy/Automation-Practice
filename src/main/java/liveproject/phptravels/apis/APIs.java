package liveproject.phptravels.apis;

import static io.restassured.RestAssured.given;

import io.qameta.allure.Step;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import utils.PropertiesReader;

public class APIs {
//    private static String account_endpoint = "account/";
//    private static String login_endpoint = "account/login";
    private static String signup_endpoint = "account/signup";
    
    private RequestSpecification requestSpec = new RequestSpecBuilder()
	    .setBaseUri(PropertiesReader.getProperty("liveproject.properties", "phptravels.home.url"))
	    .log(LogDetail.ALL)
	    .build();
    private ResponseSpecification responseSpec = new ResponseSpecBuilder()
	    .expectStatusCode(200)
	    .log(LogDetail.BODY)
	    .build();

    @Step("User Sign Up using APIs")
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

}
