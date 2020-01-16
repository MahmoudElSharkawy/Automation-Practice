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

    @BeforeTest
    public void setup() {
	System.setProperty("webdriver.chrome.driver", "src/main/resources/drivers/windows-64/chromedriver.exe");

	driver = new ChromeDriver();
	driver.manage().window().maximize();

	driver.get(FileDataReader.readFromExcel("DataDrivenDemoTestData", 2));
    }

    @Test
    public void dataDrivenTest() {
	driver.findElement(By.xpath(FileDataReader.readFromExcel("DataDrivenDemoTestData", 3)))
		.sendKeys(FileDataReader.readFromExcel("DataDrivenDemoTestData", 4), Keys.ENTER);
	assertTrue(driver.getTitle().contains(FileDataReader.readFromExcel("DataDrivenDemoTestData", 4)));

    }

    @AfterTest
    public void closeBrowser() {
	driver.quit();
    }
}
