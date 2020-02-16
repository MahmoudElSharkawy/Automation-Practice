package examples.datadriven;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class DataDrivenDemo {
    WebDriver driver;

    @BeforeMethod
    public void setUp() {
	System.setProperty("webdriver.chrome.driver", "src/main/resources/drivers/windows-64/chromedriver.exe");
	driver = new ChromeDriver();
	driver.manage().window().maximize();

	driver.get("https://opensource-demo.orangehrmlive.com");
    }

    @Test(dataProviderClass = SignInDataProvider.class, dataProvider = "signin-provider")
    public void signIn(String usename, String password, boolean success) {

	driver.findElement(By.id("txtUsername")).sendKeys(usename);
	driver.findElement(By.id("txtPassword")).sendKeys(password);
	driver.findElement(By.id("btnLogin")).click();

	System.out.println("Sign In Credentials: " + "\n" + "  Username = " + usename + "\n" + "  Password = "
		+ password + "\n" + "  Successful Sign In = " + success + "\n");

	String actualResult = driver.findElement(By.id("welcome")).getText();
	String expectedResult = "Welcome Admin";
	Assert.assertEquals(actualResult, expectedResult, "The Actual & Expected Results Do Not Match");

    }

    @AfterMethod
    public void closeBrowser() {
	driver.quit();

    }
}
