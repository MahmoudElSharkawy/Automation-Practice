package pom.simple.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

public class GoogleHomePage {	
	private String url = "https://www.google.com/ncr";
	private final static By google_search_bar = By.name("q");
	
	public void openURL(WebDriver driver) {
		driver.get(url);
	}
	
    public void googleSearch(WebDriver driver, String searchData){
		driver.findElement(google_search_bar).sendKeys(searchData, Keys.ENTER);
    }
	
    public String getTitleText(WebDriver driver) {
    	return driver.getTitle();
    }

}
