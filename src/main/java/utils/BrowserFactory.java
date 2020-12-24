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
	    Logger.logMessage("Opening [Google Chrome] Browser!....");
	    WebDriverManager.chromedriver().setup();
	    driver = new EventFiringWebDriver(new ChromeDriver());
	    driver.register(new SeleniumEventReporter());
	    driver.manage().window().maximize();
	    WebDriverWaits.getImplicitWait(driver);
	} else if (browserType == BrowserType.MOZILLA_FIREFOX
		|| (browserType == BrowserType.FROM_PROPERTIES && browserProperty.equalsIgnoreCase("firefox"))) {
	    Logger.logMessage("Opening [Mozilla Firefox] Browser!....");
	    WebDriverManager.firefoxdriver().setup();
	    driver = new EventFiringWebDriver(new FirefoxDriver());
	    driver.register(new SeleniumEventReporter());
	    driver.manage().window().maximize();
	    WebDriverWaits.getImplicitWait(driver);

	} else {
	    Logger.logMessage("The browser " + browserProperty
		    + " is not valid/supported; Please chose from the given choices in the properties file");
	}
	return driver;
    }

    @Step("Opening Remote Browser")
    /*
     * Steps to executo remotely with selenium grid of dockers VERY simple steps
     * 1- Install docker
     * 2- You need to have a .yml file to configure the network between the containers like that we have in the project root file "docker-compose_native.yml"
     * 3- open a terminal on the project directory
     * 4- Enter the following command that will setup the containers and run them automatically: docker-compose -f docker-compose_native.yml up --scale chrome=4 --remove-orphans -d
     * 5- Enter the following command to check the running containers: docker ps
     * 6- execute using this method
     */
    public static RemoteWebDriver openRemoteBrowser(BrowserType browserType) {
	if (browserType == BrowserType.GOOGLE_CHROME
		|| (browserType == BrowserType.FROM_PROPERTIES && browserProperty.equalsIgnoreCase("chrome"))) {
	    Logger.logMessage("Opening Remote [Google Chrome] Browser!....");
	    try {
		remoteDriver = new RemoteWebDriver(new URL("http://" + host + ":" + port + "/wd/hub"),
			getChromeOptions());
		WebDriverWaits.getImplicitWait(remoteDriver);
	    } catch (MalformedURLException e) {
		e.printStackTrace();
	    }

	} else if (browserType == BrowserType.MOZILLA_FIREFOX
		|| (browserType == BrowserType.FROM_PROPERTIES && browserProperty.equalsIgnoreCase("firefox"))) {
	    Logger.logMessage("Opening Remote [Mozilla Firefox] Browser!....");
	    try {
		remoteDriver = new RemoteWebDriver(new URL("http://" + host + ":" + port + "/wd/hub"),
			getFirefoxOptions());
		WebDriverWaits.getImplicitWait(remoteDriver);
	    } catch (MalformedURLException e) {
		e.printStackTrace();
	    }

	} else {
	    Logger.logMessage("The browser " + browserProperty
		    + " is not valid/supported; Please chose from the given choices in the properties file");
	}
	return remoteDriver;
    }

    private static ChromeOptions getChromeOptions() {
	ChromeOptions chOptions = new ChromeOptions();
	chOptions.setCapability("platform", Platform.LINUX);
	chOptions.addArguments("--headless");
	chOptions.addArguments("--start-maximized");
//	chOptions.addArguments("disable--infobars");
//	chOptions.setExperimentalOption("excludeSwitches", new String[] { "enable-automation" });
//	chOptions.setHeadless(true);
	return chOptions;
    }

    private static FirefoxOptions getFirefoxOptions() {
	FirefoxOptions ffOptions = new FirefoxOptions();
	ffOptions.setCapability("platform", Platform.LINUX);
	ffOptions.addArguments("--headless");
	ffOptions.addArguments("--start-maximized");
	return ffOptions;
    }

}
