package pom.fluent;

import static org.testng.Assert.assertEquals;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;
import utils.SeleniumEventReporter;

public class GoogleSearchTest {
    EventFiringWebDriver driver;

    @BeforeClass
    public void setup() {
	WebDriverManager.chromedriver().setup();
	driver = new EventFiringWebDriver(new ChromeDriver());
	driver.register(new SeleniumEventReporter());
	driver.manage().window().maximize();
	new GoogleHomePage(driver).openURL();

    }

    @Test
    public void TestGoogleSearch() {
	assertEquals(new GoogleHomePage(driver)
		.googleSearch("Selenium WebDriver")
		.getSearchResultText("1"),
		"WebDriver :: Documentation for Selenium");
    }

    @AfterClass
    public void quitWebDriver() {
	driver.quit();
    }
}
