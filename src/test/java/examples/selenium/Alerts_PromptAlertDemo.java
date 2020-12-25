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

public class Alerts_PromptAlertDemo {

    WebDriver driver;

    @BeforeTest
    public void setup() {
	WebDriverManager.chromedriver().setup();
	driver = new ChromeDriver();
	driver.manage().window().maximize();

	driver.get("http://cookbook.seleniumacademy.com/Alerts.html");
    }

    @Test(priority = 1)
    public void promptDialogAcceptTest() {

	// Confirm Dialog
	WebElement tryitP = driver.findElement(By.id("prompt"));
	tryitP.click();

	Alert jsPAlertConfirm = driver.switchTo().alert();
	// insert your name
	String name = "Sharkawy";
	jsPAlertConfirm.sendKeys(name);
	// Confirm
	jsPAlertConfirm.accept();
	// assert
	WebElement d = driver.findElement(By.id("prompt_demo"));
	assertTrue(d.getText().equals("Hello " + name + "! How are you today?"));
    }

    @Test(priority = 2)
    public void promptDialogDismissTest() {
	WebElement tryitP = driver.findElement(By.id("prompt"));
	tryitP.click();
	// Dismiss
	Alert jsPAlertDismiss = driver.switchTo().alert();
	jsPAlertDismiss.dismiss();
    }

    @AfterTest
    public void closeBrowser() {
	driver.quit();
    }
}
