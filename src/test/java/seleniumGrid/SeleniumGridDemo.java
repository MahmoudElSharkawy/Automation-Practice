package seleniumGrid;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class SeleniumGridDemo {
	WebDriver driver;

	@BeforeTest
	public void setup() {
		System.setProperty("webdriver.gecko.driver", "src/main/resources/drivers/windows-64/geckodriver.exe");

		DesiredCapabilities capability = DesiredCapabilities.firefox();
//		driver = new ChromeDriver();
		try {
			driver = new RemoteWebDriver(new URL("http://localhost:4444/wb/hub"), capability);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		driver.manage().window().maximize();

		driver.get("http://www.google.com/ncr");
	}

	@Test
	public void seleniumGridTest() {
		// set the implicit wait time to 20 Seconds
		driver.findElement(By.name("q")).sendKeys("Selenium Grid", Keys.ENTER);
	}

	@AfterTest
	public void closeBrowser() {
		driver.quit();
	}

}
