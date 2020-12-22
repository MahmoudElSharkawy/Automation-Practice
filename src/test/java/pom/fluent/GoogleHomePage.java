package pom.fluent;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

public class GoogleHomePage {
    private WebDriver driver;

    private String url = "https://www.google.com/ncr";
    protected By google_search_bar = By.name("q");

    public GoogleHomePage(WebDriver driver) {
	this.driver = driver;
    }

    public GoogleHomePage openURL() {
	driver.get(url);

	return this;
    }

    public String getTitleText() {
	return driver.getTitle();
    }

    public GoogleSearchResultsPage googleSearch(String searchData) {
	driver.findElement(google_search_bar).sendKeys(searchData, Keys.ENTER);
	return new GoogleSearchResultsPage(driver);
    }

}
