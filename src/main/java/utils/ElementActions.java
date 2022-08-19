package utils;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.fail;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

public class ElementActions {
    private WebDriver driver;

    public enum SelectType {
	TEXT, VALUE;
    }

    public ElementActions(WebDriver driver) {
	this.driver = driver;
    }

//    @Step("Click on element: [{elementLocator}]")
    public static void click(WebDriver driver, By elementLocator) {
	locatingElementStrategy(driver, elementLocator);
	try {
	    // wait for the element to be clickable
	    Helper.getExplicitWait(driver).until(ExpectedConditions.elementToBeClickable(elementLocator));
	} catch (TimeoutException toe) {
	    Logger.logStep(toe.getMessage());
	    fail(toe.getMessage());
	} catch (Exception e) {
	    Logger.logStep(e.getMessage());
	    fail(e.getMessage());
	}
	// Log the clicking action
	logElementActionStep(driver, "Click on", elementLocator);
	try {
	    /*
	     * Mouse hover on the element before clicking to enable styling and handle some
	     * element clicking issues that happens without hovering
	     */
	    new Actions(driver).moveToElement(driver.findElement(elementLocator)).perform();
	    // Now we click on the element! :>D
	    driver.findElement(elementLocator).click();
	} catch (Exception exception) {
	    /*
	     * Click using JavascriptExecutor in case of the click is not performed
	     * successfully and got an exception using the Selenium click method
	     */
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

    public ElementActions click(By elementLocator) {
	click(driver, elementLocator);
	return this;
    }

    public static void type(WebDriver driver, By elementLocator, String text) {
	type(driver, elementLocator, text, true);
    }

//    @Step("Type data: [{data}] on element: [{elementLocator}]")
    public static void type(WebDriver driver, By elementLocator, String text, boolean clearBeforeTyping) {
	locatingElementStrategy(driver, elementLocator);
	try {
	    // Clear before typing condition
	    if (!driver.findElement(elementLocator).getAttribute("value").isBlank() && clearBeforeTyping) {
		logElementActionStep(driver, "Clear and Type [" + text + "] on", elementLocator);
		driver.findElement(elementLocator).clear();
		// We type here! :D
		driver.findElement(elementLocator).sendKeys(text);
		// Type using JavascriptExecutor in case of the data is not typed successfully
		// using the Selenium sendKeys method
		if (!driver.findElement(elementLocator).getAttribute("value").equals(text)) {
		    ((JavascriptExecutor) driver).executeScript("arguments[0].setAttribute('value', '" + text + "')",
			    driver.findElement(elementLocator));
		}
	    } else {
		logElementActionStep(driver, "Type [" + text + "] on", elementLocator);
		// We type here! :D
		driver.findElement(elementLocator).sendKeys(text);
		// Type using JavascriptExecutor in case of the data is not typed successfully
		// using the Selenium sendKeys method
		if (!driver.findElement(elementLocator).getAttribute("value").contains(text)) {
		    String currentValue = driver.findElement(elementLocator).getAttribute("value");
		    ((JavascriptExecutor) driver).executeScript(
			    "arguments[0].setAttribute('value', '" + currentValue + text + "')",
			    driver.findElement(elementLocator));
		}
	    }
	} catch (Exception e) {
	    Logger.logStep(e.getMessage());
	    fail(e.getMessage());
	}
	// Make sure that the data is inserted correctly to the field
	Assert.assertTrue(driver.findElement(elementLocator).getAttribute("value").contains(text),
		"The data is not inserted successfully to the field, the inserted data should be: [" + text
			+ "]; But the current field value is: ["
			+ driver.findElement(elementLocator).getAttribute("value") + "]");
    }

    public ElementActions type(By elementLocator, String text) {
	type(driver, elementLocator, text, true);
	return this;
    }

    public ElementActions type(By elementLocator, String text, boolean clearBeforeTyping) {
	type(driver, elementLocator, text, clearBeforeTyping);
	return this;
    }

    public static void select(WebDriver driver, By elementLocator, SelectType selectType, String option) {
	locatingElementStrategy(driver, elementLocator);
	try {
	    Select select = new Select(driver.findElement(elementLocator));
//	    Logger.logStep("[Element Action] Select [" + option + "] on element [" + elementLocator + "]");
	    logElementActionStep(driver, "Select [" + option + "] from", elementLocator);
	    assertFalse(select.isMultiple());
	    switch (selectType) {
	    case TEXT -> select.selectByVisibleText(option);
	    case VALUE -> select.selectByValue(option);
	    default -> Logger.logMessage("Unexpected value: " + selectType);
	    }
	} catch (Exception e) {
	    Logger.logStep(e.getMessage());
	    fail(e.getMessage());
	}
    }

    public ElementActions select(By elementLocator, SelectType selectType, String option) {
	select(driver, elementLocator, selectType, option);
	return this;
    }

    public static void mouseHover(WebDriver driver, By elementLocator) {
	locatingElementStrategy(driver, elementLocator);
	logElementActionStep(driver, "Mouse Hover over", elementLocator);
	try {
	    new Actions(driver).moveToElement(driver.findElement(elementLocator)).perform();
	} catch (Exception e) {
	    Logger.logStep(e.getMessage());
	    fail(e.getMessage());
	}
    }

    public ElementActions mouseHover(By elementLocator) {
	mouseHover(driver, elementLocator);
	return this;
    }

    public static void doubleClick(WebDriver driver, By elementLocator) {
	locatingElementStrategy(driver, elementLocator);
	logElementActionStep(driver, "Double Click on", elementLocator);
	try {
	    Actions actions = new Actions(driver);
	    actions.doubleClick(driver.findElement(elementLocator)).perform();
	} catch (Exception e) {
	    Logger.logStep(e.getMessage());
	    fail(e.getMessage());
	}
    }

    public ElementActions doubleClick(By elementLocator) {
	doubleClick(driver, elementLocator);
	return this;
    }

//  @Step("Click a Keyboard Key on element: [{elementLocator}]")
    public static void clickKeyboardKey(WebDriver driver, By elementLocator, Keys key) {
	locatingElementStrategy(driver, elementLocator);
	logElementActionStep(driver, "Click a Keyboard key [" + key.name() + "] on", elementLocator);
	try {
	    // We click the key here! :D
	    driver.findElement(elementLocator).sendKeys(key);
	} catch (Exception e) {
	    Logger.logStep(e.getMessage());
	}
    }

    public ElementActions clickKeyboardKey(By elementLocator, Keys key) {
	clickKeyboardKey(driver, elementLocator, key);
	return this;
    }

//    @Step("Get the Text of element: [{elementLocator}]")
    public static String getText(WebDriver driver, By elementLocator) {
	locatingElementStrategy(driver, elementLocator);
	try {
	    String text = driver.findElement(elementLocator).getText();
	    logElementActionStep(driver, "Get Text" + "[" + text + "] from", elementLocator);
	    return text;
	} catch (Exception e) {
	    Logger.logStep(e.getMessage());
	}
	return null;
    }

    public static String getAttributeValue(WebDriver driver, By elementLocator, String attributeName) {
	locatingElementStrategy(driver, elementLocator);
	try {
	    String attributeValue = driver.findElement(elementLocator).getAttribute(attributeName);
	    logElementActionStep(driver, "Get the [" + attributeName + "] Attribute Value" + "[" + attributeValue + "] from",
		    elementLocator);
	    return attributeValue;
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
	    ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(false);",
		    driver.findElement(elementLocator));
	    // Check if the element is displayed
	    if (!driver.findElement(elementLocator).isDisplayed()) {
		Logger.logStep("The element [" + elementLocator.toString() + "] is not Displayed");
		fail("The element [" + elementLocator.toString() + "] is not Displayed");
	    }
	} catch (TimeoutException toe) {
	    Logger.logStep(toe.getMessage());
	    fail(toe.getMessage());
	} catch (Exception e) {
	    Logger.logStep(e.getMessage());
	    fail(e.getMessage());
	}
    }

    private static void logElementActionStep(WebDriver driver, String action, By elementLocator) {
	String elementName = driver.findElement(elementLocator).getAccessibleName();
	if ((elementName != null && !elementName.isEmpty())) {
	    Logger.logStep("[Element Action] " + action + " [" + elementName + "] element");
	} else {
	    Logger.logStep("[Element Action] " + action + " [" + elementLocator + "] element");
	}

    }

}
