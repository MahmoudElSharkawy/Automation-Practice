package examples.more;

import static org.testng.Assert.assertEquals;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class FramesDemo {
	
	WebDriver driver;
	
	@BeforeTest
	public void setup() {
		System.setProperty("webdriver.chrome.driver", "src/main/resources/drivers/windows-64/chromedriver.exe");

		driver = new ChromeDriver();
		driver.manage().window().maximize();

		driver.get("https://the-internet.herokuapp.com/tinymce");
	}
	
	@Test
	public void framesTest() {
		//switch to the iframe
		driver.switchTo().frame("mce_0_ifr");
		//do whatever action needed
		WebElement textArea = driver.findElement(By.id("tinymce"));
		textArea.clear();
		textArea.sendKeys("Hello iFrame!");
		String text = textArea.getText();
		//exit the iframe to the main area
		driver.switchTo().parentFrame();
		//Assertion
		assertEquals(text, "Hello iFrame!", "WRONG!");
	}
	
	@AfterTest
	public void closeBrowser() {
		driver.quit();
	}	
}
