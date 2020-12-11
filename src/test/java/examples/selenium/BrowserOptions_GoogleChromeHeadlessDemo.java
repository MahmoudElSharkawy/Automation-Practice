package examples.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class BrowserOptions_GoogleChromeHeadlessDemo {

	WebDriver driver;

	@BeforeTest
	public void setup() {
		System.setProperty("webdriver.chrome.driver", "src/main/resources/drivers/windows-64/chromedriver.exe");

		ChromeOptions options = new ChromeOptions();
//		options.addArguments("--headless");
		options.setHeadless(true);
//      options.addArguments("disable-infobars");
//		options.setExperimentalOption("excludeSwitches", new String[] { "enable-automation" });
		options.addArguments("--window-size=1920,1080");
		driver = new ChromeDriver(options);

		driver.get("http://www.google.com/ncr");
	}

	@Test
	public void chromeHeadlessTest() {
		driver.findElement(By.xpath("//input[@name='q']")).sendKeys("Selenium", Keys.ENTER);
	}

	@AfterTest
	public void closeBrowser() {
		driver.quit();
	}
}
