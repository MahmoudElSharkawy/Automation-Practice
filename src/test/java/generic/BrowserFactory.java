package generic;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class BrowserFactory {

	public static WebDriver browserDriver;

	// Choose the Browser
	public static void browser(String browserName) throws IOException {

		if (browserName == null) {
			throw new IOException("Browser name not found!!");
		} else {
			if (browserName.equalsIgnoreCase("chrome") || browserName.equalsIgnoreCase("google chrome")) {
				System.out.println("Opening Browser: " + browserName);

				if (System.getProperty("os.name").contains("Windows")) {
					System.setProperty("webdriver.chrome.driver",
							"src/main/resources/drivers/Windows-64/chromedriver.exe");
				} else if (System.getProperty("os.name").contains("Mac")) {
					System.setProperty("webdriver.chrome.driver", "src/main/resources/drivers/Mac-64/chromedriver");
				} else if (System.getProperty("os.name").contains("linux")) {
					System.setProperty("webdriver.chrome.driver", "src/main/resources/drivers/Linux-64/chromedriver");
				} else {
					throw new IOException("Sorry, the selected OS is not valid or Not supported!!");
				}

				browserDriver = new ChromeDriver();
				browserDriver.manage().window().maximize();

			}

			else if (browserName.equalsIgnoreCase("firefox") || browserName.equalsIgnoreCase("ff")) {
				System.out.println("Opening Browser: " + browserName);

				if (System.getProperty("os.name").contains("windows")) {
					System.setProperty("webdriver.gecko.driver",
							"src/main/resources/drivers/Windows-64/geckodriver.exe");
				} else if (System.getProperty("os.name").contains("mac")) {
					System.setProperty("webdriver.gecko.driver", "src/main/resources/drivers/Mac-64/geckodriver.exe");
				} else if (System.getProperty("os.name").contains("linux")) {
					System.setProperty("webdriver.gecko.driver", "src/main/resources/drivers/Linux-64/geckodriver");
				} else {
					throw new IOException("The selected OS is not valid or Not supported!!");
				}

				browserDriver = new FirefoxDriver();
				browserDriver.manage().window().maximize();

			} else {
				throw new IOException("The entered browser name is not valid or Not supported!!");
			}
		}

	}

}
