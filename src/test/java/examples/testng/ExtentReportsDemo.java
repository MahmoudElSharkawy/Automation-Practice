package examples.testng;


import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReportsDemo {
    ExtentReports report;
    ExtentSparkReporter spark;
    ExtentTest test;

    @BeforeClass
    public void beforeClass() {
	report = new ExtentReports();
	spark = new ExtentSparkReporter("ExtentReports.html");
	report.attachReporter(spark);
	spark.config().setTheme(Theme.DARK);
	spark.config().setDocumentTitle("Automation Report");
	spark.config().setReportName("Extent Report Demo");
    }

    @Test
    public void info() {
	test = report.createTest("Info test case", "Testing info status");

	test.log(Status.INFO, "Info status!");
	test.info("Info status!");
    }

    @Test
    public void pass() {
	test = report.createTest("Pass test case", "Testing pass status");

	test.log(Status.PASS, "Pass status!");
	test.pass("Pass status!");
    }

    @Test
    public void skip() {
	test = report.createTest("Skip test case", "Testing skip status");

	test.log(Status.SKIP, "Skip status!");
	test.skip("Skip status!");
    }

    @Test
    public void fail() {
	test = report.createTest("Fail test case", "Testing fail status");

	test.log(Status.FAIL, "Fail status!");
	test.fail("Fail status!");
    }

    @AfterClass
    public void afterClass() {
	report.flush();
    }
}
