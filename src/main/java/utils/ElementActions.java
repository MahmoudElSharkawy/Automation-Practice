package utils;

import static org.testng.Assert.fail;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

import utils.Helper.ActionType;

public class ElementActions {
    static WebDriver driver;

//    @Step("Click on element: [{elementLocator}]")
    public static void click(WebDriver driver, By elementLocator) {
	locatingElementStrategy(driver, elementLocator);
	try {
	    // Mouse hover on the element before clicking
	    Helper.actions(driver, elementLocator, ActionType.MOUSE_HOVER);
	    // wait for the element to be clickable
	    Helper.getExplicitWait(driver).until(ExpectedConditions.elementToBeClickable(elementLocator));
	} catch (TimeoutException toe) {
	    Logger.logStep("The element is not Clickable...." + toe.getMessage());
	} catch (Exception e) {
	    Logger.logStep(e.getMessage());
	}
	try {
	    // Log element text if not empty. Else, log clicking
	    if (!driver.findElement(elementLocator).getText().isBlank()) {
		Logger.logStep("Clicking on: [" + driver.findElement(elementLocator).getText() + "] Button");
	    } else {
		Logger.logStep("Clicking on element:" + elementLocator);
	    }
	    // Now we click on the element! :D
	    driver.findElement(elementLocator).click();
	} catch (Exception exception) {
	 // Click using JavascriptExecutor in case of the click is not performed successfully and got an exception using the Selenium click method
	    try {
		((JavascriptExecutor) driver).executeScript("arguments[arguments.length - 1].click();",
			driver.findElement(elementLocator));
	    } catch (Exception rootCauseException) {
		rootCauseException.initCause(exception);
		Logger.logStep(exception.getMessage());
		Logger.logStep(rootCauseException.getMessage());
		// Force fail the test case if couldn't perform the click
		fail("Couldn't click on the element:" + elementLocator, rootCauseException);
	    }
	}

    }

    public static void type(WebDriver driver, By elementLocator, String data) {
	type(driver, elementLocator, data, true);
    }

//    @Step("Type data: [{data}] on element: [{elementLocator}]")
    public static void type(WebDriver driver, By elementLocator, String data, boolean clearBeforeTyping) {
	locatingElementStrategy(driver, elementLocator);
	// Type here!
	try {
	    // Clear before typing condition
	    if (!driver.findElement(elementLocator).getAttribute("value").isBlank() && clearBeforeTyping) {
		Logger.logMessage("Clearing the data from element: " + elementLocator);
		driver.findElement(elementLocator).clear();
		Logger.logStep("Typing: [" + data + "] on element: " + elementLocator);
		// We type here! :D
		driver.findElement(elementLocator).sendKeys(data);
		// Type using JavascriptExecutor in case of the data is not typed successfully using the Selenium sendKeys method
		if (!driver.findElement(elementLocator).getAttribute("value").equals(data)) {
		    ((JavascriptExecutor) driver).executeScript("arguments[0].setAttribute('value', '" + data + "')",
			    driver.findElement(elementLocator));
		}
	    } else {
		Logger.logStep("Typing: [" + data + "] on element: " + elementLocator);
		// We type here! :D
		driver.findElement(elementLocator).sendKeys(data);
		// Type using JavascriptExecutor in case of the data is not typed successfully using the Selenium sendKeys method
		if (!driver.findElement(elementLocator).getAttribute("value").contains(data)) {
		    String currentValue = driver.findElement(elementLocator).getAttribute("value");
		    ((JavascriptExecutor) driver).executeScript(
			    "arguments[0].setAttribute('value', '" + currentValue + data + "')",
			    driver.findElement(elementLocator));
		}
	    }
	} catch (Exception e) {
	    Logger.logStep(e.getMessage());
	}
	// Make sure that the data is inserted correctly to the field
	Assert.assertTrue(driver.findElement(elementLocator).getAttribute("value").contains(data),
		"The data is not inserted successfully to the field, the inserted data should be: [" + data
			+ "]; But the current field value is: [" + driver.findElement(elementLocator).getAttribute("value") + "]");
    }

//    @Step("Click a Keyboard Key on element: [{elementLocator}]")
    public static void clickKeyboardKey(WebDriver driver, By elementLocator, Keys key) {
	locatingElementStrategy(driver, elementLocator);
	try {
	    Logger.logStep("Clicking a Keyboard key:[" + key.name() + "] on element: " + elementLocator);
	    // We click ENTER here! :D
	    driver.findElement(elementLocator).sendKeys(key);
	} catch (Exception e) {
	    Logger.logStep(e.getMessage());
	}
    }

//    @Step("Get the Text of element: [{elementLocator}]")
    public static String getText(WebDriver driver, By elementLocator) {
	locatingElementStrategy(driver, elementLocator);
	try {
	    String text = driver.findElement(elementLocator).getText();
	    Logger.logStep("Getting the Text of element: " + elementLocator + "; The Text is: " + text);
	    return text;
	} catch (Exception e) {
	    Logger.logStep(e.getMessage());
	}
	return null;
    }

    ////////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////////////

    private static void locatingElementStrategy(WebDriver driver, By elementLocator) {
	try {
	    // Wait for the element to be visible
	    Helper.getExplicitWait(driver).until(ExpectedConditions.visibilityOfElementLocated(elementLocator));
	    // Scroll the element into view to handle some browsers cases
	    ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(false);", driver.findElement(elementLocator));
	    // Check if the element is displayed
	    driver.findElement(elementLocator).isDisplayed();
	} catch (TimeoutException toe) {
	    Logger.logStep("The element is not Visible...." + toe.getMessage());
	} catch (Exception e) {
	    Logger.logStep(e.getMessage());
	}
    }

}
