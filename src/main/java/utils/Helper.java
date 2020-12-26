package utils;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Helper {
    static WebDriverWait wait;
    static Actions actions;

    private final static int TIMEOUT = Integer
	    .parseInt(PropertiesReader.getProperty("liveproject.properties", "Webdriver.Wait"));

    public static WebDriverWait getExplicitWait(WebDriver driver) {
	return wait = new WebDriverWait(driver, TIMEOUT);
    }

    public static void implicitWait(WebDriver driver) {
	driver.manage().timeouts().implicitlyWait(TIMEOUT, TimeUnit.SECONDS);
    }
    
    public static Actions getActions(WebDriver driver) {
	return actions = new Actions(driver);
    }

}
