package examples.datadriven;

import static org.testng.Assert.assertTrue;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class DataReaderDemo {

	WebDriver driver;
	FileDataReader dataReader;

	@BeforeTest
	public void setup() {
		System.setProperty("webdriver.chrome.driver", "src/main/resources/drivers/windows-64/chromedriver.exe");

		driver = new ChromeDriver();
		driver.manage().window().maximize();

		dataReader = new FileDataReader();
		driver.get(dataReader.read("DataDrivenDemoTestData.xlsx", 2, 3));
	}

	@Test
	public void dataDrivenTest() {
		dataReader = new FileDataReader();

		driver.findElement(By.xpath(dataReader.read("DataDrivenDemoTestData.xlsx", 2, 1)))
				.sendKeys(dataReader.read("DataDrivenDemoTestData.xlsx", 2, 2), Keys.ENTER);
		assertTrue(driver.getTitle().contains(dataReader.read("DataDrivenDemoTestData.xlsx", 2, 2)));

	}

	@AfterTest
	public void closeBrowser() {
		driver.quit();
	}
}
