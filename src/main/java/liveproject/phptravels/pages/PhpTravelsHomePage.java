package liveproject.phptravels.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import io.qameta.allure.Step;

public class PhpTravelsHomePage {
    private WebDriver driver;

    // Constructor
    public PhpTravelsHomePage(WebDriver driver) {
	this.driver = driver;
    }

    // Elements
    private By hotels_button = By.xpath("//div[@class = 'menu-horizontal-wrapper-02']//a[contains(text(),'Hotels')]");

    // Methods
    @Step("Searching for Hotels")
    public PhpTravelsHomePage hotelsSearch() {
	driver.findElement(hotels_button).click();
	return this;
    }

}
