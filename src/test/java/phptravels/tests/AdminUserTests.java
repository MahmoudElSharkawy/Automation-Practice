package phptravels.tests;

import java.io.File;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import phptravels.gui.pages.AdminLoginPage;
import utils.BrowserActions;
import utils.BrowserFactory;
import utils.ExcelFileManager;

@Epic("PHPTRAVELS")
@Feature("Admin User")
public class AdminUserTests {
    private ExcelFileManager spreadSheet;
    private WebDriver driver;

    private AdminLoginPage adminLoginPage;

//    private String currentTime = Helper.getCurrentTime("");

    //////////////////////////////////////////////////////
    /////////////////// Test Cases //////////////////////

    @Test(description = "Create a new admin user")
    public void createNewAdminUser() {
	adminLoginPage	.loginAsDefaultAdminUser();
    }

    //////////////////////////////////////////////////////
    ///////////////// Configurations ////////////////////

    @BeforeClass
    public void setup() {
	spreadSheet = new ExcelFileManager(new File("src/test/resources/TestData/AdminUserTestData.xlsx"));
	spreadSheet.switchToSheet("adminUser");
	driver = BrowserFactory.getBrowser();

	adminLoginPage = new AdminLoginPage(driver);
	
	adminLoginPage.navigateToAdminLoginPage();
    }

    @AfterClass
    public void afterMethod() {
	BrowserActions.closeAllOpenedBrowserWindows(driver);
    }
}
