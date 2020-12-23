package liveproject.phptravels.gui.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import io.qameta.allure.Step;

public class PhpTravels_SignUpPage {
    private WebDriver driver;

    // Constructor
    public PhpTravels_SignUpPage(WebDriver driver) {
	this.driver = driver;
    }

    // Elements
    private By hotels_button = By.xpath("//div[@class = 'menu-horizontal-wrapper-02']//a[contains(text(),'Hotels')]");

    // Methods
    @Step("Searching for Hotels")
    public PhpTravels_SignUpPage hotelsSearch() {
	driver.findElement(hotels_button).click();
	return this;
    }

}
