package examples.waits;

import static org.testng.Assert.assertTrue;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class ExplicitWaitDemo {
    WebDriver driver;

    @BeforeTest
    public void setUp() {
	System.setProperty("webdriver.chrome.driver", "src/main/resources/drivers/windows-64/chromedriver.exe");

	driver = new ChromeDriver();
	driver.manage().window().maximize();

	driver.get("http://the-internet.herokuapp.com/dynamic_loading");
    }

    @Test
    public void testExplicitWait() {
	    driver.findElement(By.cssSelector("#start button")).click();

	    WebDriverWait wait = new WebDriverWait(driver, 5);
	    wait.until(ExpectedConditions.invisibilityOf(
	            driver.findElement(By.id("loading"))));
	    
	    Assert.assertEquals(driver.findElement(By.id("finish")).getText(), "Hello World!");
    }

    @AfterTest
    public void tearDown() {
	driver.quit();
    }

}
