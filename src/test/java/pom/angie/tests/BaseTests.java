package pom.angie.tests;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;

import io.github.bonigarcia.wdm.WebDriverManager;
import pom.angie.pages.GoogleHomePage;
import pom.angie.utils.EventReporter;

public class BaseTests {

	private EventFiringWebDriver driver;
	protected GoogleHomePage homePage;

	@BeforeClass
	public void setUp() {
		WebDriverManager.chromedriver().setup();
		driver = new EventFiringWebDriver(new ChromeDriver(getChromeOptions()));
		driver.register(new EventReporter());
		driver.manage().window().maximize();

	}
	
	@BeforeMethod
	public void BeforeMethod() {
		driver.get("https://www.google.com/ncr");
		homePage = new GoogleHomePage(driver);
	}

	@AfterMethod
	public void takeScreenShot(ITestResult result) {
		if (result.getStatus() == ITestResult.FAILURE) {
			TakesScreenshot ts = (TakesScreenshot) driver;
			File source = ts.getScreenshotAs(OutputType.FILE);
			try {
				FileUtils.copyFile(source, new File("src/test/resources/ScreenShots/" + result.getName() + ".png"));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	private ChromeOptions getChromeOptions() {
		ChromeOptions options = new ChromeOptions();
//		options.addArguments("disable--infobars");
		options.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"}); 
		// options.setHeadless(true);
		return options;
	}

	@AfterClass
	public void tearDown() {
		driver.quit();
	}
}