package nextlevel.liveproject.tests;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import nextlevel.liveproject.utils.BrowserFactory;
import nextlevel.liveproject.utils.BrowserFactory.BrowserType;
import nextlevel.liveproject.utils.PropertiesReader;

public class BaseTests {

    private EventFiringWebDriver driver;

    @BeforeClass
    public void setUp() {
	driver = BrowserFactory.openBrowser(BrowserType.GOOGLE_CHROME);
	driver.get(PropertiesReader.getProperty("liveproject.properties", "url"));

    }

    @AfterMethod
    public void takeScreenShotInCaseOfFailure(ITestResult result) {
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

    @AfterClass
    public void tearDown() {
	driver.quit();
    }
}
