package utils;

import java.util.Map;

import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.response.Response;
import io.restassured.specification.QueryableRequestSpecification;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import io.restassured.specification.SpecificationQuerier;

public class ApiActions {
    RequestSpecification request;
    Response response;
    QueryableRequestSpecification queryableRequestSpecs;
    
    public enum RequestType {
	POST, GET, DELETE;
    }

    private RequestSpecification requestSpec = new RequestSpecBuilder()
//	    .setBaseUri(baseURI)
	    .log(LogDetail.ALL)
	    .build();
    private ResponseSpecification responseSpec = new ResponseSpecBuilder()
//	    .expectStatusCode(200)
	    .log(LogDetail.BODY)
	    .build();
    
    @Step("Perform API Request --> [{serviceName}]")
    /**
     * 
     * @param requestType
     * @param serviceName
     * @param expectedStatusCode
     * @param headers
     * @param formParams
     * @param queryParams
     * @param cookies
     * @return Response
     */
    public Response performRequest(RequestType requestType, String serviceName, int expectedStatusCode,
	    Map<String, Object> headers, Map<String, Object> formParams, Map<String, Object> queryParams,
	    Map<String, String> cookies) {

	request = RestAssured.given().spec(requestSpec);
	queryableRequestSpecs = SpecificationQuerier.query(request);

	if (headers != null) {
	    request.headers(headers);
	    String qHeaders = queryableRequestSpecs.getHeaders().toString();
	    Logger.attachApiRequest_headers(qHeaders.getBytes());
	}

	if (formParams != null) {
	    request.formParams(formParams);
	    String qFormParams = queryableRequestSpecs.getFormParams().toString();
	    Logger.attachApiRequest_formParams(qFormParams.getBytes());
	}

	if (queryParams != null) {
	    request.queryParams(queryParams);
	    String qQueryParams = queryableRequestSpecs.getQueryParams().toString();
	    Logger.attachApiRequest_queryParams(qQueryParams.getBytes());
	}

	if (cookies != null) {
	    request.cookies(cookies);
	    String qCookies = queryableRequestSpecs.getCookies().toString();
	    Logger.attachApiRequest_coockies(qCookies.getBytes());
	}

	if (requestType == RequestType.POST) {
	    response = request.post(serviceName);
	} else if (requestType == RequestType.GET) {
	    response = request.get(serviceName);
	} else if (requestType == RequestType.DELETE) {
	    response = request.delete(serviceName);
	}

	response.then().spec(responseSpec).statusCode(expectedStatusCode);

	Logger.attachApiResponse(response.asByteArray());
	return response;

    }

//    @Step("Perform API Request --> [https://phptravels.net/{endpoint}]")
//    public Response performPostRequest(String endpoint) {
//	RestAssured.baseURI = baseURI;
//	Response res = RestAssured
//	.given()
//		.spec(requestSpec)
//	.when()
//		.post(endpoint)
//	.then()
//		.spec(responseSpec)
//	.and()
//		.extract().response();
//	
//	Logger.attachApiResponse(res.asByteArray());
//	
//	return res;
//
//    }
//    
//    @Step("Perform API Request --> [https://phptravels.net/{endpoint}]")
//    public Response performPostRequest_withFormParams(String endpoint, Map<String, Object> formParams) {
//	Response res = RestAssured
//	.given()
//		.formParams(formParams)	
//	.and()
//		.spec(requestSpec)
//	.when()
//		.post(endpoint)
//	.then()
//		.spec(responseSpec)
//	.and()
//		.extract().response();
//	
//	Logger.attachApiResponse(res.asByteArray());
//	
//	return res;
//
//    }
//    
//    @Step("Perform API Request --> [https://phptravels.net/{endpoint}]")
//    public Response performPostRequest_withQueryParams(String endpoint, Map<String, Object> queryParams) {
//	Response res = RestAssured
//	.given()
//		.queryParams(queryParams)
//	.and()
//		.spec(requestSpec)
//	.when()
//		.post(endpoint)
//	.then()
//		.spec(responseSpec)
//	.and()
//		.extract().response();
//	
//	Logger.attachApiResponse(res.asByteArray());
//	
//	return res;
//
//    }
//    
//    @Step("Perform API Request --> [https://phptravels.net/{endpoint}]")
//    public Response performPostRequest_withFormParamsAndHeaders(String endpoint, Map<String, Object> formParams,
//	    Map<String, Object> headers) {
//	Response res = RestAssured
//	.given()
//		.formParams(formParams)
//		.headers(headers)
//	.and()
//		.spec(requestSpec)
//	.when()
//		.post(endpoint)
//	.then()
//		.spec(responseSpec)
//	.and()
//		.extract().response();
//	
//	Logger.attachApiResponse(res.asByteArray());
//	
//	return res;
//
//    }
//    
//    @Step("Perform API Request --> [https://phptravels.net/{endpoint}]")
//    public Response performPostRequest_withFormParamsAndCookies(String endpoint, Map<String, String> cookies, Map<String, Object> formParams) {
//	Response res = RestAssured
//	.given()
//		.formParams(formParams)	
//	.and()
//		.spec(requestSpec)
//		.cookies(cookies)
//	.when()
//		.post(endpoint)
//	.then()
//		.spec(responseSpec)
//	.and()
//		.extract().response();
//	
//	Logger.attachApiResponse(res.asByteArray());
//	
//	return res;
//
//    }
//    
//    @Step("Perform API Request --> [https://phptravels.net/{endpoint}]")
//    public Response performGetRequest_withCookies(String endpoint, Map<String, String> cookies) {
//	Response res = RestAssured
//	.given()
//		.spec(requestSpec)
//		.cookies(cookies)
//	.when()
//		.get(endpoint)
//	.then()
//		.spec(responseSpec)
//	.and()
//		.extract().response();
//	
//	Logger.attachApiResponse(res.asByteArray());
//	
//	return res;
//
//    }
    

}
