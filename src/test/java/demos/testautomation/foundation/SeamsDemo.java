package demos.testautomation.foundation;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import utils.BrowserFactory;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

import java.time.Duration;
import java.util.Date;

public class SeamsDemo {
    Date date = new Date();
    
    WebDriver driver;
    
    String firstName = "aw3a";
    String lastName = "alAPI";
    String phone = "01155150745";
    String email = "test" + date.getTime() + "@test.com";
    String password = "12345678";
    
    @BeforeTest
    public void UserRegistrationByAPI() {	
	System.out.println("The mail is: " + email);
	given().
	     formParam("firstname", firstName).
	     formParam("lastname", lastName).
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
    
    @Test
    public void Login() {	
	driver = BrowserFactory.getBrowser();
	
	driver.get("https://www.phptravels.net/login");
	driver.findElement(By.name("username")).sendKeys(email);
	driver.findElement(By.name("password")).sendKeys(password);
	driver.findElement(By.xpath("//button[contains(text() ,'Login')]")).click();

	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
	wait.until(ExpectedConditions.titleContains("My Account"));
	System.out.println(driver.getTitle());

	WebElement welcomeTxt = driver.findElement(By.xpath("//h3[@style='margin-left: 17px']"));

	assertEquals(welcomeTxt.getText(), "Hi, " + firstName + " " + lastName);
	
    }
 
}