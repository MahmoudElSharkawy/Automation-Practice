package utils;

import static org.testng.Assert.fail;

import java.util.List;
import java.util.Map;

import com.aventstack.extentreports.markuputils.MarkupHelper;

import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.cookie.CookieFilter;
import io.restassured.filter.log.LogDetail;
import io.restassured.filter.session.SessionFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.exception.JsonPathException;
import io.restassured.response.Response;
import io.restassured.specification.QueryableRequestSpecification;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import io.restassured.specification.SpecificationQuerier;

public class ApiActions {
    private RequestSpecification request;
    private Response response;
    private QueryableRequestSpecification queryableRequestSpecs;
    private SessionFilter sessionFilter;
    private CookieFilter cookieFilter;
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
	sessionFilter = new SessionFilter();
	cookieFilter = new CookieFilter();
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
	String requestUrl = baseUrl + serviceName;

	request = RestAssured.given().spec(requestSpec);
	queryableRequestSpecs = SpecificationQuerier.query(request);

	Logger.logStep("Request URL: [" + requestUrl + "] | Request Method: [" + requestType.getValue()
		+ "] | Expected Status Code: [" + expectedStatusCode + "]");

	try {

	    if (headers != null) {
		request.headers(headers);
		String qHeaders = queryableRequestSpecs.getHeaders().toString();
		Logger.attachApiRequestToAllureReport("Headers", qHeaders.getBytes());
		ExtentReport.info(MarkupHelper.createCodeBlock("Request Headers: " + "\n" + qHeaders));

	    }

	    if (contentType != null) {
		request.contentType(contentType);
		String qContentType = queryableRequestSpecs.getContentType();
		Logger.attachApiRequestToAllureReport("Content Type", qContentType.getBytes());
		ExtentReport.info(MarkupHelper.createCodeBlock("Request Content Type: " + qContentType));
	    }

	    if (formParams != null) {
		request.formParams(formParams);
		String qFormParams = queryableRequestSpecs.getFormParams().toString();
		Logger.attachApiRequestToAllureReport("Form params", qFormParams.getBytes());
		ExtentReport.info(MarkupHelper.createCodeBlock("Request Form params: " + "\n" + qFormParams));
	    }

	    if (queryParams != null) {
		request.queryParams(queryParams);
		String qQueryParams = queryableRequestSpecs.getQueryParams().toString();
		Logger.attachApiRequestToAllureReport("Query params", qQueryParams.getBytes());
		ExtentReport.info(MarkupHelper.createCodeBlock("Request Query params: " + "\n" + qQueryParams));
	    }

	    if (body != null) {
		request.body(body);
		String qBody = queryableRequestSpecs.getBody().toString();
		Logger.attachApiRequestToAllureReport("Body", qBody.getBytes());
		ExtentReport.info(MarkupHelper.createCodeBlock("Request Body: " + "\n" + qBody));
	    }

	    if (cookies != null) {
		request.cookies(cookies);
		String qCookies = queryableRequestSpecs.getCookies().toString();
		Logger.attachApiRequestToAllureReport("Cookies", qCookies.getBytes());
		ExtentReport.info(MarkupHelper.createCodeBlock("Request Cookies: " + "\n" + qCookies));
	    }

	    request.filter(sessionFilter);
	    request.filter(cookieFilter);

	    switch (requestType) {
	    case POST:
		response = request.post(requestUrl);
		break;
	    case GET:
		response = request.get(requestUrl);
		break;
	    case PUT:
		response = request.put(requestUrl);
		break;
	    case DELETE:
		response = request.delete(requestUrl);
		break;
	    case PATCH:
		response = request.patch(requestUrl);
		break;
	    }

	    response.then().spec(responseSpec).statusCode(expectedStatusCode);

	} catch (Exception e) {
	    Logger.logStep(e.getMessage());
	    fail(e.getMessage());
	}

	Logger.attachApiResponseToAllureReport(response.asByteArray());
	ExtentReport.info(MarkupHelper.createCodeBlock("API Response: " + "\n" + response.asPrettyString()));

	return response;
    }

    public static String getResponseJsonValue(Response response, String jsonPath) {
	String value = "";
	try {
	    value = response.jsonPath().getString(jsonPath);
	} catch (ClassCastException | JsonPathException | IllegalArgumentException e) {
	    Logger.logStep(e.getMessage());
	    fail(e.getMessage());
	}
	if (value != null) {
	    return value;
	} else {
	    return null;
	}
    }

    public static List<Object> getResponseJsonValueAsList(Response response, String jsonPath) {
	List<Object> listValue = null;
	try {
	    listValue = response.jsonPath().getList(jsonPath);
	} catch (ClassCastException | JsonPathException | IllegalArgumentException e) {
	    Logger.logStep(e.getMessage());
	    fail(e.getMessage());
	}
	if (listValue != null) {
	    return listValue;
	} else {
	    return null;
	}
    }

    public static String getResponseBody(Response response) {
	return response.getBody().asString();
    }

}
