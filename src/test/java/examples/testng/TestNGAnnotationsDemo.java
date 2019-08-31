package examples.testng;

import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class TestNGAnnotationsDemo {

	@Test
	public void TC1() {
		System.out.println("Test Case #1");
	}
	@Test
	public void TC2() {
		System.out.println("Test Case #2");
	}

	@BeforeMethod
	public void beforeMethod() {
		System.out.println("BeforeMethod will run Before each Test Case");
	}

	@AfterMethod
	public void afterMethod() {
		System.out.println("AfterMethod will run After each Test Case");
	}

	@BeforeClass
	public void beforeClass() {
		System.out.println("BeforeClass will run Before the Test Cases Only Once");
	}

	@AfterClass
	public void afterClass() {
		System.out.println("AfterClass will run After the Test Cases Only Once");
	}

	@BeforeTest
	public void beforeTest() {
		System.out.println("BeforeTest will run Before Class Only Once");
	}

	@AfterTest
	public void afterTest() {
		System.out.println("AfterTest will run After Class Only Once");
	}

	@BeforeSuite
	public void beforeSuite() {
		System.out.println("BeforeSuit will run Before All other annotations Only Once");
	}

	@AfterSuite
	public void afterSuite() {
		System.out.println("AfterSuit will run after Running the Test is done Only Once");
	}
}
