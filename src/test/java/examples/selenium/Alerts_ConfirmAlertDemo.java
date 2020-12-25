package examples.selenium;

import static org.testng.Assert.assertTrue;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Alerts_ConfirmAlertDemo {

    WebDriver driver;

    @BeforeTest
    public void setup() {
	WebDriverManager.chromedriver().setup();
	driver = new ChromeDriver();
	driver.manage().window().maximize();

	driver.get("http://cookbook.seleniumacademy.com/Alerts.html");
    }

    @Test
    public void confirmAlertAcceptTest() {

	// Confirm Dialog
	WebElement tryitC = driver.findElement(By.id("confirm"));
	tryitC.click();
	// Confirm
	Alert jsCAlertConfirm = driver.switchTo().alert();
	jsCAlertConfirm.accept();
	// assert
	WebElement d = driver.findElement(By.id("demo"));
	assertTrue(d.getText().equals("You Accepted Alert!"));
    }

    @Test
    public void confirmAlertDismissTest() {

	// Confirm Dialog
	WebElement tryitC = driver.findElement(By.id("confirm"));
	tryitC.click();
	// Dismiss
	Alert jsCAlertDismiss = driver.switchTo().alert();
	jsCAlertDismiss.dismiss();
	// assert
	WebElement d = driver.findElement(By.id("demo"));
	assertTrue(d.getText().equals("You Dismissed Alert!"));
    }

    @AfterTest
    public void closeBrowser() {
	driver.quit();
    }
}
