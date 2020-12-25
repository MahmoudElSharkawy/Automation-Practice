package examples.selenium;

import static org.testng.Assert.fail;

import java.io.File;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TakeScreenShotDemo {

    WebDriver driver;

    @BeforeTest
    public void setup() {
	WebDriverManager.chromedriver().setup();
	driver = new ChromeDriver();
	driver.manage().window().maximize();

	driver.get("http://www.amazon.com");
    }

    @Test
    public void ScreenshotOnFailureTest() throws InterruptedException {
	WebElement searchTxt = driver.findElement(By.id("twotabsearchtextbox"));
	searchTxt.sendKeys("Selenium WebDriver Book");
	searchTxt.submit();
	fail("Failed of purpose to take the screenshot");
    }

    @AfterMethod
    public void takeScreenShot(ITestResult result) {
	if (result.getStatus() == ITestResult.FAILURE) {
	    TakesScreenshot ts = (TakesScreenshot) driver;
	    File source = ts.getScreenshotAs(OutputType.FILE);
	    try {
		FileUtils.copyFile(source, new File("src/test/resources/ScreenShots/" + result.getName() + ".png"));
	    } catch (Exception e) {
		e.printStackTrace();
	    }
	}
    }

    @AfterTest
    public void closeBrowser() {
	driver.quit();
    }
}
