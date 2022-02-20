package phptravels.gui.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import io.qameta.allure.Step;
import utils.ElementActions;

public class DashboardMenu {
    private WebDriver driver;

    // Elements Locators
    private final By dashboardMenu_button = By.id("drawerToggle");
//    private final By navigation_body = By.xpath("//body[contains(@class,'nav-fixed')]");
    private final By dashboardMenu = By.className("drawer-menu");
    private final By Accounts_linkText = By.xpath("//a[contains(.,'Accounts')]");
    private final By Admins_linkText = By.xpath("//a[contains(.,'Admins')]");
    private final By locationsMenu_linkText = By.xpath("//a[@data-bs-target='#Locations']");
    private final By locations_linkText = By.xpath("//nav[@id='Locations']//a[contains(.,'Locations')]");

    // Constructor
    public DashboardMenu(WebDriver driver) {
	this.driver = driver;
    }

    //////////////////////////////////////////////////////////////////
    //////////////////////////// Actions ////////////////////////////

    @Step("Navigate to Admin Page")
    public void navigateToAdminsPage() {
	openDashboardIfClosed();
	new ElementActions(driver)
	.click(Accounts_linkText)
	.click(Admins_linkText);
    }
    
    @Step("Navigate to Locations Page")
    public void navigateToLocationsPage() {
	openDashboardIfClosed();
	new ElementActions(driver)
	.click(locationsMenu_linkText)
	.click(locations_linkText);
    }
    
    private void openDashboardIfClosed() {
//	!ElementActions.getAttributeValue(driver, navigation_body, "class").contains("drawer-toggled")
	if (!driver.findElement(dashboardMenu).isDisplayed()) {
	    ElementActions.click(driver, dashboardMenu_button);
	}
    }

}
