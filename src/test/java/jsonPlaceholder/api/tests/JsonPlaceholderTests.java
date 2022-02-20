package jsonPlaceholder.api.tests;

import static org.testng.Assert.assertEquals;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.restassured.response.Response;
import jsonPlaceholder.apis.JsonPlaceholderApis;
import jsonPlaceholder.apis.JsonPlaceholderUsersApis;
import utils.ApiActions;
import utils.Helper;
import utils.Logger;

@Epic("JsonPlaceholder")
@Feature("API")
public class JsonPlaceholderTests {
    private ApiActions apiObject;
    private JsonPlaceholderUsersApis usersApis;

    @Test(description = "JsonPlaceholder")
    public void jsonPlaceholderUser() {
	String userId = Helper.getRandomNumberBetweenTwoValuesAsString(1, 10);
	Response user = usersApis.getUser(userId);
	Logger.logMessage(ApiActions.getResponseJsonValue(user, "email"));

	Response userPosts = usersApis.getUserPosts(userId);
	Logger.logMessage(ApiActions.getResponseJsonValue(userPosts, "id"));
	usersApis.assertThatPostscontainsValidPostIds(userPosts);

	Response addPost = usersApis.createPost(userId, "post title", "post body");
	assertEquals(ApiActions.getResponseJsonValue(addPost, "title"), "post title");
	assertEquals(ApiActions.getResponseJsonValue(addPost, "body"), "post body");
	assertEquals(ApiActions.getResponseJsonValue(addPost, "userId"), userId);
    }

    /////////////////////////////////////////////////////////////////////

    @BeforeClass
    public void beforeClass() {
	apiObject = new ApiActions(JsonPlaceholderApis.BASE_URL);
	usersApis = new JsonPlaceholderUsersApis(apiObject);
    }

}
