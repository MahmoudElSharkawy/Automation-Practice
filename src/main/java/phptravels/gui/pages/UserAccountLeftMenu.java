package phptravels.gui.pages;

import static org.testng.Assert.assertEquals;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import io.qameta.allure.Step;
import utils.ElementActions;

public class UserAccountLeftMenu {
    private WebDriver driver;

    // Constructor
    public UserAccountLeftMenu(WebDriver driver) {
	this.driver = driver;
    }

    // Elements
    private By userProfileName_text = By.xpath("//h4[@class='author__title']//strong");

    //////////////////////////////////////////////////////////////////////////
    //////////////////////////////// Actions ////////////////////////////////
    
    
    //////////////////////////////////////////////////////////////////////////
    ////////////////////////////// validations //////////////////////////////

    @Step("Assert on User Profile Name")
    public void assertOnUserProfileName(String expectedUserProfileName) {
	assertEquals(ElementActions.getText(driver, userProfileName_text), expectedUserProfileName);
    }

}