package google.gui.pages;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

import io.qameta.allure.Step;
import utils.BrowserActions;
import utils.ElementActions;

public class GoogleHomePage {
    // Variables
    private WebDriver driver;
    
    private String pageTitle = "Google";
    private String url = "https://www.google.com/ncr";
    
    // Locators
    private static final By google_search_bar = By.name("q");

    // Constructor
    public GoogleHomePage(WebDriver driver) {
	this.driver = driver;
    }
    
    //////////////////// Actions \\\\\\\\\\\\\\\\\\\\\

    public void openURL() {
	BrowserActions.navigateToUrl(driver, url);
    }

    @Step("Google Search: [{searchData}]")
    public void googleSearch(String searchData) {
	ElementActions.type(driver, google_search_bar, searchData);
	ElementActions.clickKeyboardKey(driver, google_search_bar, Keys.ENTER);
    }

    //////////////////// Validations \\\\\\\\\\\\\\\\\\\\\
    
    @Step("Assert on Page Title")
    public void assertOnPageTitle() {
	assertTrue(BrowserActions.getPageTitle(driver).equals(pageTitle));
	assertEquals(BrowserActions.getPageTitle(driver), pageTitle);
    }

}
