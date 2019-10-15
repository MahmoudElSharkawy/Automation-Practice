package pom.simple.tests;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;
import pom.simple.pages.GoogleHomePage;
import pom.simple.pages.GoogleSearchResultsPage;

public class GoogleSearchTest {
	private WebDriver driver;
	
	GoogleHomePage homePage;
	GoogleSearchResultsPage searchResultsPage;
	
	@BeforeClass
	public void initWebDriver() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		
		homePage = new GoogleHomePage();
		searchResultsPage = new GoogleSearchResultsPage();
	}

	@Test
	public void TestGoogleSearch() {
		homePage.openURL(driver);
		assertTrue(homePage.getTitleText(driver).equals("Google"));
		assertEquals(homePage.getTitleText(driver), "Google");
		homePage.googleSearch(driver, "Selenium WebDriver");
		
		assertTrue(searchResultsPage.getTitleText(driver).contains("Selenium WebDriver"));
		assertEquals(searchResultsPage.getSearchResultText(driver), "Selenium WebDriver");
		searchResultsPage.clickOnSearchResult(driver);
		
	}

	@AfterClass
	public void quitWebDriver() {
		driver.quit();
	}
}
