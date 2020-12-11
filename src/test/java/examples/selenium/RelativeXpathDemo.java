package examples.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;


public class RelativeXpathDemo {
	WebDriver driver;

	@BeforeTest
	public void setup() {
		System.setProperty("webdriver.chrome.driver", "src/main/resources/drivers/windows-64/chromedriver.exe");

		driver = new ChromeDriver();
		driver.manage().window().maximize();

		driver.get("https://www.google.com/ncr");
	}

	@Test
	public void SearchAndAssertTheFirstResultText() {
		driver.findElement(By.xpath("//input[@name='q']")).sendKeys("Selenium", Keys.ENTER);
		
		String firstSearchResultText = driver.findElement(By.xpath("(//h3[@class='LC20lb'])" + "[" + 1 + "]")).getText();
		Assert.assertTrue(firstSearchResultText.contains("Selenium"));
	}

	@AfterTest
	public void closeBrowser(){
		driver.quit();
	}

}
