package utils;

import java.io.File;
import java.io.IOException;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import com.google.common.io.Files;
import io.qameta.allure.Attachment;
import io.qameta.allure.Step;

public class Logger {

    @Step("Console Log Message: [{message}]")
    public static void logMessage(String message) {
	System.out.println(message);
	ExtentReport.info(message);
    }

    @Attachment(value = "Page Screenshot", type = "image/png")
    public static byte[] attachScreenshot(WebDriver driver) {
	try {
	    File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
	    return Files.toByteArray(screenshot);
	} catch (IOException e) {
	    return null;
	}
    }

    @Step("The Test Case Failed; Attach A Screenshot.....")
    public static void attachScreenshotInCaseOfFailure(WebDriver driver) {
	attachScreenshot(driver);
    }

    @Attachment(value = "API Request", type = "text/json")
    public static byte[] attachApiRequest(byte[] b) {
	return attachTextJson(b);
    }

    @Attachment(value = "API Response", type = "text/json")
    public static byte[] attachApiResponse(byte[] b) {
	return attachTextJson(b);
    }
    
//  @Attachment(type = "text/json")
  public static byte[] attachTextJson(byte[] b) {
	try {
	    return b;
	} catch (Exception e) {
	    return null;
	}
  }

}
