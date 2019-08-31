package examples.testng;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class TestNGAssertionsDemo {

	WebDriver driver;

	@BeforeTest
	public void setup() {
		System.setProperty("webdriver.chrome.driver", "src/main/resources/drivers/windows-64/chromedriver.exe");

		driver = new ChromeDriver();
		driver.manage().window().maximize();

		driver.get("https://www.google.com/?ncr");
	}

	@Test
	public void getURL() {
		System.out.println(driver.getCurrentUrl());
		assertEquals(driver.getCurrentUrl(), "https://www.google.com/?ncr");
	}

	@Test
	public void getTitle() {
		System.out.println(driver.getTitle());
		assertTrue(driver.getTitle().contains("Goo"));
	}

	@Test
	public void getTextfromBtn() {
		String searchBtnText = driver.findElement(By.name("btnK")).getText();
		assertTrue(searchBtnText.contains("goo"));
	}

	@AfterTest
	public void closeBrowser() {
		driver.quit();
	}
}
