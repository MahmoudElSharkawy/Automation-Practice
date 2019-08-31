package pom.tests;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class NotPOM {
	
	WebDriver driver;
	
	@BeforeClass
	public void setup() {
		System.setProperty("webdriver.chrome.driver", "src/main/resources/drivers/windows-64/chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();

	}
	
  @Test
  public void googleSearchTest() {
	  	String searchData = "Selenium WebDriver";
		driver.navigate().to("https://www.google.com/ncr");
		driver.findElement(By.name("q")).sendKeys(searchData, Keys.ENTER);
		assertTrue(driver.getTitle().contains(searchData));
		WebElement firstSearchResult = driver.findElement(By.xpath("(//h3[@class='LC20lb'])[1]"));
		assertEquals(firstSearchResult.getText(), searchData);
		firstSearchResult.click();
  }
}
