package utils;

import static org.testng.Assert.fail;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

import io.qameta.allure.Step;

public class ElementActions {
    static WebDriver driver;

    @Step("Click on element: [{by}]")
    public static void click(WebDriver driver, By by) {
	try {
	    // Wait for the element to be visible
	    Helper.getExplicitWait(driver).until(ExpectedConditions.visibilityOfElementLocated(by));
	    // Scroll the element into view to handle some browsers cases
	    Helper.getJavascriptExecutor(driver).executeScript("arguments[0].scrollIntoView(false);",
		    driver.findElement(by));
	    // Check if the element is displayed
	    driver.findElement(by).isDisplayed();
	} catch (TimeoutException toe) {
	    Logger.logMessage("The element is not Visible...." + toe.getMessage());
	} catch (Exception e) {
	    Logger.logMessage(e.getMessage());
	}

	// Mouse hover on the element before clicking
	Helper.getActions(driver).moveToElement(driver.findElement(by)).perform();

	try {
	    // wait for the element to be clickable
	    Helper.getExplicitWait(driver).until(ExpectedConditions.elementToBeClickable(by));
	} catch (TimeoutException toe) {
	    Logger.logMessage("The element is not Clickable...." + toe.getMessage());
	}

	// Log element text if not empty. Else, log clicking
	if (!driver.findElement(by).getText().isBlank()) {
	    Logger.logMessage("Clicking on: " + driver.findElement(by).getText());
	} else {
	    Logger.logMessage("Clicking on element:" + by);
	}

	// Now we click on the element! :D
	try {
	    driver.findElement(by).click();
	} catch (Exception exception) {
	    try {
		Helper.getJavascriptExecutor(driver).executeScript("arguments[arguments.length - 1].click();",
			driver.findElement(by));
	    } catch (Exception rootCauseException) {
		rootCauseException.initCause(exception);
		Logger.logMessage(exception.getMessage());
		Logger.logMessage(rootCauseException.getMessage());
	//Force fail the test case if couldn't perform the click 
		fail("Couldn't click on the element", rootCauseException);

	    }
	}

//	// Wait for the element to be visible
//	Helper.getExplicitWait(driver).until(ExpectedConditions.visibilityOfElementLocated(by));
//	// Scroll the element into view to handle some browsers cases
//	Helper.getJavascriptExecutor(driver).executeScript("arguments[0].scrollIntoView(false);",
//		driver.findElement(by));
//	// Check if the element is displayed
//	driver.findElement(by).isDisplayed();
//	// wait for the element to be clickable
//	Helper.getExplicitWait(driver).until(ExpectedConditions.elementToBeClickable(by));
//	// Log element text if not empty
//	if (!driver.findElement(by).getText().isBlank()) {
//	    Logger.logMessage("Clicking on: " + driver.findElement(by).getText());
//	} else {
//	    Logger.logMessage("Clicking on element:" + by);
//	}
//	// Mouse hover on the element before clicking
//	Helper.getActions(driver).moveToElement(driver.findElement(by)).perform();
//	// Now we click on the element! :D
//	driver.findElement(by).click();
    }

    @Step("Type data: [{data}] on element: [{by}]")
    public static void type(WebDriver driver, By by, String data) {
	Helper.getExplicitWait(driver).until(ExpectedConditions.visibilityOfElementLocated(by));
	Helper.getJavascriptExecutor(driver).executeScript("arguments[0].scrollIntoView(false);",
		driver.findElement(by));
	driver.findElement(by).isDisplayed();
	// Clear the element values if it has any before typing on it
	if (!driver.findElement(by).getAttribute("value").isBlank()) {
	    Logger.logMessage("Clearing the data from element: " + by);
	    driver.findElement(by).clear();
	    Logger.logMessage("Typing: " + data + " on element: " + by);
	    // We type here! :D
	    driver.findElement(by).sendKeys(data);
	} else {
	    Logger.logMessage("Typing: " + data + " on element: " + by);
	    // We type here! :D
	    driver.findElement(by).sendKeys(data);
	}
	// Make sure that the data is inserted correctly to the field
	Assert.assertTrue(driver.findElement(by).getAttribute("value").contains(data),
		"The data is not inserted successfully to the field, the inserted data should be: [" + data
			+ "]; But the current field value is: [" + driver.findElement(by).getAttribute("value") + "]");
    }

    @Step("Click: [ENTER key] on element: [{by}]")
    public static void clickEnterKey(WebDriver driver, By by) {
	Helper.getExplicitWait(driver).until(ExpectedConditions.visibilityOfElementLocated(by));
	Helper.getJavascriptExecutor(driver).executeScript("arguments[0].scrollIntoView(false);",
		driver.findElement(by));
	driver.findElement(by).isDisplayed();
	Logger.logMessage("Clicking: [ENTER key] on element: " + by);
	// We click ENTER here! :D
	driver.findElement(by).sendKeys(Keys.ENTER);
    }

    @Step("Get the Text of element: [{by}]")
    public static String getText(WebDriver driver, By by) {
	Helper.getExplicitWait(driver).until(ExpectedConditions.visibilityOfElementLocated(by));
	Helper.getJavascriptExecutor(driver).executeScript("arguments[0].scrollIntoView(false);",
		driver.findElement(by));
	driver.findElement(by).isDisplayed();
	// We get the text here! :D
	String text = driver.findElement(by).getText();
	Logger.logMessage("Getting the Text of element: " + by + "; The Text is: " + text);
	return text;
    }

}
