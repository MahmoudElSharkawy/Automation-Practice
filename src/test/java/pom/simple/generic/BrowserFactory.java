package pom.simple.generic;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

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

	public static WebDriver browser(BrowserType browserType) {

		switch (browserType) {
		case GOOGLE_CHROME:
			System.out.println("Opening Browser: " + browserType.value);

			try {
				if (System.getProperty("os.name").contains("Windows")) {
					System.setProperty("webdriver.chrome.driver",
							"src/main/resources/drivers/Windows-64/chromedriver.exe");
				} else if (System.getProperty("os.name").contains("Mac")) {
					System.setProperty("webdriver.chrome.driver", "src/main/resources/drivers/Mac-64/chromedriver");
				} else if (System.getProperty("os.name").contains("linux")) {
					System.setProperty("webdriver.chrome.driver", "src/main/resources/drivers/Linux-64/chromedriver");
				}
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}

			driver = new ChromeDriver();
			driver.manage().window().maximize();
			break;

		case MOZILLA_FIREFOX:
			System.out.println("Opening Browser: " + browserType.value);
			try {
				if (System.getProperty("os.name").contains("Windows")) {
					System.setProperty("webdriver.gecko.driver",
							"src/main/resources/drivers/Windows-64/geckodriver.exe");
				} else if (System.getProperty("os.name").contains("Mac")) {
					System.setProperty("webdriver.gecko.driver", "src/main/resources/drivers/Mac-64/geckodriver.exe");
				} else if (System.getProperty("os.name").contains("Linux")) {
					System.setProperty("webdriver.gecko.driver", "src/main/resources/drivers/Linux-64/geckodriver");
				}

			} catch (Exception e) {
				System.out.println(e.getMessage());
			}

			driver = new FirefoxDriver();
			driver.manage().window().maximize();
			break;

		}

		return driver;
	}
}
