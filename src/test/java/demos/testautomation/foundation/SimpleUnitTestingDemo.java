package demos.testautomation.foundation;

import org.testng.Assert;
import org.testng.annotations.Test;

public class SimpleUnitTestingDemo {

    int x = 5;
    int y = 10;

    @Test
    public void calculation() {
	System.out.println("Calculating x + y");
	Assert.assertEquals(x + y, 16, "The calculation is wrong!");
    }
}
