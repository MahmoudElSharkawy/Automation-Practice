package engine;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

public class Utils {
    private static int TIMEOUT = 20;

    public static WebDriverWait getWait(WebDriver driver) {
	return new WebDriverWait(driver, TIMEOUT);

    }

    public static void takeScreenShotInCaseOfFailure(ITestResult result, WebDriver driver) {
	if (result.getStatus() == ITestResult.FAILURE) {
	    TakesScreenshot ts = (TakesScreenshot) driver;
	    File source = ts.getScreenshotAs(OutputType.FILE);
	    try {
		FileUtils.copyFile(source, new File("src/test/resources/ScreenShots/" + result.getName() + ".png"));
	    } catch (Exception e) {
		System.out.println(e.getMessage());
	    }
	}
    }

    public static void jsExecutor(WebDriver driver, String script, WebElement element) {
	JavascriptExecutor executor = (JavascriptExecutor) driver;
	executor.executeScript(script, element);

    }
}
