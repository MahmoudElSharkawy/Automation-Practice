package engine;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BrowserFactory {
    static WebDriver driver;

    public enum BrowserType {
	MOZILLA_FIREFOX("Mozilla Firefox"), GOOGLE_CHROME("Google Chrome");

	private String value;

	BrowserType(String type) {
	    this.value = type;
	}

	protected String getValue() {
	    return value;
	}
    }

    public static WebDriver openBrowser(BrowserType browserType) {

	switch (browserType) {
	case GOOGLE_CHROME:
	    System.out.println("Opening Browser: " + browserType.value);
	    WebDriverManager.chromedriver().setup();
	    driver = new ChromeDriver();
	    driver.manage().window().maximize();
	    break;
	    
	case MOZILLA_FIREFOX:
	    System.out.println("Opening Browser: " + browserType.value);
	    WebDriverManager.firefoxdriver().setup();
	    driver = new FirefoxDriver();
	    break;
	}

	return driver;
    }
}
