package examples.beginning;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class LocatorsDemo {

	public static void main(String[] args) {
		System.setProperty("webdriver.chrome.driver", "src/main/resources/drivers/windows-64/chromedriver.exe");

		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();

		driver.get("https://www.google.com/ncr");
		
//		//By name
//		driver.findElement(By.name("q")).sendKeys("Selenium", Keys.ENTER);
		
		//By absolute xpath (DONT EVER DO THIS!!!)
//		driver.findElement(By.xpath("/html/body/div/div[3]/form/div[2]/div/div[1]/div/div[1]/input")).sendKeys("Selenium", Keys.ENTER);
		
		//By relative xpath ( Xpath=//tagname[@attribute='value'] )
//		driver.findElement(By.xpath("//input[@name='q']")).sendKeys("Selenium", Keys.ENTER);
		
		//Click on I'm Feeling Lucky button
//		driver.findElement(By.name("btnI")).click();
		
//		//Click on the Gmail Link
//		driver.findElement(By.linkText("Gmail")).click();
	}

}
