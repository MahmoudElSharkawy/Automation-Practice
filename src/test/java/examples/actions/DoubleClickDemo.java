package examples.actions;

import static org.testng.Assert.assertEquals;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class DoubleClickDemo {

	WebDriver driver;

	@BeforeTest
	public void setup() {
		System.setProperty("webdriver.chrome.driver", "src/main/resources/drivers/windows-64/chromedriver.exe");

		driver = new ChromeDriver();
		driver.manage().window().maximize();

		driver.get("http://cookbook.seleniumacademy.com/DoubleClickDemo.html");
	}

	@Test
	public void DoubleClick() throws InterruptedException{

		WebElement box = driver.findElement(By.id("message"));

		System.out.println(box.getCssValue("background-color"));
		assertEquals(box.getCssValue("background-color"), "rgba(0, 0, 255, 1)");

		//Double Click
		Actions a = new Actions(driver);
		a.doubleClick(box).perform();

		//get the value to asset on it
		System.out.println(box.getCssValue("background-color"));
		Thread.sleep(2000);
		assertEquals(box.getCssValue("background-color"), "rgba(255, 255, 0, 1)");

	}

	@AfterTest
	public void closeBrowser() {
		driver.quit();
	}	

}
