package restAssured;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

import java.util.Date;

public class RestAssuredPostRequestDemo {
    Date date = new Date();
    
    @Test
    public void restAssuredGherkinsSyntaxTest1() {

	given().
	     formParam("firstname", "aw3a").
	     formParam("lastname", "alAPI").
	     formParam("phone", "01155150745").
	     formParam("email", "test" + date.getTime() + "@test.com").
	     formParam("password", "12345678").
	     formParam("confirmpassword", "12345678").
	when().
	     post("https://www.phptravels.net/account/signup").
	then().
	     assertThat().statusCode(200).
	 and().
             log().body();
	
    }
 
}