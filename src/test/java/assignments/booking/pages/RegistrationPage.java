package assignments.booking.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

import generic.Utils;

public class RegistrationPage {
    private WebDriver driver;

    private By email_field = By.id("login_name_register");
    private By get_started_button = By.xpath("//span[text()='Get started']");
    private By password_field = By.id("password");
    private By confirm_password_field = By.id("confirmed_password");
    private By create_account_button = By.xpath("//span[text()='Create account']");

    public RegistrationPage(WebDriver driver) {
	this.driver = driver;
    }

    public void Register(String email, String password) {
	driver.findElement(email_field).sendKeys(email);
	driver.findElement(get_started_button).click();
	Utils.getWait(driver).until(ExpectedConditions.elementToBeClickable(password_field));

	driver.findElement(password_field).sendKeys(password);
	driver.findElement(confirm_password_field).sendKeys(password);
	driver.findElement(create_account_button).click();

    }

}
