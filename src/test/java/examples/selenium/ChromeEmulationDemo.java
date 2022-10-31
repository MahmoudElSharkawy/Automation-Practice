package examples.selenium;

import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class ChromeEmulationDemo {
    WebDriver driver;

    @Test
    public void chromeEmultionDeviceTest() {
	WebDriverManager.chromedriver().setup();
	
	Map<String, String> mobileEmulation = new HashMap<>();
	mobileEmulation.put("deviceName", "Nexus 5");
	ChromeOptions chromeOptions = new ChromeOptions();
	chromeOptions.setExperimentalOption("mobileEmulation", mobileEmulation);
	driver = new ChromeDriver(chromeOptions);
	
	driver.get("https://www.google.com/");
	
    }
    
    @Test
    public void chromeEmultionCustomDeviceTest() {
	WebDriverManager.edgedriver().setup();
	
	Map<String, Object> deviceMetrics = new HashMap<>();
	deviceMetrics.put("width", 560);
	deviceMetrics.put("height", 640);
	deviceMetrics.put("pixelRatio", 3.0);
	Map<String, Object> mobileEmulation = new HashMap<>();
	mobileEmulation.put("deviceMetrics", deviceMetrics);
	mobileEmulation.put("userAgent", "Mozilla/5.0 (Linux; Android 4.2.1; en-us; Nexus 5 Build/JOP40D) AppleWebKit/535.19 (KHTML, like Gecko) Chrome/18.0.1025.166 Mobile Safari/535.19");
	EdgeOptions chromeOptions = new EdgeOptions(); 
	chromeOptions.setExperimentalOption("mobileEmulation", mobileEmulation);
	driver = new EdgeDriver(chromeOptions);
	
	driver.get("https://www.google.com/");
	
    }

}
