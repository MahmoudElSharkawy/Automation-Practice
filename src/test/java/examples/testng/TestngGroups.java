package examples.testng;

import org.testng.annotations.AfterGroups;
import org.testng.annotations.BeforeGroups;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

//	Can add a Group for the class level that will be applied for all the Test Cases inside this class
@Test(groups = { "classLevelGroup" })
public class TestngGroups {

//	The Test Cases
    @Test(groups = "Group1")
    public void test1() {
	System.out.println("Test#1 - Group1");
    }

    @Test(groups = "Group2")
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

//     Before/After Method annotations that runs before/after EACH test case of the groups included in it
    @BeforeMethod(onlyForGroups = { "Group1" })
    public void beforeMethod() {
	System.out.println("Test onlyForGroups parameter for @BeforeMethod");
    }

//     Before/After Groups annotations that runs before/after the Group included ONLY ONCE
    @BeforeGroups(groups = "Group1")
    public void beforeGroups1() {
	System.out.println("Test Before Groups Annotation - Group1");
    }
    
    @AfterGroups(groups = "Group1")
    public void afterGroups1() {
	System.out.println("Test After Groups Annotation - Group1");
    }
    
    @BeforeGroups(groups = "Group2")
    public void beforeGroups2() {
	System.out.println("Test Before Groups Annotation - Group2");
    }

    @AfterGroups(groups = "Group2")
    public void afterGroups2() {
	System.out.println("Test After Groups Annotation - Group2");
    }
}
