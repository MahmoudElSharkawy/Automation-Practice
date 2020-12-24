package liveproject.phptravels.gui.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

import io.qameta.allure.Step;
import utils.Logger;

public class PhpTravels_SignUp_Page {
    private WebDriver driver;

    // Constructor
    public PhpTravels_SignUp_Page(WebDriver driver) {
	this.driver = driver;
    }

    // Elements
    private By firstname_field = By.name("firstname");
    private By lastname_field = By.name("lastname");
    private By phone_field = By.name("phone");
    private By email_field = By.name("email");
    private By password_field = By.name("password");
    private By confirmpassword_field = By.name("confirmpassword");
//    private By signup_button = By.xpath("//button[contains(text() ,'Sign Up')]");
    private By alert_text = By.xpath("//div[contains(@class, 'alert')]");


    // Methods
    @Step("User Sign up")
    public PhpTravels_UserAccount_Page userSignUp(String firstName, String lastName, String phone, String email,
	    String password) {
	driver.findElement(firstname_field).sendKeys(firstName);
	driver.findElement(lastname_field).sendKeys(lastName);
	driver.findElement(phone_field).sendKeys("1523648987");
	driver.findElement(email_field).sendKeys(email);
	driver.findElement(password_field).sendKeys(password);
	driver.findElement(confirmpassword_field).sendKeys(password, Keys.ENTER);
//	driver.findElement(signup_button).click();
//	WebDriverWaits.getExplicitWait(driver).until(ExpectedConditions.titleContains("My Account"));
	return new PhpTravels_UserAccount_Page(driver);
    }
    
    @Step("User Invalid Sign up")
    public PhpTravels_SignUp_Page invalidUserSignUp(String firstName, String lastName, String phone, String email,
	    String password) {
	userSignUp(firstName, lastName, phone, email, password);
	return this;
    }
    
    @Step("Get the text of the Alert message")
    public String getAlertMessage() {
	String m = driver.findElement(alert_text).getText(); 
	Logger.logMessage("The Alert message is: " + m);
	return m;
    }
    
}
