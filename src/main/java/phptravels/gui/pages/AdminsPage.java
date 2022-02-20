package phptravels.gui.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import io.qameta.allure.Step;
import utils.BrowserActions;
import utils.ElementActions;

public class AdminsPage {
    private WebDriver driver;
    private String url = "https://phptravels.net/api/admin/accounts/admins";

    // Elements Locators
    private final By add_Button = By.xpath("//form[@class='add_button']//button");
    private final By firstName_input = By.name("fname");
    private final By lastName_input = By.name("lname");
    private final By email_input = By.name("email");
    private final By password_input = By.name("password");
    private final By mobile_input = By.name("mobile");
    private final By country_span = By.xpath("//span[contains(.,'Please Select')]");
    private final By countrySearch_input = By.xpath("//div[@class='select2-search']//input");
    private final By searchResult_span(String countryName) {return By.xpath("//span[@class='select2-match' and contains(.,'" + countryName + "')]");}
    private final By address1_input = By.name("address1");
    private final By address2_input = By.name("address2");
    private final By addLocationPermission_input = By.xpath("//input[@value='addlocationsRentals']");
    private final By editLocationPermission_input = By.xpath("//input[@value='editlocationsRentals']");
    private final By updateSettings_button = By.xpath("//button[contains(.,'Update Settings')]");
    private final By edit_icon(String email) {return By.xpath("//tr[contains(.,'"+ email + "')]//a[@title='Edit']");}
    private final By adminUsersTable_table = By.xpath("//tbody");
//    private final By save_header = By.xpath("//h4");
    
    // Constructor
    public AdminsPage(WebDriver driver) {
	this.driver = driver;
    }

    //////////////////////////////////////////////////////////////////
    /////////////////////////// Assertions //////////////////////////

    @Step("Navigate to Admins Add page")
    public void navigateToAdminsAddPage() {
	BrowserActions.navigateToUrl(driver, url + "/add");
    }

    @Step("Add Admin User")
    public void addAdminUser(String firstName, String lastName, String email, String password, String mobileNumber,
	    String country, String address1, String address2) {
	new ElementActions(driver)
	.click(add_Button)
	.type(firstName_input, firstName)
	.type(lastName_input, lastName)
	.type(email_input, email)
	.type(password_input, password)
	.type(mobile_input, mobileNumber)
	.click(country_span)
	.type(countrySearch_input, country)
	.click(searchResult_span(country))
	.type(address1_input, address1)
	.type(address2_input, address2)
	.click(updateSettings_button);
    }
    
    @Step("Click Edit Admin User Icon")
    public void clickEditAdminUserIcon(String email) {
	ElementActions.click(driver, edit_icon(email));
    }
    
    @Step("Toggle Location Permissions for Admin User")
    public void toggleLocationPermissions() {
//	Helper.getExplicitWait(driver).until(ExpectedConditions.invisibilityOfElementLocated(save_header));
	new ElementActions(driver)
	.click(addLocationPermission_input)
	.click(editLocationPermission_input)
	.click(updateSettings_button);
    }

    //////////////////////////////////////////////////////////////////
    //////////////////////////// Actions ////////////////////////////
    
    @Step("Assert That The Admin User is in The Table")
    public void assertThatTheAdminUserIsInTheTable(String email) {
	Assert.assertTrue(ElementActions.getText(driver, adminUsersTable_table).contains(email),
		"The user with Email [" + email + "] is not in the admin users table");
    }

}
