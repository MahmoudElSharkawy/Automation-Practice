package utils;

import java.util.Map;

import com.aventstack.extentreports.markuputils.MarkupHelper;

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
	POST, GET, PUT, DELETE, PATCH;
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
	ExtentReport.info("Perform API Request --> [" + serviceName + "]");

	request = RestAssured.given().spec(requestSpec);
	queryableRequestSpecs = SpecificationQuerier.query(request);

	if (headers != null) {
	    request.headers(headers);
	    String qHeaders = queryableRequestSpecs.getHeaders().toString();
	    Logger.attachApiRequest(qHeaders.getBytes());
	    ExtentReport.info(MarkupHelper.createCodeBlock("Headers: " + "\n" + qHeaders));

	}

	if (formParams != null) {
	    request.formParams(formParams);
	    String qFormParams = queryableRequestSpecs.getFormParams().toString();
	    Logger.attachApiRequest(qFormParams.getBytes());
	    ExtentReport.info(MarkupHelper.createCodeBlock("Form params: " + "\n" + qFormParams));
	}

	if (queryParams != null) {
	    request.queryParams(queryParams);
	    String qQueryParams = queryableRequestSpecs.getQueryParams().toString();
	    Logger.attachApiRequest(qQueryParams.getBytes());
	    ExtentReport.info(MarkupHelper.createCodeBlock("Query params: " + "\n" + qQueryParams));
	}

	if (cookies != null) {
	    request.cookies(cookies);
	    String qCookies = queryableRequestSpecs.getCookies().toString();
	    Logger.attachApiRequest(qCookies.getBytes());
	    ExtentReport.info(MarkupHelper.createCodeBlock("Cookies: " + "\n" + qCookies));
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

	Logger.attachApiRequest(queryableRequestSpecs.getMethod().getBytes());
	ExtentReport.info(MarkupHelper.createCodeBlock("Request method: " + queryableRequestSpecs.getMethod()));

	response.then().spec(responseSpec).statusCode(expectedStatusCode);

	Logger.attachApiResponse(response.asByteArray());
	ExtentReport.info(MarkupHelper.createCodeBlock("API Response: " + "\n" + response.asPrettyString()));

	return response;
    }

}
