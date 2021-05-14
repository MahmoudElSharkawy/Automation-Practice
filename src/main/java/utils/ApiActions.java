package utils;

import java.util.Map;

import com.aventstack.extentreports.markuputils.MarkupHelper;

import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.QueryableRequestSpecification;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import io.restassured.specification.SpecificationQuerier;

public class ApiActions {
    // TODO: Enhance the logging & BASE URI should start from here as a constructor
    private RequestSpecification request;
    private Response response;
    private QueryableRequestSpecification queryableRequestSpecs;
    private String baseUrl;

    public enum RequestType {
	POST("POST"), GET("GET"), PUT("PUT"), DELETE("DELETE"), PATCH("PATCH");

	private String value;

	RequestType(String type) {
	    this.value = type;
	}

	protected String getValue() {
	    return value;
	}
    }

    public ApiActions(String baseUrl) {
	this.baseUrl = baseUrl;
    }

    private RequestSpecification requestSpec = new RequestSpecBuilder()
//	    .setBaseUri(baseURI)
	    .log(LogDetail.ALL).build();
    private ResponseSpecification responseSpec = new ResponseSpecBuilder()
//	    .expectStatusCode(200)
	    .log(LogDetail.BODY).build();

    @Step("Perform API Request for Service Name: [{serviceName}]")
    /**
     * 
     * @param requestType
     * @param serviceName
     * @param expectedStatusCode
     * @param headers
     * @param contentType
     * @param formParams
     * @param queryParams
     * @param body
     * @param cookies
     * @return Response
     */
    public Response performRequest(RequestType requestType, String serviceName, int expectedStatusCode,
	    Map<String, Object> headers, ContentType contentType, Map<String, Object> formParams,
	    Map<String, Object> queryParams, Object body, Map<String, String> cookies) {
	serviceName = baseUrl + serviceName;

	request = RestAssured.given().spec(requestSpec);
	queryableRequestSpecs = SpecificationQuerier.query(request);

	Logger.logStep("Request URL: [" + serviceName + "] | Request Method: [" + requestType.getValue()
		+ "] | Expected Status Code: [" + expectedStatusCode + "]");

	if (headers != null) {
	    request.headers(headers);
	    String qHeaders = queryableRequestSpecs.getHeaders().toString();
	    Logger.attachApiRequest("Headers", qHeaders.getBytes());
	    ExtentReport.info(MarkupHelper.createCodeBlock("Request Headers: " + "\n" + qHeaders));

	}

	if (contentType != null) {
	    request.contentType(contentType);
	    String qContentType = queryableRequestSpecs.getContentType();
	    Logger.attachApiRequest("Content Type", qContentType.getBytes());
	    ExtentReport.info(MarkupHelper.createCodeBlock("Request Content Type: " + qContentType));
	}

	if (formParams != null) {
	    request.formParams(formParams);
	    String qFormParams = queryableRequestSpecs.getFormParams().toString();
	    Logger.attachApiRequest("Form params", qFormParams.getBytes());
	    ExtentReport.info(MarkupHelper.createCodeBlock("Request Form params: " + "\n" + qFormParams));
	}

	if (queryParams != null) {
	    request.queryParams(queryParams);
	    String qQueryParams = queryableRequestSpecs.getQueryParams().toString();
	    Logger.attachApiRequest("Query params", qQueryParams.getBytes());
	    ExtentReport.info(MarkupHelper.createCodeBlock("Request Query params: " + "\n" + qQueryParams));
	}

	if (body != null) {
	    request.body(body);
	    String qBody = queryableRequestSpecs.getBody().toString();
	    Logger.attachApiRequest("Body", qBody.getBytes());
	    ExtentReport.info(MarkupHelper.createCodeBlock("Request Body: " + "\n" + qBody));
	}

	if (cookies != null) {
	    request.cookies(cookies);
	    String qCookies = queryableRequestSpecs.getCookies().toString();
	    Logger.attachApiRequest("Cookies", qCookies.getBytes());
	    ExtentReport.info(MarkupHelper.createCodeBlock("Request Cookies: " + "\n" + qCookies));
	}

	switch (requestType) {
	case POST:
	    response = request.post(serviceName);
	    break;
	case GET:
	    response = request.get(serviceName);
	    break;
	case PUT:
	    response = request.put(serviceName);
	    break;
	case DELETE:
	    response = request.delete(serviceName);
	    break;
	case PATCH:
	    response = request.patch(serviceName);
	    break;
	}
	
	response.then().spec(responseSpec).statusCode(expectedStatusCode);

	Logger.attachApiResponse(response.asByteArray());
	ExtentReport.info(MarkupHelper.createCodeBlock("API Response: " + "\n" + response.asPrettyString()));

	return response;
    }

}
