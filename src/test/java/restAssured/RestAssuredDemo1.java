package restAssured;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.restassured.RestAssured;

import io.restassured.response.Response;
import static org.hamcrest.Matchers.*;

public class RestAssuredDemo1 {
 
    Response response;

    @BeforeTest
    public void beforeClass() {
	
	RestAssured.baseURI = "http://api.zippopotam.us";
    }
    
    @Test
    public void restAssuredTest() {
	response = RestAssured.get("/us/90210");
	System.out.println(response.asString());
	System.out.println(response.getStatusCode());
	System.out.println(response.asString().contains("California"));

	assertEquals(response.getStatusCode(), 200);	
	assertTrue(response.body().jsonPath().get("places[0].'place name'").equals("Beverly Hills"));
	assertTrue(response.asString().contains("California"));
	
    }
    
    @Test
    public void restAssuredBDDTest() {
	RestAssured.
        given().
        when().
            get("http://zippopotam.us/us/90210").
        then().
            assertThat().
            body("places[0].'place name'", equalTo("Beverly Hills"));
    }
}