package base;

public class BrowserActions {
	
	// Generic url navigation method
	public static void openURL(String url) {
		BaseClass.browserDriver.get(url);
	}

	// Close
	public static void close() {
		BaseClass.browserDriver.quit();
	}

}
