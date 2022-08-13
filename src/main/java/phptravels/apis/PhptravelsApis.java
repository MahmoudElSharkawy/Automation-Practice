package phptravels.apis;

import java.util.HashMap;
import java.util.Map;

import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import utils.ApiActions;
import utils.ExtentReport;
import utils.ApiActions.RequestType;

public class PhptravelsApis {
    String baseUrl = System.getProperty("phptravels.baseurl");
    ApiActions api = new ApiActions(baseUrl);

    // Expected status codes
//    private static final int SUCCESS = 200; 
    private static final int REDIRECTION = 302;
    
    // Services Names
    private String signup_endpoint = "/signup";


    @Step("User Sign up with Data --> First Name: [{firstName}], Last Name: [{lastName}], Mobile Number: [{mobileNumber}], Email: [{email}] and Password: [{password}]")
    public Response userSignUp(String firstName, String lastName, String mobileNumber, String email, String password, String accountType) {
	ExtentReport.info(MarkupHelper.createLabel(" Api - User Sign up", ExtentColor.BLUE));

	Map<String, Object> formParams = new HashMap<>();
	formParams.put("address", "");
	formParams.put("first_name", firstName);
	formParams.put("last_name", lastName);
	formParams.put("phone", mobileNumber);
	formParams.put("email", email);
	formParams.put("password", password);
	formParams.put("type", password);

	return api.performRequest(RequestType.POST, signup_endpoint, REDIRECTION, null, null, formParams, null,
		null, null);

    }

}