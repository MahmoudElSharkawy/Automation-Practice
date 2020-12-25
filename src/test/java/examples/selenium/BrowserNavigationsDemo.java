package examples.selenium;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BrowserNavigationsDemo {

    WebDriver driver;

    @BeforeClass
    public void setup() {
	WebDriverManager.chromedriver().setup();
	driver = new ChromeDriver();
	driver.manage().window().maximize();

	driver.get("http://toolsqa.com/");
    }

    @Test(priority = 1)
    public void backTest() throws InterruptedException {

	System.out.println("First URL: " + driver.getCurrentUrl());

	driver.navigate().to("http://toolsqa.com/selenium-tutorial/");
	System.out.println("After navigation URL: " + driver.getCurrentUrl());
	Thread.sleep(2000);
	driver.navigate().back();
	System.out.println("Back URL: " + driver.getCurrentUrl());
	Thread.sleep(2000);

    }

    @Test(priority = 2)
    public void forwardTest() throws InterruptedException {
	driver.navigate().forward();
	System.out.println("Forward URL: " + driver.getCurrentUrl());
	Thread.sleep(2000);

    }

    @Test(priority = 3)
    public void refreshTest() throws InterruptedException {
	driver.navigate().refresh();
	System.out.println("Refresh URL: " + driver.getCurrentUrl());
	Thread.sleep(2000);

    }

    @AfterClass
    public void closeBrowser() {
	driver.quit();
    }
}
