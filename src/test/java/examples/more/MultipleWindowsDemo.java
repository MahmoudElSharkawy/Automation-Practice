package examples.more;

import static org.testng.Assert.assertEquals;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class MultipleWindowsDemo {
	
	WebDriver driver;
	
	@BeforeTest
	public void setup() {
		System.setProperty("webdriver.chrome.driver", "src/main/resources/drivers/windows-64/chromedriver.exe");

		driver = new ChromeDriver();
		driver.manage().window().maximize();

		driver.get("https://the-internet.herokuapp.com/checkboxes");
	}
	
	@Test
	public void testWindowUsingName()
	{
		// Store WindowHandle of parent window
		String currentWindowID = driver.getWindowHandle();
		driver.findElement(By.id("helpbutton")).click();
		driver.switchTo().window("HelpWindow");
		assertEquals("Help", driver.getTitle());
		System.out.println(driver.getTitle());
		// code inside Help windows 
		driver.close();
		driver.switchTo().window(currentWindowID);

	}
	
	@AfterTest
	public void closeBrowser() {
		driver.quit();
	}	
}
