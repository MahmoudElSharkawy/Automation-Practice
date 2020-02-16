package assignments.parabank.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ParaBankLoanRequestPage {
	WebDriver driver;
	
	By loan_amount_field = By.id("amount");
	By down_payment_field = By.id("downPayment");
	By apply_now_button = By.xpath("//input[@value='Apply Now']");
	By approval_message = By.xpath("//p[text()[contains(.,'Congratulations, your loan has been approved.')]]");

	
	public ParaBankLoanRequestPage(WebDriver driver) {
		this.driver = driver;
	}
	
	public void requestLoan(String loanAmount, String downPayment) {
		driver.findElement(loan_amount_field).sendKeys(loanAmount);
		driver.findElement(down_payment_field).sendKeys(downPayment);
		driver.findElement(apply_now_button).click();
		
	}
	
	public String getApprovalMessageText() {
		return driver.findElement(approval_message).getText();
	}

}
