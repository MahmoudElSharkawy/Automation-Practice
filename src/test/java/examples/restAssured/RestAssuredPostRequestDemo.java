package examples.restAssured;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

import java.util.Date;

public class RestAssuredPostRequestDemo {
    Date date = new Date();
    
    String firstname = "aw3a";
    String lastname = "alAPI";
    String phone = "01155150745";
    String email = "test" + date.getTime() + "@test.com";
    String password = "12345678";
    
    @Test
    public void restAssuredGherkinsSyntaxTest1() {
	System.out.println("The mail is: " + email);
	given().
	     formParam("firstname", firstname).
	     formParam("lastname", lastname).
	     formParam("phone", phone).
	     formParam("email", email).
	     formParam("password", password).
	     formParam("confirmpassword", password).
	     log().all().
	when().
	     post("https://www.phptravels.net/account/signup").
	then().
	     assertThat().statusCode(200).
	 and().
             log().body();
	
    }
 
}