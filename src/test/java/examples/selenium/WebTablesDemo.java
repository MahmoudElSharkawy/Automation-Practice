package examples.selenium;

import static org.testng.Assert.assertEquals;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class WebTablesDemo {

    WebDriver driver;

    @BeforeTest
    public void setup() {
	WebDriverManager.chromedriver().setup();
	driver = new ChromeDriver();
	driver.manage().window().maximize();

	driver.get("https://the-internet.herokuapp.com/tables");
    }

    @Test
    public void webTable() {

	WebElement wt = driver.findElement(By.id("table1"));

	// get all rows
	List<WebElement> rows = wt.findElements(By.tagName("tr"));
	assertEquals(5, rows.size());

	// get all cells data
	for (WebElement row : rows) {
	    List<WebElement> cols = row.findElements(By.tagName("td"));
	    for (WebElement col : cols) {
		System.out.println(col.getText());
	    }
	    System.out.println();
	}

    }

    @AfterTest
    public void closeBrowser() {
	driver.quit();
    }
}
