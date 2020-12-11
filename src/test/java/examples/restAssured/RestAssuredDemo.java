package examples.restAssured;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import static org.hamcrest.Matchers.*;

public class RestAssuredDemo {
     
    
    @Test
    public void restAssuredGherkinsSyntaxTest() {
	
        given().
        when().
            get("http://zippopotam.us/us/90210").
        then().
            assertThat().
            body("places[0].'place name'", equalTo("Beverly Hills")).
        and().
            assertThat().
            statusCode(200).
        and().
            assertThat().
            contentType(ContentType.JSON);
	
    }
    
    
    @Test
    public void restAssuredCheckingTheResponseBodyTest() {
	
        given().
        when().
            get("http://zippopotam.us/us/90210").
        then().
            assertThat().
            body("places[0].state", equalTo("California")).
        and().
            assertThat().
            body("places.'place name'", hasItem("Beverly Hills")).
        and().
            assertThat().
            body("places.'place name'", hasSize(1));
	
    }
    
    
    @Test
    public void restAssuredLoggingTest() {
	
        given().
            log().all().
        when().
            get("http://zippopotam.us/us/90210").
        then().
            log().body();
	
    }
    
    
    @Test
    public void restAssuredTest() {
	RestAssured.baseURI = "http://api.zippopotam.us";
	Response response = RestAssured.get("/us/90210");

	System.out.println(response.asString());
	System.out.println(response.getStatusCode());
	System.out.println(response.asString().contains("California"));

	assertEquals(response.getStatusCode(), 200);
	assertTrue(response.body().jsonPath().get("places[0].'place name'").equals("Beverly Hills"));
	assertTrue(response.asString().contains("California"));

    }
    
}