package examples.restAssured;

import org.testng.annotations.Test;

import com.jayway.jsonpath.JsonPath;

import static io.restassured.RestAssured.given;

import java.util.List;

import io.restassured.response.Response;

public class JsonPathFilterTest {

    @Test
    public void jsonPathFilterTest() {

	Response res = given().when().get("https://jsonplaceholder.typicode.com/users");

	List<String> list = JsonPath.read(res.asString(), "$[?(@.name=='Chelsey Dietrich')].id");
	System.out.println(String.valueOf(list.get(0)));

    }

}