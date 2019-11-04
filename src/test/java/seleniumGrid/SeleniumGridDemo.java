package seleniumGrid;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class SeleniumGridDemo {
	RemoteWebDriver driver;
	public static final String host = "localhost";
	public static final String port = "4444";
 
	@BeforeClass
	public void setup() {
		driver = null;
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability("browserName", "chrome");
		capabilities.setCapability("version", "78.0");
		capabilities.setCapability("platform", "win10"); // If this cap isn't specified, it will just get the any
		capabilities.setCapability("network", true); // To enable network logs
		capabilities.setCapability("visual", true); // To enable step by step screenshot
		capabilities.setCapability("video", true); // To enable video recording
		capabilities.setCapability("console", true); // To capture console logs
		try {
			driver = new RemoteWebDriver(new URL("https://" + host + ":" + port + "/wb/hub"), capabilities);
		} catch (Exception e) {
			System.out.println("Invalid grid URL" + e.getMessage());
		}

	}

	@Test
	public void seleniumGridTest() {
		driver.get("http://www.google.com/ncr");
		driver.findElement(By.name("q")).sendKeys("Selenium Grid", Keys.ENTER);
	}

	@AfterClass
	public void closeBrowser() {
		driver.quit();
	}

}
