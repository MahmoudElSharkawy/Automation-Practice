package pom.fluent.tests;

import static org.testng.Assert.assertEquals;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import engine.BrowserFactory;
import engine.BrowserFactory.BrowserType;
import pom.fluent.pages.GoogleHomePage;

public class GoogleSearchTest {
    private WebDriver driver;

    @BeforeClass
    public void setup() {
	driver = BrowserFactory.openBrowser(BrowserType.GOOGLE_CHROME);
    }

    @BeforeMethod
    public void beforemethod() {
	driver = new ChromeDriver();
	new GoogleHomePage(driver).openURL();
    }

    @Test
    public void TestGoogleSearch() {
	assertEquals(new GoogleHomePage(driver).googleSearch("Selenium WebDriver").getSearchResultText(),
		"What is Selenium WebDriver? Difference with RC - Guru99");
    }

    @Test
    public void TestGoogleSearchAndClickOnTheFirstResult() {
	new GoogleHomePage(driver).googleSearch("Selenium WebDriver").clickOnSearchResult();

    }

    @AfterMethod
    public void quitWebDriver() {
	driver.quit();
    }
}
