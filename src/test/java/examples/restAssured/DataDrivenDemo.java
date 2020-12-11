package examples.restAssured;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import static org.hamcrest.Matchers.*;

public class DataDrivenDemo {
 
    @DataProvider
    public static Object[][] zipCodesAndPlaces() {
        return new Object[][] {
            { "us", "90210", "Beverly Hills" },
            { "us", "12345", "Schenectady" },
            { "ca", "B2R", "Waverley"}
        };
    }

    @Test(dataProvider = "zipCodesAndPlaces")
    public void DataProviderTest(String countryCode, String zipCode, String expectedPlaceName) {
	RestAssured.
        given().
            pathParam("countryCode", countryCode).pathParam("zipCode", zipCode).
        when().
            get("http://zippopotam.us/{countryCode}/{zipCode}").
        then().
            assertThat().
            body("places[0].'place name'", equalTo(expectedPlaceName));
    }

}