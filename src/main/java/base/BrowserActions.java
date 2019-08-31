package base;

import java.io.File;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

public class BrowserActions {

	// Generic url navigation method
	public static void openURL(String url) {
		BrowserFactory.browserDriver.get(url);
	}

	// Close
	public static void close() {
		BrowserFactory.browserDriver.quit();
	}

	public static void takeScreenShot(String testCaseName){
		TakesScreenshot ts = (TakesScreenshot)BrowserFactory.browserDriver; 
		File source = ts.getScreenshotAs(OutputType.FILE); 
		try {
			FileUtils.copyFile(source, new File("src/test/resources/ScreenShots/"+ testCaseName +".png"));
		} catch (Exception e) {
			System.out.println("Exception while taking screenshot " + e.getMessage());
		}
	}
}
