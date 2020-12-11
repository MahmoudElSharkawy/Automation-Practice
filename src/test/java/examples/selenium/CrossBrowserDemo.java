package examples.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class CrossBrowserDemo {

	WebDriver driver;

	@Parameters("browserName")
	@BeforeTest()
	public void setup(String browserName) {

		if (browserName.equalsIgnoreCase("chrome")) {
			System.out.println("Browser name: " + browserName);
			System.setProperty("webdriver.chrome.driver", "src/main/resources/drivers/windows-64/chromedriver.exe");
			driver = new ChromeDriver();
			driver.manage().window().maximize();
		}

		else if (browserName.equalsIgnoreCase("firefox")) {
			System.out.println("Browser name: " + browserName);
			System.setProperty("webdriver.gecko.driver", "src/main/resources/drivers/windows-64/geckodriver.exe");
			driver = new FirefoxDriver();
		}
	}

	@Test
	public void crossBrowserTest() {
		driver.get("https://www.google.com/ncr");
		driver.findElement(By.xpath("//input[@name='q']")).sendKeys("Selenium", Keys.ENTER);

	}

	@AfterTest
	public void closeBrowser() {
		driver.quit();
	}	
}
