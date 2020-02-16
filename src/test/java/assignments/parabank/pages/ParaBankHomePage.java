package assignments.parabank.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ParaBankHomePage {
	WebDriver driver;
	
	private By registration_link = By.linkText("Register");

    public ParaBankHomePage(WebDriver driver) {
	this.driver = driver;
    }
    
    public void clickOnRegistrationLink() {
    	driver.findElement(registration_link).click();
    }
    
}
