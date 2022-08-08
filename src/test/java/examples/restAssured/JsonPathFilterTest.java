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

	String pathWithFilter = "$[?(@.name=='Chelsey Dietrich')].id";
	var s = JsonPath.read(res.asPrettyString(), pathWithFilter);
	System.out.println(s);
	if (pathWithFilter.contains("?")) {
	    List<String> list = JsonPath.read(res.asPrettyString(), pathWithFilter);
	    System.out.println(String.valueOf(list.get(0)));
	}

	var s2 = JsonPath.read(res.asPrettyString(), "$[1].id");
	System.out.println(s2);
	
	var ss = JsonPath.read(res.asPrettyString(), "$[0].address.geo.lng");
	System.out.println(ss);
	
	Response ress = given().when().get("https://jsonplaceholder.typicode.com/todos");
	var sss = JsonPath.read(ress.asPrettyString(), "$[1].completed");
	System.out.println(sss);

	Response res2 = given().when().get("https://jsonplaceholder.typicode.com/posts/1");
	var s3 = JsonPath.read(res2.asPrettyString(), "id");
	System.out.println(s3);

	Response res3 = given().when().get("https://api.coindesk.com/v1/bpi/currentprice.json");
	var s4 = JsonPath.read(res3.asPrettyString(), "time.updated");
	var s5 = JsonPath.read(res3.asPrettyString(), "bpi.USD.description");
	System.out.println(s4);
	System.out.println(s5);

    }

}