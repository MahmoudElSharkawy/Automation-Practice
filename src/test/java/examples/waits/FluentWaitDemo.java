package examples.waits;

import static org.testng.Assert.assertTrue;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class FluentWaitDemo {
    WebDriver driver;

    @BeforeTest
    public void setUp() {
	System.setProperty("webdriver.chrome.driver", "src/main/resources/drivers/windows-64/chromedriver.exe");

	driver = new ChromeDriver();
	driver.manage().window().maximize();

	driver.get("http://cookbook.seleniumacademy.com/AjaxDemo.html");
    }

    @Test
    public void testFluentWait() {

	FluentWait wait = new FluentWait(driver)
		.withTimeout(Duration.ofSeconds(5))
		.pollingEvery(Duration.ofSeconds(1))
		.ignoring(NoSuchElementException.class);

	wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.id("loading"))));
	
	Assert.assertEquals(driver.findElement(By.id("finish")).getText(), "Hello World!");

    }

    @AfterTest
    public void tearDown() {
	driver.quit();
    }

}
