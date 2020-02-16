package assignments.parabank.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ParaBankRegistrationPage {
	WebDriver driver;

	By first_name_field = By.id("customer.firstName");
	By last_name_field = By.id("customer.lastName");
	By address_field = By.id("customer.address.street");
	By city_field = By.id("customer.address.city");
	By state_field = By.id("customer.address.state");
	By zip_code_field = By.id("customer.address.zipCode");
	By phone_number_field = By.id("customer.phoneNumber");
	By ssn_field = By.id("customer.ssn");
	By username_field = By.id("customer.username");
	By password_field = By.id("customer.password");
	By confirm_password_field = By.id("repeatedPassword");
	By register_button = By.xpath("//input[@value='Register']");
	By welcome_message = By.xpath("//h1[@class='title']");


	public ParaBankRegistrationPage(WebDriver driver) {
		this.driver = driver;
	}

	public void userRegistration(String firstName, String lastname, String address, String city, String state,
			String zipCode, String phoneNumber, String ssn, String userName, String password) {

		driver.findElement(first_name_field).sendKeys(firstName);
		driver.findElement(last_name_field).sendKeys(lastname);
		driver.findElement(address_field).sendKeys(address);
		driver.findElement(city_field).sendKeys(city);
		driver.findElement(state_field).sendKeys(state);
		driver.findElement(zip_code_field).sendKeys(zipCode);
		driver.findElement(phone_number_field).sendKeys(phoneNumber);
		driver.findElement(ssn_field).sendKeys(ssn);
		driver.findElement(username_field).sendKeys(userName);
		driver.findElement(password_field).sendKeys(password);
		driver.findElement(confirm_password_field).sendKeys(password);
		driver.findElement(register_button).click();
		
	}
	
	public String getWelcomeMessageText() {
		return driver.findElement(welcome_message).getText();
	}

}
