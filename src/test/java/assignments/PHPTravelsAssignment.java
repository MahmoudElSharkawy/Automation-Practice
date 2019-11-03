package assignments;

import static org.testng.Assert.assertEquals;

import java.util.Date;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import generic.BrowserFactory;
import generic.BrowserFactory.BrowserType;

public class PHPTravelsAssignment {

    String firstName = "TestFN";
    String lastName = "TestLN";
    String email;
    String password = "12345678";

    WebDriver driver;
    Date date;

    @BeforeClass
    public void setUp() {
	date = new Date();
	email = "test" + date.getTime() + "@test.com";
	System.setProperty("webdriver.chrome.driver", "src/main/resources/drivers/windows-64/chromedriver.exe");

	driver = BrowserFactory.browser(BrowserType.GOOGLE_CHROME);
	driver.get("https://www.phptravels.net/");
    }

    @Test(priority = 1)
    public void Registration() {

	driver.findElement(By.linkText("MY ACCOUNT")).click();
	driver.findElement(By.linkText("Sign Up")).click();
	// tagname[@attribute='value']
	driver.findElement(By.xpath("//input[@placeholder='First Name']")).sendKeys(firstName);
	driver.findElement(By.name("lastname")).sendKeys(lastName);
	driver.findElement(By.name("phone")).sendKeys("1523648987");
	driver.findElement(By.name("email")).sendKeys(email);
	driver.findElement(By.name("password")).sendKeys(password);
	driver.findElement(By.name("confirmpassword")).sendKeys(password);
	// tagname[@attribute='value']
	driver.findElement(
		By.xpath("//button[@type='submit' and contains (class, signupbtn) and contains(text() ,'Sign Up')]"))
		.click();

	WebDriverWait wait = new WebDriverWait(driver, 20);
	wait.until(ExpectedConditions.titleContains("My Account"));
	System.out.println(driver.getTitle());

	WebElement welcomeTxt = driver.findElement(By.xpath("//*[@style='margin-left: 17px']"));

	assertEquals(welcomeTxt.getText(), "Hi, " + firstName + " " + lastName);
    }

    @Test(priority = 2, dependsOnMethods = { "Registration" })
    public void loginWithRegisteredData() {
	driver.findElement(By.linkText(firstName.toUpperCase())).click();
	driver.findElement(By.linkText("Logout")).click();

	driver.findElement(By.name("username")).sendKeys(email);
	driver.findElement(By.name("password")).sendKeys(password);
	driver.findElement(
		By.xpath("//button[@type='submit' and contains (class, signupbtn) and contains(text() ,'Login')]"))
		.click();

	WebDriverWait wait = new WebDriverWait(driver, 20);
	wait.until(ExpectedConditions.titleContains("My Account"));
	System.out.println(driver.getTitle());

	WebElement welcomeTxt = driver.findElement(By.xpath("//*[@style='margin-left: 17px']"));

	assertEquals(welcomeTxt.getText(), "Hi, " + firstName + " " + lastName);
    }

    @AfterClass
    public void closeBrowser() {
	driver.quit();
    }
}
