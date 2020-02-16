package assignments.parabank.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ParaBankSkeleton {
	WebDriver driver;
	
	By request_loan_link = By.linkText("Request Loan");
	By home_link = By.linkText("Home");

	public ParaBankSkeleton(WebDriver driver) {
		this.driver = driver;
	}
	
	public void clickOnRequestLoanLink() {
		driver.findElement(request_loan_link).click();
	}
	
	public void clickOnHomeLink() {
		driver.findElement(home_link).click();
	}
	
}
