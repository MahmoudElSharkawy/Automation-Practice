package examples.selenium;

import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v106.log.Log;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class ConsoleLogsDemo {
    EdgeDriver driver;

    @BeforeClass
    public void setUp() {
	WebDriverManager.edgedriver().setup();
	driver = new EdgeDriver();
	driver.manage().window().maximize();
    }

    @Test
    public void viewBrowserConsoleLogs() {
	// Get The DevTools & Create A Session
	DevTools devTools = driver.getDevTools();
	devTools.createSession();

	// Enable The Console Logs
	devTools.send(Log.enable());

	// Add A Listener For The Logs
	devTools.addListener(Log.entryAdded(), logEntry -> {
	    System.out.println("----------");
	    System.out.println("Level: " + logEntry.getLevel());
	    System.out.println("Text: " + logEntry.getText());
	    System.out.println("Broken URL: " + logEntry.getUrl());
	});

	// Load The AUT
	driver.get("http://the-internet.herokuapp.com/broken_images");
    }

    @AfterClass
    public void tearDown() {
	driver.quit();
    }
}
