package utils;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

import io.qameta.allure.Step;

public class ElementActions {
    static WebDriver driver;

    @Step("Clicking on element: [{by}]")
    public static void click(WebDriver driver, By by) {
	Logger.logMessage("Clicking on element:" + by);
	driver.findElement(by).click();
    }

    @Step("Typing: [{data}] on element: [{by}]")
    public static void type(WebDriver driver, By by, String data) {
	Logger.logMessage("Typing: " + data + " on element: " + by);
	driver.findElement(by).sendKeys(data);
    }

    @Step("Clicking: [ENTER key] on element: [{by}]")
    public static void clickEnterKey(WebDriver driver, By by) {
	Logger.logMessage("Clicking: [ENTER key] on element: " + by);
	driver.findElement(by).sendKeys(Keys.ENTER);
    }

    @Step("Getting the Text of element: [{by}]")
    public static String getText(WebDriver driver, By by) {
	Logger.logMessage("Getting the Text of element: " + by);
	return driver.findElement(by).getText();
    }

}
