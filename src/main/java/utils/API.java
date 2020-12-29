package utils;

import java.util.Map;

import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class API {
    private String baseURI = PropertiesReader.getProperty("liveproject.properties", "phptravels.home.url");
    
    private RequestSpecification requestSpec = new RequestSpecBuilder()
	    .setBaseUri(baseURI)
	    .log(LogDetail.ALL)
	    .build();
    private ResponseSpecification responseSpec = new ResponseSpecBuilder()
	    .expectStatusCode(200)
	    .log(LogDetail.BODY)
	    .build();
    
    @Step("Perform API Request with end point --> {endpoint}]")
    public Response performRequest(String endpoint) {
	Response res = RestAssured
	.given()
		.spec(requestSpec)
	.when()
		.post(endpoint)
	.then()
		.spec(responseSpec)
	.and()
		.extract().response();
	
	Logger.attachApiResponse(res.asByteArray());
	
	return res;

    }
    
    @Step("Perform API Request with end point --> {endpoint}]")
    public Response performRequest_withFormParams(String endpoint, Map<String, Object> formParams) {
	Response res = RestAssured
	.given()
		.formParams(formParams)	
	.and()
		.spec(requestSpec)
	.when()
		.post(endpoint)
	.then()
		.spec(responseSpec)
	.and()
		.extract().response();
	
	Logger.attachApiResponse(res.asByteArray());
	
	return res;

    }
    
    @Step("Perform API Request with end point --> [{endpoint}]")
    public Response performRequest_withCookies(String endpoint, Map<String, String> cookies) {
	Response res = RestAssured
	.given()
		.spec(requestSpec)
		.cookies(cookies)
	.when()
		.post(endpoint)
	.then()
		.spec(responseSpec)
	.and()
		.extract().response();
	
	Logger.attachApiResponse(res.asByteArray());
	
	return res;

    }

}
