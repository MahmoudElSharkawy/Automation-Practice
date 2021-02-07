package examples.testng;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;

@Epic("Allure Reporting Demo -> Test Google home page")

public class AllureReportingDemo {
	WebDriver driver;

	@BeforeTest
	public void setup() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().window().maximize();

		driver.get("https://www.google.com/?ncr");
	}

	@Test
	@Description("Validating the Goole home page URL")
	@Severity(SeverityLevel.BLOCKER)
	public void getURL() {
		System.out.println(driver.getCurrentUrl());
		assertEquals(driver.getCurrentUrl(), "https://www.google.com/?ncr");
	}

	@Test
	@Description("Validating the Goole home page title")
	@Severity(SeverityLevel.CRITICAL)
	public void getTitle() {
		System.out.println(driver.getTitle());
		assertTrue(driver.getTitle().contains("Goo"));
	}

	@Test
	@Description("Validating the Goole home page search button text")
	@Severity(SeverityLevel.NORMAL)
	public void getTextfromBtn() {
		String searchBtnText = driver.findElement(By.name("btnK")).getText();
		System.out.println("SEARCH BUTTON TEXT >>>>>>: "+searchBtnText);
		assertTrue(searchBtnText.contains("Goo"));
	}

	@AfterClass
	public void closeBrowser() {
		driver.quit();
	}
}
