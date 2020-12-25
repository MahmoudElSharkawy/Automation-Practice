package examples.selenium;

import static org.testng.Assert.assertEquals;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class FramesDemo {

    WebDriver driver;

    @BeforeTest
    public void setup() {
	WebDriverManager.chromedriver().setup();
	driver = new ChromeDriver();
	driver.manage().window().maximize();

	driver.get("https://the-internet.herokuapp.com/tinymce");
    }

    @Test
    public void framesTest() {
	// switch to the iframe
	driver.switchTo().frame("mce_0_ifr");
	// do whatever action needed
	WebElement textArea = driver.findElement(By.id("tinymce"));
	textArea.clear();
	textArea.sendKeys("Hello iFrame!");
	String text = textArea.getText();
	// exit the iframe to the main area
	driver.switchTo().parentFrame();
	// Assertion
	assertEquals(text, "Hello iFrame!", "WRONG!");
    }

    @AfterTest
    public void closeBrowser() {
	driver.quit();
    }
}
