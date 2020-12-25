package examples.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CheckBoxsDemo {

    WebDriver driver;

    @BeforeTest
    public void setup() {
	WebDriverManager.chromedriver().setup();
	driver = new ChromeDriver();
	driver.manage().window().maximize();

	driver.get("https://the-internet.herokuapp.com/checkboxes");
    }

    @Test
    public void checkBoxesTest() {

	WebElement cb = driver.findElement(By.xpath("//input[@type = 'checkbox'][1]"));
	cb.click();

	WebElement cb2 = driver.findElement(By.xpath("//input[@type = 'checkbox'][2]"));
//		cb2.click();

	if (!(cb2.isSelected())) {
	    cb2.click();
	}

    }

    @AfterTest
    public void closeBrowser() {
	driver.quit();
    }
}
