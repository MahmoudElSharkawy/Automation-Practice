package utils;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.WebDriverEventListener;

import io.qameta.allure.Step;

public class SeleniumEventReporter implements WebDriverEventListener {
    @Override
    public void beforeAlertAccept(WebDriver webDriver) {

    }

    @Override
    public void afterAlertAccept(WebDriver webDriver) {

    }

    @Override
    public void afterAlertDismiss(WebDriver webDriver) {

    }

    @Override
    public void beforeAlertDismiss(WebDriver webDriver) {

    }

    @Override
    @Step("Selenium logs: Navigating to")
    public void beforeNavigateTo(String url, WebDriver webDriver) {
	Logger.logMessage("Navigating to url: " + url);
    }

    @Override
    @Step("Selenium logs: Successfully navigated to")
    public void afterNavigateTo(String url, WebDriver webDriver) {
	Logger.logMessage("Successfully navigated to url: " + url);
    }

    @Override
    public void beforeNavigateBack(WebDriver webDriver) {

    }

    @Override
    public void afterNavigateBack(WebDriver webDriver) {

    }

    @Override
    public void beforeNavigateForward(WebDriver webDriver) {

    }

    @Override
    public void afterNavigateForward(WebDriver webDriver) {

    }

    @Override
    public void beforeNavigateRefresh(WebDriver webDriver) {

    }

    @Override
    public void afterNavigateRefresh(WebDriver webDriver) {

    }

    @Override
    @Step("Selenium logs: Finding element")
    public void beforeFindBy(By by, WebElement webElement, WebDriver webDriver) {
	Logger.logMessage("Finding element --> " + by);
    }

    @Override
    @Step("Selenium logs: Successfully found element")
    public void afterFindBy(By by, WebElement webElement, WebDriver webDriver) {
	Logger.logMessage("Successfully found element --> " + by);
    }

    @Override
    @Step("Selenium logs: Clicking on element")
    public void beforeClickOn(WebElement webElement, WebDriver webDriver) {
	Logger.logMessage("Clicking on element: " + webElement);
    }

    @Override
    @Step("Selenium logs: Successfully Clicked on element")
    public void afterClickOn(WebElement webElement, WebDriver webDriver) {
	Logger.logMessage("Successfully clicked on element: " + webElement);
    }

    @Override
    @Step("Selenium logs: Changing value of Element")
    public void beforeChangeValueOf(WebElement webElement, WebDriver webDriver, CharSequence[] charSequences) {
	Logger.logMessage("Changing value of element: " + webElement);
    }

    @Override
    @Step("Selenium logs: Successfully changed the value of element")
    public void afterChangeValueOf(WebElement webElement, WebDriver webDriver, CharSequence[] charSequences) {
	Logger.logMessage("Successfully changed the value of element: " + webElement);
    }

    @Override
    public void beforeScript(String s, WebDriver webDriver) {

    }

    @Override
    public void afterScript(String s, WebDriver webDriver) {

    }

    @Override
    public void beforeSwitchToWindow(String s, WebDriver webDriver) {

    }

    @Override
    public void afterSwitchToWindow(String s, WebDriver webDriver) {

    }

    @Override
    public void onException(Throwable throwable, WebDriver webDriver) {

    }

    @Override
    public <X> void beforeGetScreenshotAs(OutputType<X> outputType) {

    }

    @Override
    public <X> void afterGetScreenshotAs(OutputType<X> outputType, X x) {

    }

    @Override
    public void beforeGetText(WebElement webElement, WebDriver webDriver) {
	Logger.logMessage("Getting text of element: " + webElement);
    }

    @Override
    public void afterGetText(WebElement webElement, WebDriver webDriver, String text) {
	Logger.logMessage("Successfully got the text of the element --> " + text);
    }
}
