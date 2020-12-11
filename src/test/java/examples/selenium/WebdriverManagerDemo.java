package examples.selenium;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class WebdriverManagerDemo {
	
	WebDriver driver;
	
	@BeforeTest
	public void setup() {
      WebDriverManager.chromedriver().setup();
      driver = new ChromeDriver();
//		WebDriverManager.firefoxdriver().setup();
//		driver = new FirefoxDriver();
//		WebDriverManager.iedriver().setup();
//		driver = new InternetExplorerDriver();
	}
	
  @Test
  public void webDriverManagerTest() {
	  driver.get("http://www.google.com/ncr");
  }
}
