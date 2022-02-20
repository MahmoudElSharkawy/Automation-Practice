package phptravels.tests;

import java.io.File;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import phptravels.gui.pages.AdminLoginPage;
import phptravels.gui.pages.AdminsPage;
import phptravels.gui.pages.DashboardMenu;
import phptravels.gui.pages.LocationsPage;
import utils.BrowserActions;
import utils.BrowserFactory;
import utils.ExcelFileManager;
import utils.Helper;

@Epic("PHPTRAVELS")
@Feature("Admin User")
public class AdminUserTests {
    private ExcelFileManager spreadSheet;
    private WebDriver driver;

    private AdminLoginPage adminLoginPage;
    private DashboardMenu dashboardMenu;
    private AdminsPage adminsPage;
    private LocationsPage locationsPage;

    private String currentTime = Helper.getCurrentTime();

    //////////////////////////////////////////////////////
    /////////////////// Test Cases //////////////////////

    @Test(description = "Create a new admin user with location permissions")
    public void createNewAdminUserWithLocationPermissions() {
	String email = spreadSheet.getCellData("Email") + currentTime + "@" + spreadSheet.getCellData("EmailDomain");
	
	dashboardMenu	.navigateToAdminsPage();
	adminsPage	.addAdminUser(spreadSheet.getCellData("FirstName"), spreadSheet.getCellData("LastName"), email, spreadSheet.getCellData("Password"), spreadSheet.getCellData("MobileNumber"), spreadSheet.getCellData("Country"), spreadSheet.getCellData("Address1"), spreadSheet.getCellData("Address2"));
	adminsPage	.clickEditAdminUserIcon(email);
	adminsPage	.toggleLocationPermissions();
	adminsPage	.assertThatTheAdminUserIsInTheTable(email);
    }
    
    @Test(description = "Add New Location", dependsOnMethods = "createNewAdminUserWithLocationPermissions")
    public void addNewLocation() {
	dashboardMenu	.navigateToLocationsPage();
	locationsPage	.addNewLocation(spreadSheet.getCellData("Country"), spreadSheet.getCellData("City"), currentTime, currentTime, spreadSheet.getCellData("Status"));
	locationsPage	.assertThatLocationDataIsAddedToTheTable(spreadSheet.getCellData("City") + " " + spreadSheet.getCellData("Country") + " " + currentTime + " " + currentTime);
    }
    
    @Test(description = "Update Location", dependsOnMethods = "addNewLocation")
    public void updateLocation() {
	locationsPage	.updateLocationCity(spreadSheet.getCellData("City"), spreadSheet.getCellData("NewCity"));
	locationsPage	.assertThatLocationDataIsAddedToTheTable(spreadSheet.getCellData("NewCity") + " " + spreadSheet.getCellData("Country") + " " + currentTime + " " + currentTime);
    }

    //////////////////////////////////////////////////////
    ///////////////// Configurations ////////////////////

    @BeforeClass
    public void setup() {
	spreadSheet = new ExcelFileManager(new File("src/test/resources/TestData/AdminUserTestData.xlsx"));
	spreadSheet.switchToSheet("adminUser");
	
	driver = BrowserFactory.getBrowser();
	
	adminLoginPage = new AdminLoginPage(driver);
	dashboardMenu = new DashboardMenu(driver);
	adminsPage = new AdminsPage(driver);
	locationsPage = new LocationsPage(driver);
	
	adminLoginPage.navigateToAdminLoginPage();
	adminLoginPage.loginAsDefaultAdminUser();

    }

    @AfterClass
    public void tearDown() {
	BrowserActions.closeAllOpenedBrowserWindows(driver);
    }
}
