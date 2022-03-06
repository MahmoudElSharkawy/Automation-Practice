package examples.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.Rectangle;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class ElementPositionWithGetRectDemo {
    WebDriver driver;

    @BeforeClass
    public void setUp() {
	WebDriverManager.chromedriver().setup();
	driver = new ChromeDriver();
	driver.manage().window().maximize();
	driver.get("https://testautomationu.applitools.com/learningpaths.html");
    }

    @Test
    public void getPositionDimension() {
	WebElement logoTAU = driver.findElement(By.xpath("//div[@id='app']//header/a/img"));
	Rectangle rectTAULogo = logoTAU.getRect();
	System.out.println("x: " + rectTAULogo.getX());
	System.out.println("y: " + rectTAULogo.getY());
	System.out.println("Width: " + rectTAULogo.getWidth());
	System.out.println("Height: " + rectTAULogo.getHeight());
    }

    @AfterClass
    public void tearDown() {
	driver.quit();
    }
}
