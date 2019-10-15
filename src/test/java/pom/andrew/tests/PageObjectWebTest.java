package pom.andrew.tests;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;
import pom.andrew.pages.DuckDuckGoResultsPage;
import pom.andrew.pages.DuckDuckGoSearchPage;

import static org.testng.Assert.assertTrue;

import java.util.List;


public class PageObjectWebTest {

    private WebDriver driver;

    @BeforeClass
    public void initWebDriver() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test
    public void searchDuckDuckGo() {
        DuckDuckGoSearchPage searchPage = new DuckDuckGoSearchPage(driver);
        searchPage.loadPage();
        searchPage.search("giant panda");

        DuckDuckGoResultsPage resultsPage = new DuckDuckGoResultsPage(driver);
        List<String> linkTexts = resultsPage.getResultsLinkText("panda");

        for (String text : linkTexts) {
            assertTrue(text.matches("(?i).*panda.*"));
        }
    }

    @AfterClass
    public void quitWebDriver() {
        driver.quit();
    }
}
