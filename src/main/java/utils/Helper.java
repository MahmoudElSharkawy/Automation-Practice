package utils;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Helper {
    //TODO: Action to ElementActions class and Alert to BrowserActions class 
    
    static WebDriverWait wait;

    static Alert alert;

    public enum ActionType {
	MOUSE_HOVER, DOUBLE_CLICK;
    }

    public enum CookieBuilderType {
	ADD, DELETE;
    }

    public enum ConfirmAlerActionType {
	ACCEPT_ALERT, DISMISS_ALERT;
    }

    private static final int TIMEOUT = Integer
	    .parseInt(PropertiesReader.getProperty("liveproject.properties", "webdriver.wait"));

    public static WebDriverWait getExplicitWait(WebDriver driver) {
	return wait = new WebDriverWait(driver, TIMEOUT);
    }

    public static void implicitWait(WebDriver driver) {
	driver.manage().timeouts().implicitlyWait(TIMEOUT, TimeUnit.SECONDS);
    }

    public static void actions(WebDriver driver, By by, ActionType actionType) {
	Actions actions = new Actions(driver);
	switch (actionType) {
	case MOUSE_HOVER:
	    actions.moveToElement(driver.findElement(by)).perform();
	    break;
	case DOUBLE_CLICK:
	    actions.doubleClick(driver.findElement(by)).perform();
	    break;
	}
    }

    public static void cookieBuilder(WebDriver driver, CookieBuilderType cookieBuilderType, String name, String value,
	    String domain) {
	Cookie cookie = new Cookie.Builder(name, value).domain(domain).build();

	switch (cookieBuilderType) {
	case ADD:
	    driver.manage().addCookie(cookie);
	    break;
	case DELETE:
	    driver.manage().deleteCookie(cookie);
	    break;
	}
    }

    public static void confirmAlert(WebDriver driver, ConfirmAlerActionType confirmAlerActionType) {
	switch (confirmAlerActionType) {
	case ACCEPT_ALERT:
	    Helper.getExplicitWait(driver).until(ExpectedConditions.alertIsPresent());
	    alert = driver.switchTo().alert();
	    alert.accept();
	    break;
	case DISMISS_ALERT:
	    Helper.getExplicitWait(driver).until(ExpectedConditions.alertIsPresent());
	    alert = driver.switchTo().alert();
	    alert.dismiss();
	    break;
	}
    }
}
