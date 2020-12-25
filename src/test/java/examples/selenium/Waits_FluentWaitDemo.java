package examples.selenium;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Waits_FluentWaitDemo {
    WebDriver driver;

    @BeforeClass
    public void setUp() {
	WebDriverManager.chromedriver().setup();
	driver = new ChromeDriver();
	driver.manage().window().maximize();

	driver.get("http://the-internet.herokuapp.com/dynamic_loading");
    }

    @Test
    public void testFluentWait() {
	driver.findElement(By.linkText("Example 1: Element on page that is hidden")).click();
	driver.findElement(By.cssSelector("#start button")).click();

	FluentWait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(5))
		.pollingEvery(Duration.ofSeconds(1)).ignoring(NoSuchElementException.class);

	wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.id("loading"))));

	Assert.assertEquals(driver.findElement(By.id("finish")).getText(), "Hello World!");

    }

    @AfterClass
    public void tearDown() {
	driver.quit();
    }

}
