package nextlevel.liveproject.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;

public class GoogleHomePage {
	private WebDriver driver;

	//Constructor
    public GoogleHomePage(WebDriver driver){
        this.driver = driver;
    }
    
	//variables
	
	//Elements
	private By google_search_bar = By.name("q");

	//Method
    public GoogleSearchResultsPage googleSearch(String searchData){
	driver.findElement(google_search_bar).sendKeys(searchData, Keys.ENTER);
	return new GoogleSearchResultsPage(driver);
    }

}
