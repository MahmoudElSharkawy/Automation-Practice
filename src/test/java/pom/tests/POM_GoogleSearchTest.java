package pom.tests;


import java.io.IOException;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import base.BaseClass;
import base.BrowserActions;
import base.FileDataReader;
import pom.pages.POM_GoogleHomePage;
import pom.pages.POM_GoogleSearchResultsPage;


public class POM_GoogleSearchTest {
	
	FileDataReader dataReader;
	POM_GoogleHomePage homePage;
	POM_GoogleSearchResultsPage srchResults;
	
	@BeforeClass
	public void setup() throws IOException {
		dataReader = new FileDataReader();
		
		BaseClass.browser(dataReader.read("POM_GoogleSearchTestData.xlsx", 1, 0));
	}

	@Test
	public void NavigateToGoogleAndAssertPageTitle(){
		homePage = new POM_GoogleHomePage();
		
		BrowserActions.openURL(homePage.url());
		homePage.assertPagetitle();
		
	}
	
	@Test(dependsOnMethods = {"NavigateToGoogleAndAssertPageTitle"})
	public void SearchAndAssertTheFirstResultText() throws IOException {
		homePage = new POM_GoogleHomePage();
		srchResults = new POM_GoogleSearchResultsPage();
		
		homePage.inputSearch();
		srchResults.assertResultText(1, dataReader.read("POM_GoogleSearchTestData.xlsx",1 , 2));
		
	}
	
	@AfterClass
	public void closeBrowser(){
		BrowserActions.close();
	}
	
}
