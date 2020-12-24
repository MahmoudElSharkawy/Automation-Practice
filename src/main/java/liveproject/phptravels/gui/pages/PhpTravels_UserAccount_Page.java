package liveproject.phptravels.gui.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import io.qameta.allure.Step;
import utils.Logger;

public class PhpTravels_UserAccount_Page {
    private WebDriver driver;

    // Constructor
    public PhpTravels_UserAccount_Page(WebDriver driver) {
	this.driver = driver;
    }

    // Elements
    private By hi_text = By.xpath("//*[@style='margin-left: 17px']");

    // Methods
    @Step("Get the text of the Hi message")
    public String getHiMessage() {
	String m = driver.findElement(hi_text).getText(); 
	Logger.logMessage("The Hi message is: " + m);
	return m;
    }
    
}
