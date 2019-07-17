package examples.beginning;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class FirefoxDemo {

	public static void main(String[] args) {
		System.setProperty("webdriver.gecko.driver", "src/main/resources/drivers/windows-64/geckodriver.exe");

		WebDriver driver = new FirefoxDriver();
		driver.manage().window().maximize();

		driver.get("https://www.google.com/ncr");
	}

}
