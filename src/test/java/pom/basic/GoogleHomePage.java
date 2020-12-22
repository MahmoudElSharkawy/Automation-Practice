package pom.basic;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

public class GoogleHomePage {
    WebDriver driver;

    private String url = "https://www.google.com/ncr";
    private final static By google_search_bar = By.name("q");

    public GoogleHomePage(WebDriver driver) {
	this.driver = driver;
    }

    public void openURL() {
	driver.get(url);
    }

    public void googleSearch(String searchData) {
	driver.findElement(google_search_bar).sendKeys(searchData, Keys.ENTER);
    }

    public String getTitleText() {
	return driver.getTitle();
    }

}
