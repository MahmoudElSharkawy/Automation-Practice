package examples.testng;


import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReportsDemo {
    ExtentReports report;
    ExtentSparkReporter spark;
    ExtentTest test;

    @BeforeClass
    public void beforeClass() {
	report = new ExtentReports();
	spark = new ExtentSparkReporter("ExtentReports.html");
    }

    @Test
    public void info() {
	test = report.createTest("Info test case", "Testing info status");

	test.log(Status.INFO, "Info status!");
    }

    @Test
    public void pass() {
	test = report.createTest("Pass test case", "Testing pass status");

	test.log(Status.PASS, "Pass status!");
    }

    @Test
    public void skip() {
	test = report.createTest("Skip test case", "Testing skip status");

	test.log(Status.SKIP, "Skip status!");
    }

    @Test
    public void fail() {
	test = report.createTest("Fail test case", "Testing fail status");

	test.log(Status.FAIL, "Fail! status");
    }

    @AfterClass
    public void afterClass() {
	report.attachReporter(spark);
	report.flush();
    }
}
