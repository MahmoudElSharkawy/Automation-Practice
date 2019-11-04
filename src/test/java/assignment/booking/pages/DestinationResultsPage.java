package assignment.booking.pages;

import static org.testng.Assert.assertTrue;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

import generic.Utils;

public class DestinationResultsPage {
    WebDriver driver;

    private By contains_aqua_park_text = By.xpath("(//span[contains(text(),'Aqua Park')])[1]");
//    private By see_availability_button = By.xpath("");

    public DestinationResultsPage(WebDriver driver) {
	this.driver = driver;
    }

    public void assertAquaParkListedInTheResultSet() {
	Utils.getWait(driver).until(ExpectedConditions.visibilityOf(driver.findElement(contains_aqua_park_text)));
	Utils.getJsExecutor(driver, "arguments[0].scrollIntoView(true);", driver.findElement(contains_aqua_park_text));
	assertTrue(driver.findElement(contains_aqua_park_text).getText().contains("Aqua Park"));
    }

}
