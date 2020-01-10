package assignments;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class AutomationPractice_Registration {
	WebDriver driver;
	Date date = new Date();

	@BeforeClass
	public void setUp() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		// navigate to URL
		driver.navigate().to("http://automationpractice.com/index.php");
	}

	@Test
	public void registrationTest() {


		driver.findElement(By.xpath("//*[@id='header']/div[2]/div/div/nav/div[1]/a")).click();

		driver.findElement(By.xpath("//*[@id='email_create']")).sendKeys("test" + date.getTime() + "@testing.com");
		driver.findElement(By.xpath("//*[@id='SubmitCreate']")).click();

		// Select Radio Button
		driver.findElement(By.id("id_gender1")).click();

		// Enter customer details
		driver.findElement(By.xpath("//*[@id='customer_firstname']")).sendKeys("Test First Name");
		driver.findElement(By.xpath("//*[@id='customer_lastname']")).sendKeys("Test Last Name");
		driver.findElement(By.xpath("//*[@id='passwd']")).sendKeys("Password@123");

		// Select date of Birth
		Select sDate = new Select(driver.findElement(By.xpath("//*[@id='days']")));
		sDate.selectByVisibleText("2  ");

		Select sMonth = new Select(driver.findElement(By.xpath("//*[@id='months']")));
		sMonth.selectByVisibleText("May ");

		Select sYear = new Select(driver.findElement(By.xpath("//*[@id='years']")));
		sYear.selectByVisibleText("2015  ");

		// select required check boxes
		String newsLetterReq = "Yes";
		if (newsLetterReq.equalsIgnoreCase(newsLetterReq)) {
			driver.findElement(By.xpath(".//*[@id='newsletter']")).click();
		}

		String reciveSpclOffer = "Yes";
		if (reciveSpclOffer.equalsIgnoreCase(reciveSpclOffer)) {
			driver.findElement(By.xpath("//*[@id='optin']")).click();

			// Fill address related details
			driver.findElement(By.xpath("//*[@id='firstname']")).sendKeys("FnameInAddr");
			driver.findElement(By.xpath("//*[@id='lastname']")).sendKeys("LnameinAddr");
			driver.findElement(By.xpath("//*[@id='company']")).sendKeys("AFAQY");
			driver.findElement(By.xpath("//*[@id='address1']")).sendKeys("addr1");
			driver.findElement(By.xpath("//*[@id='address2']")).sendKeys("addr2");
			driver.findElement(By.xpath("//*[@id='city']")).sendKeys("6October");

			Select sState = new Select(driver.findElement(By.xpath("//*[@id='id_state']")));
			sState.selectByVisibleText("Alabama");

			driver.findElement(By.xpath("//*[@id='postcode']")).sendKeys("12345");

			Select sCountry = new Select(driver.findElement(By.xpath("//*[@id='id_country']")));
			sCountry.selectByVisibleText("United States");

			driver.findElement(By.xpath("//*[@id='other']")).sendKeys("any other info");
			driver.findElement(By.xpath("//*[@id='phone']")).sendKeys("123457876");
			driver.findElement(By.xpath("//*[@id='phone_mobile']")).sendKeys("021543512351");
			
			driver.findElement(By.xpath("//*[@id='alias']")).clear();
			driver.findElement(By.xpath("//*[@id='alias']")).sendKeys("alias", Keys.ENTER);
			
			Assert.assertEquals(driver.findElement(By.xpath("//h1[@class='page-heading']")).getText(), "MY ACCOUNT");

		}
	}
}
