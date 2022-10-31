package google.gui.pages;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import io.qameta.allure.Step;
import utils.BrowserActions;

public class GoogleSearchResultsPage {
    private WebDriver driver;

    private static final By googleSearchResult_h3 (String index) { 
	return By.xpath("(//h3[contains(@class,'LC20lb')])[" + index + "]");
    }

    public GoogleSearchResultsPage(WebDriver driver) {
	this.driver = driver;
    }
    
    //////////////////// Actions \\\\\\\\\\\\\\\\\\\\\

    @Step("Get Search Resutl Text for result number [{index}]")
    public String getSearchResultText(String index) {
	return driver.findElement(googleSearchResult_h3(index)).getText();
    }

    @Step("Click on Search Result for result number [{index}]")
    public void clickOnSearchResult(String index) {
	driver.findElement(googleSearchResult_h3(index)).click();
    }
    
    //////////////////// Validations \\\\\\\\\\\\\\\\\\\\\
    
    @Step("Assert on Page Title for search data: [{expectedPageTitle}]")
    public void assertOnPageTitle(String expectedPageTitle) {
	assertTrue(BrowserActions.getPageTitle(driver).equals(expectedPageTitle + " - Google Search"));
	assertEquals(BrowserActions.getPageTitle(driver), expectedPageTitle + " - Google Search");
    }
    
}
