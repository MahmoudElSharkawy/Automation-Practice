package utils;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

import io.qameta.allure.Step;

public class ElementActions {
    static WebDriver driver;
//TODO: add scroll into view java script with java script executor to handle the firefox cases
//TODO: ashel al ing mn hena wmen al classes kolaha
    
    @Step("Clicking on element: [{by}]")
    public static void click(WebDriver driver, By by) {
	Helper.getExplicitWait(driver).until(ExpectedConditions.visibilityOfElementLocated(by));
	Helper.getExplicitWait(driver).until(ExpectedConditions.elementToBeClickable(by));
	Logger.logMessage("Clicking on element:" + by);
	Helper.getActions(driver).moveToElement(driver.findElement(by)).perform();
	driver.findElement(by).click();
    }

    @Step("Clearing and Typing: [{data}] on element: [{by}]")
    public static void type(WebDriver driver, By by, String data) {
	Helper.getExplicitWait(driver).until(ExpectedConditions.visibilityOfElementLocated(by));
	Logger.logMessage("Typing: " + data + " on element: " + by);
	driver.findElement(by).clear();
	driver.findElement(by).sendKeys(data);
//	WebDriverWaits.getExplicitWait(driver).until(ExpectedConditions.textToBe(by, data));
//	WebDriverWaits.getExplicitWait(driver).until(ExpectedConditions.textToBePresentInElementLocated(by, data));
    }

    @Step("Clicking: [ENTER key] on element: [{by}]")
    public static void clickEnterKey(WebDriver driver, By by) {
	Logger.logMessage("Clicking: [ENTER key] on element: " + by);
	driver.findElement(by).sendKeys(Keys.ENTER);
    }

    @Step("Getting the Text of element: [{by}]")
    public static String getText(WebDriver driver, By by) {
	Helper.getExplicitWait(driver).until(ExpectedConditions.visibilityOfElementLocated(by));
	Logger.logMessage("Getting the Text of element: " + by);
	return driver.findElement(by).getText();
    }

}
