package liveproject.phptravels.tests.ReservationSearch;

import java.io.File;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Issue;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import io.qameta.allure.TmsLink;
import io.restassured.response.Response;
import liveproject.phptravels.apis.PhpTravels_APIs;
import utils.Spreadsheet;

@Epic("PHPTRAVELS")
@Feature("API")
public class Api_ReservationHotelsSearch_Test {
    PhpTravels_APIs apis;
    Spreadsheet spreadSheet;
    
    @BeforeClass
    public void beforeClass() {
	apis = new PhpTravels_APIs();
	spreadSheet = new Spreadsheet(
		new File("src/test/resources/TestData/LiveProject_PhpTravels_ReservationHotelsSearch_TestData.xlsx"));
	spreadSheet.switchToSheet("API");
    }
    
    @Test(description = "Validating the search function of the Hotels")
    @Description("Given I'm on the PHPTravels home page; When I Enter the data needed to search for hotels And click the search button; Then I should be navigated to the hotels search results page, Then I should get the search results related to the search value entered")
    @Story("Reservation Search")
    @Severity(SeverityLevel.CRITICAL)
    @TmsLink("focus-case-1539798")
    @Issue("bug-tracker#1")
    public void testingHotelsSearch() {
	Response hotel = apis.hotelsSearch(spreadSheet.getCellData("City Name", 2), spreadSheet.getCellData("Hotel Name", 2),
		spreadSheet.getCellData("Check In Date", 2), spreadSheet.getCellData("Check Out Date", 2),
		spreadSheet.getCellData("Adults Count", 2), spreadSheet.getCellData("Child Count", 2));
	Assert.assertTrue(hotel.getBody().asString().contains(spreadSheet.getCellData("Expected Hotel Name", 2)),
		"No/Wrong Hotel Name!; The Hotel Name should be: [" + spreadSheet.getCellData("Expected Hotel Name", 2)
			+ "]");
	
    }
}
