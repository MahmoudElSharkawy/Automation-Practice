package examples.selenium;

import static org.testng.Assert.assertEquals;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class WindowsAndTabsManagementDemo {
    WebDriver driver;

    @BeforeMethod
    public void setUp() {
	WebDriverManager.chromedriver().setup();
	driver = new ChromeDriver();
	driver.get("http://automationpractice.com/index.php");
	System.out.println("Title: " + driver.getTitle());
    }

    @Test
    public void testNewWindowTab() {
	WebDriver newWindow = driver.switchTo().newWindow(WindowType.WINDOW);
	newWindow.get("http://automationpractice.com/index.php?controller=prices-drop");
	System.out.println("Title: " + driver.getTitle());
	assertEquals(driver.getTitle(), "Prices drop - My Store");
    }

    @Test
    public void testWorkingInBothWindowTabs() {
	// Automatically Open & Switch To The New Window Or Tab
	String mainWindowHandle = driver.getWindowHandle();
	driver.switchTo().newWindow(WindowType.TAB)
		.get("http://automationpractice.com/index.php?controller=authentication&back=my-account");
	System.out.println("Title: " + driver.getTitle());
	assertEquals(driver.getTitle(), "Login - My Store");

	// Work In The New Window Or Tab
	driver.findElement(By.id("email_create")).sendKeys("Selenium4@TAU.com");
	driver.findElement(By.id("SubmitCreate")).click();

//	// Get The Window ID Handles
//	Set<String> allWindowTabs = driver.getWindowHandles();
//	Iterator<String> iterate = allWindowTabs.iterator();
//	String mainFirstWindow = iterate.next();
//	// Switch & Work In The Main Window Or Tab
//	driver.switchTo().window(mainFirstWindow);
	driver.switchTo().window(mainWindowHandle);
	driver.findElement(By.id("search_query_top")).sendKeys("Shirt");
	driver.findElement(By.name("submit_search")).click();
	System.out.println("Title: " + driver.getTitle());
	assertEquals(driver.getTitle(), "Search - My Store");
    }

    @AfterMethod
    public void teardown() {
	driver.quit();
    }
}