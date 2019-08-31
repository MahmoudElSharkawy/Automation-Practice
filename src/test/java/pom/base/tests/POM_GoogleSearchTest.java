package pom.base.tests;



import java.io.IOException;

import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import base.BrowserFactory;
import base.BrowserActions;
import base.FileDataReader;
import pom.base.pages.POM_GoogleHomePage;
import pom.base.pages.POM_GoogleSearchResultsPage;


public class POM_GoogleSearchTest {

	FileDataReader dataReader;
	POM_GoogleHomePage homePage;
	POM_GoogleSearchResultsPage srchResults;

	@BeforeClass
	public void setup() throws IOException {
		dataReader = new FileDataReader();

		BrowserFactory.browser(dataReader.read("POM_GoogleSearchTestData.xlsx", 1, 0));
		
		homePage = new POM_GoogleHomePage();
		srchResults = new POM_GoogleSearchResultsPage();
	}

	@Test
	public void NavigateToGoogleAndAssertPageTitle(){
		BrowserActions.openURL(homePage.url());
		homePage.assertPagetitle();
//		fail("hwa kda");
	}

	@Test(dependsOnMethods = {"NavigateToGoogleAndAssertPageTitle"})
	public void SearchAndAssertTheFirstResultText() throws IOException {
		homePage.inputSearch();
		srchResults.assertResultText(1, dataReader.read("POM_GoogleSearchTestData.xlsx",1 , 2));

	}

	@AfterMethod
	public void takeScreenShot(ITestResult result) {
		if (result.getStatus() == ITestResult.FAILURE) {
			String testCaseName = result.getName();
				BrowserActions.takeScreenShot(testCaseName);
		}
	}

	@AfterClass
	public void closeBrowser(){
		BrowserActions.close();
	}

}
