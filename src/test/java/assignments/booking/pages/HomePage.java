package assignments.booking.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import generic.Utils;

public class HomePage {
    private WebDriver driver;

    private String url = "https://www.booking.com/";
    private By registration_button = By.xpath("(//div[@class='sign_in_wrapper']/span)[1]");
    private By close_button = By.cssSelector("button.modal-mask-closeBtn");
    private By search_bar_destination = By.id("ss");
    private By sharm_elshekh_result_field = By.cssSelector("[data-label='Sharm El Sheikh, South Sinai, Egypt']");
//	private By sharm_elshekh_result = By.xpath("//span[text()='Sharm El Sheikh']");
    private By guests_toggle = By.id("xp__guests__toggle");
    private By add_child_button = By.xpath("//div[contains(@class, 'sb-group-children')] //span[text()='+']");
    private By search_button = By.xpath("//span[text()='Search']");

    public HomePage(WebDriver driver) {
	this.driver = driver;
    }

    public void openURL() {
	driver.get(url);
    }

    public void clickOnTheRegistrationButton() {
	driver.findElement(registration_button).click();
    }

    public void clickOnCloseButton() {
	WebDriverWait wait = new WebDriverWait(driver, 20);
	wait.until(ExpectedConditions.elementToBeClickable(close_button));
	driver.findElement(close_button).click();
    }

    public void searchDestination(String destination, String checkInDate, String checkOutDate, int numbersOfchilds) {
	Utils.getWait(driver).until(ExpectedConditions.elementToBeClickable(search_bar_destination));
	driver.findElement(search_bar_destination).sendKeys(destination);

	Utils.getWait(driver).until(ExpectedConditions.visibilityOf(driver.findElement(sharm_elshekh_result_field)));
	driver.findElement(sharm_elshekh_result_field).click();

	Utils.getWait(driver)
		.until(ExpectedConditions.and(ExpectedConditions.visibilityOf(getCheckInElementLocator(checkInDate)),
			ExpectedConditions.visibilityOf(getCheckOutElementLocator(checkOutDate))));
	getCheckInElementLocator(checkInDate).click();
	getCheckOutElementLocator(checkOutDate).click();

	driver.findElement(guests_toggle).click();
	for (int i = 0; i < numbersOfchilds; i++) {
	    driver.findElement(add_child_button).click();
	    try {
		Thread.sleep(600);
	    } catch (Exception e) {
		System.out.println(e.getMessage());
	    }
	}
	driver.findElement(search_button).click();

    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////// PRIVATE METHODS
    ///////////////////////////////////////////////////////////////////////////////////////////////////////// ////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////////////////////////////////

    private WebElement getCheckInElementLocator(String checkInDate) {
//		return driver.findElement(By.cssSelector("[data-date ='2019-11-15']"));
	return driver.findElement(By.cssSelector("[data-date =" + "'" + checkInDate + "']"));
    }

    private WebElement getCheckOutElementLocator(String checkOutDate) {
//		return driver.findElement(By.cssSelector("[data-date ='2019-11-22']"));
	return driver.findElement(By.cssSelector("[data-date =" + "'" + checkOutDate + "']"));
    }

}
