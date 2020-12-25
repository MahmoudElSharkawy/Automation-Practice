package examples.selenium;

import static org.testng.Assert.assertEquals;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class MultipleWindowsDemo {

    WebDriver driver;

    @BeforeTest
    public void setup() {
	WebDriverManager.chromedriver().setup();
	driver = new ChromeDriver();
	driver.manage().window().maximize();

	driver.get("http://cookbook.seleniumacademy.com/Config.html");
    }

    @Test
    public void multipleWindowsTest() {
	// Store WindowHandle of parent window
	String mainWindowHandle = driver.getWindowHandle();
	driver.findElement(By.id("helpbutton")).click();

	for (String winHandle : driver.getWindowHandles()) {
	    if (driver.switchTo().window(winHandle).getTitle().equals("Help")) {
		driver.switchTo().window(winHandle);

		System.out.println(driver.getTitle());
		assertEquals(driver.getTitle(), "Help");

		driver.close();
		driver.switchTo().window(mainWindowHandle);
		break;
	    }
	}
	System.out.println(driver.getTitle());
    }

    @AfterTest
    public void closeBrowser() {
	driver.quit();
    }
}
