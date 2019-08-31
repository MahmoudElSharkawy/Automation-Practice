package examples.pom.base.pages;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.testng.Assert;

import base.BrowserFactory;
import base.FileDataReader;

public class POM_GoogleHomePage {

	//Variables
	FileDataReader dataReader;
	String url = "https://www.google.com/ncr";
	String pageTitle = "Google";

	// Elements
	By google_search_bar = By.xpath("//input[@name='q']");

	
	// Methods
	public String url() {
		return url;
	}
	
	public void assertPagetitle(){
		String title = BrowserFactory.getWebDriver().getTitle();
		Assert.assertEquals(title, pageTitle);
	}
	
	public void inputSearch() throws IOException {
		dataReader = new FileDataReader();
		BrowserFactory.getWebDriver().findElement(google_search_bar).sendKeys(dataReader.read("POM_GoogleSearchTestData.xlsx",1 , 1), Keys.ENTER);
//		TestBase.getWebDriver().findElement(google_search_bar).submit();
	}

}
