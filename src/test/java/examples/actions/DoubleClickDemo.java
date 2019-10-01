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
		System.setProperty("webdriver.chrome.driver", "src/main/resources/drivers/mac-64/chromedriver");

		driver = new ChromeDriver();
		driver.manage().window().maximize();

		driver.get("http://cookbook.seleniumacademy.com/DoubleClickDemo.html");
	}

	@Test
	public void DoubleClick(){

		WebElement box = driver.findElement(By.id("message"));

		String backGroundColor = box.getCssValue("background-color");
		System.out.println(box.getCssValue("background-color"));
		assertEquals(backGroundColor, "rgba(0, 0, 255, 1)");

		//Double Click
		Actions a = new Actions(driver);
		a.doubleClick(box).perform();

		//get the value to asset on it
		System.out.println(backGroundColor);
		assertEquals(backGroundColor, "rgba(255, 255, 0, 1)");

	}

	@AfterTest
	public void closeBrowser() {
		driver.quit();
	}	

}
