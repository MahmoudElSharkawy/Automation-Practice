package examples.selenium;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class JavaScriptExecutorDemo {

    WebDriver driver;

    @BeforeTest
    public void javaScriptExecutorTest() {
	WebDriverManager.chromedriver().setup();
	driver = new ChromeDriver();
	driver.manage().window().maximize();

	driver.get("https://www.softwaretestingmaterial.com/javascriptexecutor-selenium-webdriver/");
    }

    @Test
    public void jsDemo() {
	JavascriptExecutor js = (JavascriptExecutor) driver;
	// Vertical scroll - down by 500 pixels
//		js.executeScript("window.scrollBy(0,3000)");
	// for scrolling till the bottom of the page we can use the code like
	js.executeScript("window.scrollBy(0,document.body.scrollHeight)");
    }

    @AfterTest
    public void closeBrowser() {
	driver.quit();
    }
}
