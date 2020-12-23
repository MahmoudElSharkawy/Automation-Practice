package utils;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Step;

public class BrowserFactory {
    private static EventFiringWebDriver driver;
    private static RemoteWebDriver remoteDriver;
    private static String browserProperty = PropertiesReader.getProperty("liveproject.properties", "target.browser");
    private static String host = "localhost";
    private static String port = "4444";

    public enum BrowserType {
	MOZILLA_FIREFOX("Mozilla Firefox"), GOOGLE_CHROME("Google Chrome"), FROM_PROPERTIES(browserProperty);

	private String value;

	BrowserType(String type) {
	    this.value = type;
	}

	protected String getValue() {
	    return value;
	}
    }

    @Step("Opening Browser")
    public static EventFiringWebDriver openBrowser(BrowserType browserType) {
	if (browserType == BrowserType.GOOGLE_CHROME
		|| (browserType == BrowserType.FROM_PROPERTIES && browserProperty.equalsIgnoreCase("chrome"))) {
//	    System.out.println("Opening Google Chrome Browser!....");
	    AllureReport.logMessage("Opening [Google Chrome] Browser!....");
	    WebDriverManager.chromedriver().setup();
	    driver = new EventFiringWebDriver(new ChromeDriver());
	    driver.register(new EventReporter());
	    driver.manage().window().maximize();
	    driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	} else if (browserType == BrowserType.MOZILLA_FIREFOX
		|| (browserType == BrowserType.FROM_PROPERTIES && browserProperty.equalsIgnoreCase("firefox"))) {
//	    System.out.println("Opening Mozilla Firefox Browser!....");
	    AllureReport.logMessage("Opening [Mozilla Firefox] Browser!....");
	    WebDriverManager.firefoxdriver().setup();
	    driver = new EventFiringWebDriver(new FirefoxDriver());
	    driver.register(new EventReporter());
	    driver.manage().window().maximize();
	    driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	} else {
	    AllureReport.logMessage("The browser " + browserProperty
		    + " is not valid/supported; Please chose from the given choices in the properties file");
	}
	return driver;
    }

    @Step("Opening Browser")
    public static RemoteWebDriver openBrowser_remote(BrowserType browserType) {
	if (browserType == BrowserType.GOOGLE_CHROME
		|| (browserType == BrowserType.FROM_PROPERTIES && browserProperty.equalsIgnoreCase("chrome"))) {
	    AllureReport.logMessage("Opening Remote [Google Chrome] Browser!....");
	    DesiredCapabilities capabilities = new DesiredCapabilities();
	    capabilities.setCapability("browserName", browserProperty);
	    capabilities.setCapability("version", "ANY");
	    try {
		remoteDriver = new RemoteWebDriver(new URL("http://" + host + ":" + port + "/wd/hub"), capabilities);
	    } catch (MalformedURLException e) {
		e.printStackTrace();
	    }
	}
	return remoteDriver;
    }

//    private ChromeOptions getOptions() {
//	ChromeOptions options = new ChromeOptions();
//	options.addArguments("disable--infobars");
//	options.setExperimentalOption("excludeSwitches", new String[] { "enable-automation" });
//	options.setHeadless(true);
//	return options;
//    }

}
