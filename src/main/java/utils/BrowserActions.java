package utils;

import org.openqa.selenium.WebDriver;

import io.qameta.allure.Step;

public class BrowserActions {
    static WebDriver driver;

    @Step("Navigate to URL: [{url}]")
    public static void navigateToUrl(WebDriver driver, String url) {
	Logger.logMessage("Navigating to URL: " + url);
	driver.get(url);
//	driver.navigate().to(url);
//	Helper.getJavascriptExecutor(driver).executeScript("return document.readyState").equals("complete");
    }

    @Step("Close All Opened Browser Windows.....")
    public static void closeAllOpenedBrowserWindows(WebDriver driver) {
	Logger.logMessage("Closing All Opened Browser Windows.....");
	if (driver != null) {
	    driver.quit();
	}
    }

    @Step("Maximize the Browser Window")
    public static void maximizeTheWindow(WebDriver driver) {
	Logger.logMessage("Maximizing the Browser Window");
	driver.manage().window().maximize();
    }

}
