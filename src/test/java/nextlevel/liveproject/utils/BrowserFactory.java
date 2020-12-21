package nextlevel.liveproject.utils;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BrowserFactory {
    private static EventFiringWebDriver driver;
    private static String browserProperty = PropertiesReader.getProperty("liveproject.properties", "target.browser");

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

    public static EventFiringWebDriver openBrowser(BrowserType browserType) {
	if (browserType == BrowserType.GOOGLE_CHROME
		|| (browserType == BrowserType.FROM_PROPERTIES && browserProperty.equalsIgnoreCase("chrome"))) {
	    System.out.println("Opening Google Chrome Browser!....");
	    WebDriverManager.chromedriver().setup();
	    driver = new EventFiringWebDriver(new ChromeDriver());
	    driver.register(new EventReporter());
	    driver.manage().window().maximize();
	} else if (browserType == BrowserType.MOZILLA_FIREFOX
		|| (browserType == BrowserType.FROM_PROPERTIES && browserProperty.equalsIgnoreCase("firefox"))) {
	    System.out.println("Opening Mozilla Firefox Browser!....");
	    WebDriverManager.firefoxdriver().setup();
	    driver = new EventFiringWebDriver(new FirefoxDriver());
	    driver.register(new EventReporter());
	} else {
	    System.out.println("The browser " + browserProperty
		    + " is not valid/supported; Please chose from the given choices in the propertied file");
	}
	return driver;
    }

//    private ChromeOptions getOptions() {
//	ChromeOptions options = new ChromeOptions();
//	options.addArguments("disable--infobars");
//	options.setExperimentalOption("excludeSwitches", new String[] { "enable-automation" });
//	options.setHeadless(true);
//	return options;
//    }

}
