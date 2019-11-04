package assignment.booking.pages;

import org.openqa.selenium.WebDriver;

import examples.datadriven.FileDataReader;

public class DestinationPage {
    WebDriver driver;
    FileDataReader dataReader;
    
    public DestinationPage(WebDriver driver) {
	this.driver = driver;
    }
    
    public void switchToDestinationPageTab() {
	dataReader = new FileDataReader();
	driver.switchTo().window(dataReader.read("BookingTestData.xlsx", 8, 2));
    }
    
}
