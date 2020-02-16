package assignments.parabank.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

public class GooglePage {
	WebDriver driver;

    private String url = "https://www.google.com/ncr";
    private By google_search_bar = By.name("q");
    private By parabank_search_result = By.xpath("(//h3[contains(text(),'ParaBank - Parasoft')])[1]");
  

    public GooglePage(WebDriver driver) {
	this.driver = driver;
    }
    
    public void openURL() {
	driver.get(url);
    }
    
    public void googleSearch(String searchData) {
	driver.findElement(google_search_bar).sendKeys(searchData, Keys.ENTER);
    }
    
    public void clickOnParabankSearchResult() {
    	driver.findElement(parabank_search_result).click();
    }
    
}
