package pom.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

public class GoogleHomePage {

	//variables
	private String googleHomePageURL = "https://www.google.com/ncr";
	
	//Elements
	private By google_search_bar = By.name("q");

	//Methods
	public void navigateToGoogleHomePageURL(WebDriver driver) {
		driver.navigate().to(googleHomePageURL);
	}
	public void googleSearch(String searchData, WebDriver driver) {
		driver.findElement(google_search_bar).sendKeys(searchData, Keys.ENTER);
	}

}
