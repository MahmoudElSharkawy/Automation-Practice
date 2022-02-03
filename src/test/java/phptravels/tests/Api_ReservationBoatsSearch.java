package phptravels.tests;

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
import phptravels.apis.PhptravelsApis;
import utils.ApiActions;
import utils.ExcelFileManager;

@Epic("PHPTRAVELS")
@Feature("API")
public class Api_ReservationBoatsSearch {
    private ApiActions apiObject;
    private PhptravelsApis phptravelsApis;
    private ExcelFileManager spreadSheet;

    @BeforeClass
    public void beforeClass() {
	spreadSheet = new ExcelFileManager(
		new File("src/test/resources/TestData/PhpTravels_ReservationBoatsSearch_TestData.xlsx"));
	spreadSheet.switchToSheet("API");
	
	apiObject = new ApiActions(PhptravelsApis.BASE_URL);
	phptravelsApis = new PhptravelsApis(apiObject);
    }

    @Test(description = "PHPTRAVELS - API - Validating the search function of the Boats")
    @Description("Given I'm on the PHPTravels home page; When I enter the data needed to search for Boats And click the search button; Then I should be navigated to the Boats search results page, Then I should get the search results related to the search value entered")
    @Story("Reservation Search")
    @Severity(SeverityLevel.CRITICAL)
    @TmsLink("Test_case")
    @Issue("Software_bug")
    public void testingBoatsSearch() {
	Response hotel = phptravelsApis.boatsSearch(spreadSheet.getCellData("Country Name", 2),
		spreadSheet.getCellData("City Name", 2), spreadSheet.getCellData("Boat Name", 2),
		spreadSheet.getCellData("Boat Date", 2), spreadSheet.getCellData("Adults Count", 2));
	Assert.assertTrue(hotel.getBody().asString().contains(spreadSheet.getCellData("Expected Boat Name", 2)),
		"No/Wrong Boat Name!; The Hotel Name should be: [" + spreadSheet.getCellData("Expected Boat Name", 2)
			+ "]");

    }
}
