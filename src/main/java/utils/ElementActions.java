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
    static WebDriver driver;
    
    public enum SelectType {
	TEXT, VALUE;
    }

//    @Step("Click on element: [{elementLocator}]")
    public static void click(WebDriver driver, By elementLocator) {
	// Mouse hover on the element before clicking
	mouseHover(driver, elementLocator);
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
	try {
	    // Log element text if not empty. Else, log clicking
	    if (!driver.findElement(elementLocator).getText().isBlank()) {
		Logger.logStep("[Element Action] Click on [" + driver.findElement(elementLocator).getText() + "] Button");
	    } else {
		Logger.logStep("[Element Action] Click on element [" + elementLocator + "]");
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

    public static void type(WebDriver driver, By elementLocator, String text) {
	type(driver, elementLocator, text, true);
    }

//    @Step("Type data: [{data}] on element: [{elementLocator}]")
    public static void type(WebDriver driver, By elementLocator, String text, boolean clearBeforeTyping) {
	locatingElementStrategy(driver, elementLocator);
	try {
	    // Clear before typing condition
	    if (!driver.findElement(elementLocator).getAttribute("value").isBlank() && clearBeforeTyping) {
		Logger.logStep("[Element Action] Clear and Type [" + text + "] on element [" + elementLocator + "]");
		driver.findElement(elementLocator).clear();
		// We type here! :D
		driver.findElement(elementLocator).sendKeys(text);
		// Type using JavascriptExecutor in case of the data is not typed successfully using the Selenium sendKeys method
		if (!driver.findElement(elementLocator).getAttribute("value").equals(text)) {
		    ((JavascriptExecutor) driver).executeScript("arguments[0].setAttribute('value', '" + text + "')",
			    driver.findElement(elementLocator));
		}
	    } else {
		Logger.logStep("[Element Action] Type [" + text + "] on element [" + elementLocator + "]");
		// We type here! :D
		driver.findElement(elementLocator).sendKeys(text);
		// Type using JavascriptExecutor in case of the data is not typed successfully using the Selenium sendKeys method
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
			+ "]; But the current field value is: [" + driver.findElement(elementLocator).getAttribute("value") + "]");
    }
    
    public static void select(WebDriver driver, By elementLocator, SelectType selectType, String option) {
	locatingElementStrategy(driver, elementLocator);
	try {
	    Select s = new Select(driver.findElement(elementLocator));
	    Logger.logStep("[Element Action] Select [" + option + "] on element [" + elementLocator + "]");
	    assertFalse(s.isMultiple());
	    switch (selectType) {
	    case TEXT:
		s.selectByVisibleText(option);
		break;
	    case VALUE:
		Helper.getExplicitWait(driver).until(ExpectedConditions.alertIsPresent());
		s.selectByValue(option);
		break;
	    }
	} catch (Exception e) {
	    Logger.logStep(e.getMessage());
	    fail(e.getMessage());
	}
    }

    public static void mouseHover(WebDriver driver, By elementLocator) {
	locatingElementStrategy(driver, elementLocator);
	try {
	    Actions actions = new Actions(driver);
	    actions.moveToElement(driver.findElement(elementLocator)).perform();
	} catch (Exception e) {
	    Logger.logStep(e.getMessage());
	    fail(e.getMessage());
	}
    }

    public static void doubleClick(WebDriver driver, By elementLocator) {
	locatingElementStrategy(driver, elementLocator);
	try {
	    Actions actions = new Actions(driver);
	    actions.doubleClick(driver.findElement(elementLocator)).perform();
	} catch (Exception e) {
	    Logger.logStep(e.getMessage());
	    fail(e.getMessage());
	}
    }  

//  @Step("Click a Keyboard Key on element: [{elementLocator}]")
  public static void clickKeyboardKey(WebDriver driver, By elementLocator, Keys key) {
	locatingElementStrategy(driver, elementLocator);
	try {
	    Logger.logStep("[Element Action] Click a Keyboard key [" + key.name() + "] on element [" + elementLocator + "]");
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
	    Logger.logStep("[Element Action] Get the Text of element [" + elementLocator + "]; The Text is [" + text + "]");
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
	    Logger.logStep(toe.getMessage());
	    fail(toe.getMessage());
	} catch (Exception e) {
	    Logger.logStep(e.getMessage());
	    fail(e.getMessage());
	}
    }

}
