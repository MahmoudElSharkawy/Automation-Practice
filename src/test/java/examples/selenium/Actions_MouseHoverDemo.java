package examples.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Actions_MouseHoverDemo {

    WebDriver driver;

    @BeforeTest
    public void setup() {
	WebDriverManager.chromedriver().setup();
	driver = new ChromeDriver();
	driver.manage().window().maximize();

	driver.get("http://automationpractice.com/index.php");
    }

    @Test
    public void mousehover() {
	Actions a = new Actions(driver);
	WebElement element = driver.findElement(By.linkText("Printed Chiffon Dress"));
	a.moveToElement(element).perform();
	driver.findElement(By.xpath("(//a[@data-id-product='7'])[1]//span")).click();

	a.contextClick(element);
    }

    @AfterTest
    public void closeBrowser() {
	driver.quit();
    }

}
