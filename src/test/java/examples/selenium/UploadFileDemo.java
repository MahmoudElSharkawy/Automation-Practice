package examples.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class UploadFileDemo {
    public WebDriver driver;

    @BeforeTest
    public void setUp() {
	WebDriverManager.chromedriver().setup();
	driver = new ChromeDriver();
	driver.get("https://the-internet.herokuapp.com/upload");
	driver.manage().window().maximize();
    }

    @Test
    public void testFileUpload() throws InterruptedException {
	String imageName = "counterguardian.jpg";
	String imagePath = System.getProperty("user.dir") + "/src/test/resources/Uploads/" + imageName;
	WebElement fileUploader = driver.findElement(By.id("file-upload"));
	fileUploader.sendKeys(imagePath);
	WebElement fileSubmit = driver.findElement(By.id("file-submit"));
	fileSubmit.click();
	WebElement uploadedFiles = driver.findElement(By.id("uploaded-files"));
	Assert.assertEquals(imageName, uploadedFiles.getText());

    }

    @AfterTest
    public void closeBrowser() {
	driver.quit();
    }

}
