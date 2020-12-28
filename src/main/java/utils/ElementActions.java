package utils;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

import io.qameta.allure.Step;

public class ElementActions {
    static WebDriver driver;
    // Exist >> Displayed >> clickable

    @Step("Click on element: [{by}]")
    public static void click(WebDriver driver, By by) {
	Helper.getExplicitWait(driver).until(ExpectedConditions.visibilityOfElementLocated(by));
	// Check if the element is displayed
	driver.findElement(by).isDisplayed();
	// wait for the element to be clickable
	Helper.getExplicitWait(driver).until(ExpectedConditions.elementToBeClickable(by));
	// Log element text if not empty
	if (!driver.findElement(by).getText().isBlank()) {
	    Logger.logMessage("Clicking on element: " + driver.findElement(by).getText());
	} else {
	    Logger.logMessage("Clicking on element:" + by);
	}
	Helper.getActions(driver).moveToElement(driver.findElement(by)).perform();
	driver.findElement(by).click();
    }

    @Step("Clear then Type: [{data}] on element: [{by}]")
    public static void type(WebDriver driver, By by, String data) {
	Helper.getExplicitWait(driver).until(ExpectedConditions.visibilityOfElementLocated(by));
	driver.findElement(by).isDisplayed();

	if (!driver.findElement(by).getAttribute("value").isBlank()) {
	    Logger.logMessage("Clearing the data from element: " + by);
	    driver.findElement(by).clear();
	    Logger.logMessage("Typing: " + data + " on element: " + by);
	    driver.findElement(by).sendKeys(data);

	} else {
	    Logger.logMessage("Typing: " + data + " on element: " + by);
	    driver.findElement(by).sendKeys(data);
	}
//	WebDriverWaits.getExplicitWait(driver).until(ExpectedConditions.textToBe(by, data));
//	WebDriverWaits.getExplicitWait(driver).until(ExpectedConditions.textToBePresentInElementLocated(by, data));
    }

    @Step("Click: [ENTER key] on element: [{by}]")
    public static void clickEnterKey(WebDriver driver, By by) {
	Helper.getExplicitWait(driver).until(ExpectedConditions.visibilityOfElementLocated(by));
	driver.findElement(by).isDisplayed();
	Logger.logMessage("Clicking: [ENTER key] on element: " + by);
	driver.findElement(by).sendKeys(Keys.ENTER);
    }

    @Step("Get the Text of element: [{by}]")
    public static String getText(WebDriver driver, By by) {
	Helper.getExplicitWait(driver).until(ExpectedConditions.visibilityOfElementLocated(by));
	driver.findElement(by).isDisplayed();
	Logger.logMessage("Getting the Text of element: " + by + " .... The Text is: " + driver.findElement(by).getText());
	return driver.findElement(by).getText();
    }

}
