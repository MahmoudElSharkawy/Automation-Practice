package examples.testng;

import static org.testng.Assert.fail;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(examples.testng.ExtentReporterNG.class)
@Test(groups = { "classLevelGroup" })
public class ExtentReportsDemo2 {

//	The Test Cases
    @Test(groups = "Group1")
    public void test1() {
	System.out.println("Test#1 - Group1");
	fail();
    }

    @Test(groups = "Group2", dependsOnMethods = {"test1"})
    public void test2() {
	System.out.println("Test#2 - Group2");
    }

    @Test(groups = "Group1")
    public void test3() {
	System.out.println("Test#3 - Group1");
    }

    @Test(groups = "Group2")
    public void test4() {
	System.out.println("Test#4 - Group2");
    }

}
