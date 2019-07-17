package examples.more;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class UploadFileDemo 
{
	public WebDriver driver ; 


	@BeforeTest
	public void setUp() {
		System.setProperty("webdriver.chrome.driver", "src/main/resources/drivers/windows-64/chromedriver.exe");

		driver = new ChromeDriver(); 
		driver.get("https://the-internet.herokuapp.com/upload");
		driver.manage().window().maximize();
	}


	@Test
	public void testFileUpload() throws InterruptedException {
		String imageName = "counterguardian.jpg";
		String imagePath = "./src/test/resources/Uploads/"+imageName;	
		WebElement fileUploader = driver.findElement(By.id("file-upload")); 
		fileUploader.sendKeys(imagePath);
		WebElement fileSubmit = driver.findElement(By.id("file-submit")); 
		fileSubmit.click();
		WebElement uploadedFiles = driver.findElement(By.id("uploaded-files")); 
		System.out.println(uploadedFiles.getText());
		Thread.sleep(3000);
		Assert.assertEquals(imageName, uploadedFiles.getText());

	}

	@AfterTest
	public void closeBrowser() {
		driver.quit();
	}


}
