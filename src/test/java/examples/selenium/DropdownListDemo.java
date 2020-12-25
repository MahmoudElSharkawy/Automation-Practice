package examples.selenium;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DropdownListDemo {

    WebDriver driver;

    @BeforeTest
    public void setup() {
	WebDriverManager.chromedriver().setup();
	driver = new ChromeDriver();
	driver.manage().window().maximize();

	driver.get("https://the-internet.herokuapp.com/dropdown");
    }

    @Test
    public void dropDownListTest() {

	// get the dropdown as a select using it's name attribute
	Select s = new Select(driver.findElement(By.id("dropdown")));

	// assert that it doesn't support multiple selection
	assertFalse(s.isMultiple());

	// Verify that it has 2 options only
	assertEquals(s.getOptions().size(), 3);

	// Select by visibleText
	s.selectByVisibleText("Option 1");
	assertEquals("Option 1", s.getFirstSelectedOption().getText());

	// Select by value
	s.selectByValue("2");
	assertEquals("Option 2", s.getFirstSelectedOption().getText());

	// Select by index
	s.selectByIndex(1);
	assertEquals("Option 1", s.getFirstSelectedOption().getText());

    }

    @AfterTest
    public void closeBrowser() {
	driver.quit();
    }

}
