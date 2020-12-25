package examples.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DownloadFileWithFireFoxOptionsDemo {
    WebDriver driver;
    static String downloadPath = "src/test/resources/Downloads";

    public static FirefoxOptions firefoxOption() {
	FirefoxOptions option = new FirefoxOptions();
	option.addPreference("browser.download.folderList", 2);
	option.addPreference("browser.download.dir", downloadPath);
	option.addPreference("browser.helperApps.neverAsk.saveToDisk", "application/octet-stream");
	option.addPreference("browser.download.manager.showWhenStarting", false);
	return option;
    }

    @BeforeTest
    public void setup() {
	WebDriverManager.firefoxdriver().setup();
	driver = new FirefoxDriver(firefoxOption());
	driver.get("http://the-internet.herokuapp.com/download");
	driver.manage().window().maximize();
    }

    @Test
    public void TestDownloadFile() throws InterruptedException {
	driver.findElement(By.linkText("some-file.txt")).click();
	Thread.sleep(3000);
    }

    @AfterTest
    public void closeBrowser() {
	driver.quit();
    }

}
