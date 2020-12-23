package liveproject.phptravels.gui.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

import io.qameta.allure.Step;
import utils.WebDriverWaits;

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
	WebDriverWaits.getExplicitWait(driver).until(ExpectedConditions.titleContains("My Account"));
	return new PhpTravels_UserAccount_Page(driver);
    }

}
