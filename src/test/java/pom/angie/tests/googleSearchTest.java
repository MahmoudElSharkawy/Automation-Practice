package pom.angie.tests;

import org.testng.annotations.Test;

import pom.angie.pages.GoogleSearchResultsPage;

public class googleSearchTest extends BaseTests {
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
