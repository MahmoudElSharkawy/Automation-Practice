package jsonPlaceholder.apis;

import static org.testng.Assert.assertTrue;

import java.util.HashMap;
import java.util.Map;

import org.json.simple.JSONObject;

import io.qameta.allure.Step;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import jsonPlaceholder.apis.JsonPlaceholderApis.Status;
import utils.ApiActions;
import utils.ApiActions.RequestType;

public class JsonPlaceholderUsersApis {
    private ApiActions apiObject;

    // Services Names
    private String users_serviceName = "/users/";
    private String posts_serviceName = "/posts";

    // Constructor
    public JsonPlaceholderUsersApis(ApiActions apiObject) {
	this.apiObject = apiObject;
    }

    //////////////////////////////////////////////////////////////////
    //////////////////////////// Actions ////////////////////////////

    @Step("Get Users")
    public Response getUser(String userId) {
	return apiObject.performRequest(RequestType.GET, users_serviceName + userId, Status.SUCCESS.getCode(), null,
		null, null, null, null, null);
    }

    @Step("Get User's Posts")
    public Response getUserPosts(String userId) {
	return apiObject.performRequest(RequestType.GET, users_serviceName + userId + posts_serviceName,
		Status.SUCCESS.getCode(), null, null, null, null, null, null);
    }

    @SuppressWarnings("unchecked")
    @Step("Add Post")
    public Response createPost(String userId, String title, String body) {
	JSONObject createPostBody = new JSONObject();
	createPostBody.put("title", title);
	createPostBody.put("body", body);
	createPostBody.put("userId", userId);

	Map<String, Object> createPostHeaders = new HashMap<>();
	createPostHeaders.put("charset", "UTF-8");

	return apiObject.performRequest(RequestType.POST, posts_serviceName, Status.SUCCESS_CREATE.getCode(),
		createPostHeaders, ContentType.JSON, null, null, createPostBody, null);
    }

    ////////////////////////

    public void assertThatPostscontainsValidPostIds(Response postsResponse) {
	for (Object element : ApiActions.getResponseJsonValueAsList(postsResponse, "id")) {
	    assertTrue(1 <= (int) element && (int) element <= 100);
	}
    }

}
