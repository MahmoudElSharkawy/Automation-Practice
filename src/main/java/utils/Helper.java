package utils;

import static org.testng.Assert.fail;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Helper {
    private static final int TIMEOUT = Integer
	    .parseInt(PropertiesReader.getProperty("automationPractice.properties", "webdriver.wait"));

    public static WebDriverWait getExplicitWait(WebDriver driver) {
	return new WebDriverWait(driver, Duration.ofSeconds(TIMEOUT));
    }

    public static void implicitWait(WebDriver driver) {
	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(TIMEOUT));
    }

    public static String getCurrentTime(String dateFormat) {
	String currentTime = "";
	try {
	    currentTime = new SimpleDateFormat(dateFormat).format(new Date());
	} catch (IllegalArgumentException e) {
	    Logger.logStep(e.getMessage());
	    fail(e.getMessage());
	}
	return currentTime;
    }

    public static String getCurrentTime() {
	return getCurrentTime("ddMMyyyyHHmmssSSS");
    }

}
