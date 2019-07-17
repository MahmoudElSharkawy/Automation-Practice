package examples.beginning;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class BrowserCommandsDemo {

	public static void main(String[] args) {
		System.setProperty("webdriver.chrome.driver", "src/main/resources/drivers/windows-64/chromedriver.exe");

		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();

		driver.get("http://toolsqa.com/");
		System.out.println("First URL: "+ driver.getCurrentUrl());

		driver.navigate().to("http://toolsqa.com/selenium-tutorial/");
		System.out.println("After navigation URL: "+ driver.getCurrentUrl());

		driver.navigate().back();
		System.out.println("Back URL: "+ driver.getCurrentUrl());

		driver.navigate().forward();
		System.out.println("Forward URL: "+ driver.getCurrentUrl());

		driver.navigate().refresh();
		System.out.println("Refresh URL: "+ driver.getCurrentUrl());

		driver.quit();
	}


}
