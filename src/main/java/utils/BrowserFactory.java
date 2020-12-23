package utils;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.Platform;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
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
	    AllureReport.logMessage("Opening [Google Chrome] Browser!....");
	    WebDriverManager.chromedriver().setup();
	    driver = new EventFiringWebDriver(new ChromeDriver());
	    driver.register(new EventReporter());
	    driver.manage().window().maximize();
	    WebDriverWaits.getImplicitWait(driver);
	} else if (browserType == BrowserType.MOZILLA_FIREFOX
		|| (browserType == BrowserType.FROM_PROPERTIES && browserProperty.equalsIgnoreCase("firefox"))) {
	    AllureReport.logMessage("Opening [Mozilla Firefox] Browser!....");
	    WebDriverManager.firefoxdriver().setup();
	    driver = new EventFiringWebDriver(new FirefoxDriver());
	    driver.register(new EventReporter());
	    driver.manage().window().maximize();
	    WebDriverWaits.getImplicitWait(driver);

	} else {
	    AllureReport.logMessage("The browser " + browserProperty
		    + " is not valid/supported; Please chose from the given choices in the properties file");
	}
	return driver;
    }

    @Step("Opening Browser")
    public static RemoteWebDriver openRemoteBrowser(BrowserType browserType) {
	if (browserType == BrowserType.GOOGLE_CHROME
		|| (browserType == BrowserType.FROM_PROPERTIES && browserProperty.equalsIgnoreCase("chrome"))) {
	    AllureReport.logMessage("Opening Remote [Google Chrome] Browser!....");
	    try {
		remoteDriver = new RemoteWebDriver(new URL("http://" + host + ":" + port + "/wd/hub"),
			getChromeOptions());
		WebDriverWaits.getImplicitWait(remoteDriver);
	    } catch (MalformedURLException e) {
		e.printStackTrace();
	    }

	} else if (browserType == BrowserType.MOZILLA_FIREFOX
		|| (browserType == BrowserType.FROM_PROPERTIES && browserProperty.equalsIgnoreCase("firefox"))) {
	    AllureReport.logMessage("Opening Remote [Mozilla Firefox] Browser!....");
	    try {
		remoteDriver = new RemoteWebDriver(new URL("http://" + host + ":" + port + "/wd/hub"),
			getFirefoxOptions());
		WebDriverWaits.getImplicitWait(remoteDriver);
	    } catch (MalformedURLException e) {
		e.printStackTrace();
	    }

	} else {
	    AllureReport.logMessage("The browser " + browserProperty
		    + " is not valid/supported; Please chose from the given choices in the properties file");
	}
	return remoteDriver;
    }

    private static ChromeOptions getChromeOptions() {
	ChromeOptions chOptions = new ChromeOptions();
	chOptions.setCapability("platform", Platform.LINUX);
	chOptions.addArguments("--headless");
//	chOptions.addArguments("disable--infobars");
//	chOptions.setExperimentalOption("excludeSwitches", new String[] { "enable-automation" });
//	chOptions.setHeadless(true);
	return chOptions;
    }

    private static FirefoxOptions getFirefoxOptions() {
	FirefoxOptions ffOptions = new FirefoxOptions();
	ffOptions.setCapability("platform", Platform.LINUX);
	ffOptions.addArguments("--headless");
	return ffOptions;
    }

}
