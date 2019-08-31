package pom.tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import pom.pages.GoogleHomePage;
import pom.pages.GoogleSearchResultsPage;

public class googleSearchTest {
	WebDriver driver;
	GoogleHomePage googleHomePageObject;
	GoogleSearchResultsPage googleSearchResultsObject;

	@BeforeClass
	public void setup() {
		System.setProperty("webdriver.chrome.driver", "src/test/resources/drivers/chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		
		googleHomePageObject = new GoogleHomePage();
		googleSearchResultsObject = new GoogleSearchResultsPage();
	}
	
	@Test
	public void googleSearchOnSeleniumWebDriver() {
		String searchData = "Selenium WebDriver";
		googleHomePageObject.navigateToGoogleHomePageURL(driver);
		googleHomePageObject.googleSearch(searchData, driver);
		googleSearchResultsObject.assertOnPageTitle(searchData, driver);
		googleSearchResultsObject.assertOnSearchResult(searchData, driver);
		googleSearchResultsObject.clickOnSearchResult(driver);
	}
}
