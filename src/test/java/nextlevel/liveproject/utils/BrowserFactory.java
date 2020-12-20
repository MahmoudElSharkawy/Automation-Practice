package nextlevel.liveproject.utils;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BrowserFactory {
    private static EventFiringWebDriver driver;

    public enum BrowserType {
	MOZILLA_FIREFOX("Mozilla Firefox"), GOOGLE_CHROME("Google Chrome"), FROM_PROPERTIES("");

	private String value;

	BrowserType(String type) {
	    this.value = type;
	}

	protected String getValue() {
	    return value;
	}
    }

    public static EventFiringWebDriver openBrowser(BrowserType browserType) {

	switch (browserType) {
	case GOOGLE_CHROME:
	    System.out.println("Opening Browser: " + browserType.value);
	    WebDriverManager.chromedriver().setup();
	    driver = new EventFiringWebDriver(new ChromeDriver());
	    driver.register(new EventReporter());
	    driver.manage().window().maximize();
//	    private ChromeOptions getOptions() {
//		ChromeOptions options = new ChromeOptions();
////		options.addArguments("disable--infobars");
//		options.setExperimentalOption("excludeSwitches", new String[] { "enable-automation" });
////		options.setHeadless(true);
//		return options;
//	    }
	    break;

	case MOZILLA_FIREFOX:
	    System.out.println("Opening Browser: " + browserType.value);
	    WebDriverManager.firefoxdriver().setup();
	    driver = new EventFiringWebDriver(new FirefoxDriver());
	    break;

	case FROM_PROPERTIES:

	}

	return driver;
    }
}
