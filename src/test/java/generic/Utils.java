package generic;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.JavascriptExecutor;

public class Utils {
    private static int TIMEOUT = 20;

    public static WebDriverWait getWait(WebDriver driver) {
	return new WebDriverWait(driver, TIMEOUT);

    }

    public static void jsExecutor(WebDriver driver, String script, WebElement element) {
	JavascriptExecutor executor = (JavascriptExecutor) driver;
	executor.executeScript(script, element);

    }
}
