package examples.testng;

import static org.testng.Assert.fail;

import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TestNGParametersDemo {

    ChromeDriver driver;

    @BeforeTest
    public void setup() {
	WebDriverManager.chromedriver().setup();
	driver = new ChromeDriver();
	driver.manage().window().maximize();

	driver.get("https://www.google.com/ncr");
    }

    @Test(priority = 2)
    public void getTitle() {
	System.out.println("Page Title: " + driver.getTitle());
    }

    @Test(priority = 1)
    public void getURL() {
	System.out.println("Page URL: " + driver.getCurrentUrl());
    }

    @Test(priority = 3)
    public void failedCase() {
	System.out.println("Failed Case");
	fail("hwa kda :P");
    }

    @Test(priority = 3, dependsOnMethods = { "failedCase" })
    public void skippedCase() {
	System.out.println("Skipped Case that depends of other method");
    }

    @Test(enabled = false)
    public void disabledCase() {
	System.out.println("Disabled Case");
    }

    @AfterTest
    public void quitBrowser() {
	driver.quit();
    }

}
