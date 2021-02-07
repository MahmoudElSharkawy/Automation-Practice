package examples.testng;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TestNGAssertionsDemo {

    WebDriver driver;

    @BeforeTest
    public void setup() {
	WebDriverManager.chromedriver().setup();
	driver = new ChromeDriver();
	driver.manage().window().maximize();

	driver.get("https://www.google.com/?ncr");
    }

    @Test
    public void getURL() {
	System.out.println(driver.getCurrentUrl());
	assertEquals(driver.getCurrentUrl(), "https://www.google.com/?ncr");
    }

    @Test
    public void getTitle() {
	System.out.println(driver.getTitle());
	assertTrue(driver.getTitle().contains("Goo"));
    }

    @AfterTest
    public void closeBrowser() {
	driver.quit();
    }
}
