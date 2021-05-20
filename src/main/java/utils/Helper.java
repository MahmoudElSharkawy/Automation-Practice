package utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Helper {
    private static final int TIMEOUT = Integer
	    .parseInt(PropertiesReader.getProperty("automationPractice.properties", "webdriver.wait"));

    public static WebDriverWait getExplicitWait(WebDriver driver) {
	return new WebDriverWait(driver, TIMEOUT);
    }

    public static void implicitWait(WebDriver driver) {
	driver.manage().timeouts().implicitlyWait(TIMEOUT, TimeUnit.SECONDS);
    }

//    public static String getCurrentTime(String dateFormat) {
//	return new SimpleDateFormat(dateFormat).format(new Date());
//    }

}
