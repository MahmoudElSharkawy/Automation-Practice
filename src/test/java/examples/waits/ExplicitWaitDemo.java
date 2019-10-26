package examples.waits;

import static org.testng.Assert.assertTrue;


import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;


public class ExplicitWaitDemo {
	WebDriver driver;

	@BeforeTest
	public void setup() {
		System.setProperty("webdriver.chrome.driver", "src/main/resources/drivers/windows-64/chromedriver.exe");

		driver = new ChromeDriver();
		driver.manage().window().maximize();

		driver.get("https://www.google.com/ncr");
	}

	@Test
	public void testExplicityWait() 
	{
		driver.findElement(By.xpath("//input[@name='q']")).sendKeys("Selenium", Keys.ENTER);

		WebDriverWait wait = new WebDriverWait(driver, 20); 
		wait.until(ExpectedConditions.titleContains("Selenium"));
		assertTrue(driver.getTitle().startsWith("Selenium"));
	}

	@AfterTest
	public void closeBrowser(){
		driver.quit();
	}

}
