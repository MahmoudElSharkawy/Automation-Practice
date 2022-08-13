package utils;

import static org.testng.Assert.fail;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.Reporter;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Step;

public class BrowserFactory {
//    private static WebDriver driver;
    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();
    private static String browserTypeProperty = System.getProperty("browser.type");
    private static String executionTypeProperty = System.getProperty("execution.type");
    private static String host = System.getProperty("remote.execution.host");
    private static String port = System.getProperty("remote.execution.port");

    public enum BrowserType {
	MOZILLA_FIREFOX("Mozilla Firefox"), GOOGLE_CHROME("Google Chrome"), FROM_PROPERTIES(browserTypeProperty);

	private String value;

	BrowserType(String type) {
	    this.value = type;
	}

	protected String getValue() {
	    return value;
	}
    }

    public enum ExecutionType {
	LOCAL("Local"), REMOTE("Remote"), LOCAL_HEADLESS("Local Headless"), FROM_PROPERTIES(executionTypeProperty);

	private String value;

	ExecutionType(String type) {
	    this.value = type;
	}

	protected String getValue() {
	    return value;
	}
    }

    public static WebDriver getBrowser() {
	return getBrowser(BrowserType.FROM_PROPERTIES, ExecutionType.FROM_PROPERTIES);
    }

    @Step("Initializing a new Web GUI Browser!.....")
    public static synchronized WebDriver getBrowser(BrowserType browserType, ExecutionType executionType) {
	Logger.logStep("Initialize [" + browserType.getValue() + "] Browser and the Execution Type is ["
		+ executionType.getValue() + "]");
    	ITestResult result = Reporter.getCurrentTestResult();
	ITestContext context = result.getTestContext();
	// Remote execution condition
	if (executionType == ExecutionType.REMOTE || (executionType == ExecutionType.FROM_PROPERTIES
		&& executionTypeProperty.equalsIgnoreCase("remote"))) {
	    /*
	     * Steps to execute remotely with selenium grid and dockers VERY simple steps:... 
	     * 1- Install docker 
	     * 2- You need to have a .yml file to configure the network between the containers like that we have in the src/main/resource file "docker-compose_native.yml" 
	     * 3- open a terminal on the .yml file directory 
	     * 4- Enter the following command that will setup the containers (1 hub & 4 nodes) and run them automatically: 
	     * docker-compose -f docker-compose_native.yml up --scale chrome=4 --remove-orphans -d 
	     * 5- Enter the following command to check the running containers: docker ps 
	     * 6- open a browser and enter this url to see the grid :D http://localhost:4444/ui/index.html 
	     * 7- execute using this condition
	     */
	    if (browserType == BrowserType.GOOGLE_CHROME
		    || (browserType == BrowserType.FROM_PROPERTIES && browserTypeProperty.equalsIgnoreCase("google chrome"))) {
		try {
		    driver.set(new RemoteWebDriver(new URL("http://" + host + ":" + port + "/wd/hub"),
			    getChromeOptions()));
		    context.setAttribute("driver", driver.get());
		    Helper.implicitWait(driver.get());
		} catch (MalformedURLException e) {
		    e.printStackTrace();
		}

	    } else if (browserType == BrowserType.MOZILLA_FIREFOX || (browserType == BrowserType.FROM_PROPERTIES
		    && browserTypeProperty.equalsIgnoreCase("mozilla firefox"))) {
		try {
		    driver.set(new RemoteWebDriver(new URL("http://" + host + ":" + port + "/wd/hub"),
			    getFirefoxOptions()));
		    context.setAttribute("driver", driver.get());
		    Helper.implicitWait(driver.get());
		} catch (MalformedURLException e) {
		    e.printStackTrace();
		}
	    } else {
		String warningMsg = "The driver is null! because the browser type [" + browserTypeProperty
			+ "] is not valid/supported; Please choose a valid browser type from the given choices in the properties file";
		Logger.logMessage(warningMsg);
		fail(warningMsg);
//		throw new NullPointerException(warningMsg);
	    }
	}
	// Local execution condition
	else if (executionType == ExecutionType.LOCAL || (executionType == ExecutionType.FROM_PROPERTIES
		&& executionTypeProperty.equalsIgnoreCase("local"))) {
	    if (browserType == BrowserType.GOOGLE_CHROME
		    || (browserType == BrowserType.FROM_PROPERTIES && browserTypeProperty.equalsIgnoreCase("google chrome"))) {
		WebDriverManager.chromedriver().setup();
		driver.set(new ChromeDriver());
		context.setAttribute("driver", driver.get());
		Helper.implicitWait(driver.get());
		if (System.getProperty("maximize").equalsIgnoreCase("true")) {
		    BrowserActions.maximizeWindow(driver.get());
		} else {
		    BrowserActions.setWindowResolution(driver.get());
		}
	    } else if (browserType == BrowserType.MOZILLA_FIREFOX || (browserType == BrowserType.FROM_PROPERTIES
		    && browserTypeProperty.equalsIgnoreCase("mozilla firefox"))) {
		WebDriverManager.firefoxdriver().setup();
		driver.set(new FirefoxDriver());
		context.setAttribute("driver", driver.get());
		Helper.implicitWait(driver.get());
		if (System.getProperty("maximize").equalsIgnoreCase("true")) {
		    BrowserActions.maximizeWindow(driver.get());
		} else {
		    BrowserActions.setWindowResolution(driver.get());
		}
	    } else {
		String warningMsg = "The driver is null! because the browser type [" + browserTypeProperty
			+ "] is not valid/supported; Please choose a valid browser type from the given choices in the properties file";
		Logger.logMessage(warningMsg);
		fail(warningMsg);
//		throw new NullPointerException(warningMsg);
	    }
	}
	// Local Headless execution condition
	else if (executionType == ExecutionType.LOCAL_HEADLESS || (executionType == ExecutionType.FROM_PROPERTIES
		&& executionTypeProperty.equalsIgnoreCase("local headless"))) {
	    if (browserType == BrowserType.GOOGLE_CHROME
		    || (browserType == BrowserType.FROM_PROPERTIES && browserTypeProperty.equalsIgnoreCase("google chrome"))) {
		WebDriverManager.chromedriver().setup();
		driver.set(new ChromeDriver(getChromeOptions()));
		context.setAttribute("driver", driver.get());
		Helper.implicitWait(driver.get());
//		BrowserActions.maximizeWindow(driver);
	    } else if (browserType == BrowserType.MOZILLA_FIREFOX || (browserType == BrowserType.FROM_PROPERTIES
		    && browserTypeProperty.equalsIgnoreCase("mozilla firefox"))) {
		WebDriverManager.firefoxdriver().setup();
		driver.set(new FirefoxDriver(getFirefoxOptions()));
		context.setAttribute("driver", driver.get());
		Helper.implicitWait(driver.get());
//		BrowserActions.maximizeWindow(driver);
	    } else {
		String warningMsg = "The driver is null! because the browser type [" + browserTypeProperty
			+ "] is not valid/supported; Please choose a valid browser type from the given choices in the properties file";
		Logger.logMessage(warningMsg);
		fail(warningMsg);
//		throw new NullPointerException(warningMsg);
	    }
	} else {
	    String warningMsg = "The driver is null! because the execution type [" + executionTypeProperty
		    + "] is not valid/supported; Please choose a valid execution type from the given choices in the properties file";
	    Logger.logMessage(warningMsg);
	    fail(warningMsg);
//	    throw new NullPointerException(warningMsg);
	}
	return driver.get();
    }

    ////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////
    private static ChromeOptions getChromeOptions() {
	ChromeOptions chOptions = new ChromeOptions();
	chOptions.setHeadless(true);
	chOptions.addArguments("--window-size=1920,1080");
	
//	chOptions.addArguments("--start-maximized");
//	chOptions.setCapability("platform", Platform.LINUX);
//	chOptions.addArguments("--headless");
//	chOptions.addArguments("disable--infobars");
//	chOptions.setExperimentalOption("excludeSwitches", new String[] { "enable-automation" });
//	chOptions.addArguments("--ignore-certificate-errors");
	return chOptions;
    }

    private static FirefoxOptions getFirefoxOptions() {
	FirefoxOptions ffOptions = new FirefoxOptions();
	ffOptions.setHeadless(true);
	ffOptions.addArguments("--window-size=1920,1080");

	return ffOptions;
    }

}
