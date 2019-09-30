package pom.tests;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import pom.base.BaseTests;
import pom.pages.GoogleSearchResultsPage;

public class googleSearchTest extends BaseTests {
	WebDriver driver;
	GoogleSearchResultsPage googleSearchResults;
	
	@Test
	public void testingGoogleSearch() {
		String searchIndex = "[1]";
		String searchData = "Selenium WebDriver";
		googleSearchResults = homePage.googleSearch(searchData);
		googleSearchResults.assertOnPageTitle(searchData);
		googleSearchResults.assertOnSearchResult(searchData, searchIndex);
		googleSearchResults.clickOnSearchResult(searchIndex);
	}
}
