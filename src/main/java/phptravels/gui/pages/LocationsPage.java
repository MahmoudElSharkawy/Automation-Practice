package phptravels.gui.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import io.qameta.allure.Step;
import utils.BrowserActions;
import utils.ElementActions;
import utils.ElementActions.SelectType;

public class LocationsPage {
    private WebDriver driver;
    private String url = "https://phptravels.net/api/admin/locations";

    // Elements Locators
    private final By add_Button = By.xpath("//form[@class='add_button']//button");
    private final By countrySearch_span = By.xpath("//span[@class='select2-chosen']");
    private final By countrySearch_input = By.xpath("//div[@class='select2-search']//input");
    private final By searchResult_span(String countryName) {return By.xpath("//span[@class='select2-match' and contains(.,'" + countryName + "')]");}
    private final By city_input = By.name("city");
    private final By longitude_input = By.id("long");
    private final By latitude_input = By.id("lat");
    private final By status_select = By.name("status");
    private final By submit_button = By.xpath("//button[@type='submit']");
    private final By locationsTable_table = By.xpath("//tbody");
    private final By editLocation_button(String locationData) {return By.xpath("//td[contains(.,'" + locationData + "')]//parent::tr//a[@title='Edit']");}

    // Constructor
    public LocationsPage(WebDriver driver) {
	this.driver = driver;
    }

    //////////////////////////////////////////////////////////////////
    //////////////////////////// Actions ////////////////////////////

    @Step("Navigate to Admins Add page")
    public void navigateToLocationsPage() {
	BrowserActions.navigateToUrl(driver, url);
    }
    
    @Step("Add New Location")
    public void addNewLocation(String country, String city, String longitude, String latitude, String status) {
	new ElementActions(driver)
	.click(add_Button)
	.click(countrySearch_span)
	.type(countrySearch_input, country)
	.click(searchResult_span(country))
	.type(city_input, city)
	.type(longitude_input, longitude)
	.type(latitude_input, latitude)
	.select(status_select, SelectType.TEXT, status)
	.click(submit_button);
    }
    
    @Step("Update Location Details")
    public void updateLocationCity(String oldCity, String newCity) {
	new ElementActions(driver)
	.click(editLocation_button(oldCity))
	.type(city_input, newCity)
	.click(submit_button);
    }

    //////////////////////////////////////////////////////////////////
    /////////////////////////// Assertions //////////////////////////
    
    @Step("Assert That The Location data is added to the Table")
    public void assertThatLocationDataIsAddedToTheTable(String locationData) {
	Assert.assertTrue(ElementActions.getText(driver, locationsTable_table).contains(locationData),
		"The Location data [" + locationData + "] is not in the locations table");
    }

}
